package com.d3lph1.bridge.server.game;

import com.d3lph1.bridge.common.cards.AbstractCard;
import com.d3lph1.bridge.common.cards.Rank;
import com.d3lph1.bridge.common.game.Turn;
import com.d3lph1.bridge.common.network.packets.*;
import com.d3lph1.bridge.server.Player;
import com.d3lph1.bridge.server.Server;
import com.d3lph1.bridge.server.cards.Card;
import com.d3lph1.bridge.server.cards.Deck;
import com.d3lph1.bridge.server.cards.dealing.CardDealer;
import com.d3lph1.bridge.server.game.ex.AnotherPlayerIsMovingException;
import com.d3lph1.bridge.server.game.ex.BridgeDoesNotAllowedException;
import com.d3lph1.bridge.server.game.ex.InvalidCardSetException;
import com.d3lph1.bridge.server.game.ex.SkippingTurnWithoutTakingCardException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game
{
    public static final int BRIDGE_SEQUENCE_LENGTH = 4;

    private Server server;

    private Deck deck;

    private History history;

    private State state;

    private Player currentTurnPlayer;

    public Game(Server server)
    {
        this.server = server;
        if (server.getPlayers().size() != 2) {
            throw new IllegalStateException("The game allows only 2 players");
        }
        server.getSessionRegistry().broadcastToAll(
                new MessagePacket("Game started.", "SERVER")
        );
        state = new State();
        server.getSessionRegistry().broadcastToAll(new GameStartedPacket());
        deck = Deck.create(server.getConfiguration().getCardPool());
        CardDealer cardDealer = server.getConfiguration().getCardDealer();
        cardDealer.deal(deck, server.getPlayers());
        turn(server.getPlayers().get(0));
        history = new History();
        makeMove(currentTurnPlayer, Stream.of(cardDealer.firstCard(deck)).collect(Collectors.toList()));
        server.getSessionRegistry().broadcastToAll(new DeckSizePacket(deck.size()));
        broadcastHandSizes();
    }

    private void turn(Player player)
    {
        currentTurnPlayer = player;
        currentTurnPlayer.getSession().send(new TurnPacket());
    }

    public boolean acceptMove(Player player, List<Card> cards)
    {
        if (!currentTurnPlayer.equals(player)) {
            throw new AnotherPlayerIsMovingException();
        }

        if (Turn.isSkipped(cards) && !player.isTookCard() && !getState().isFirstTurn()) {
            throw new SkippingTurnWithoutTakingCardException();
        }

        List<Card> cardsWithoutJack = cards.stream().filter(card -> card.getRank() != Rank.JACK).collect(Collectors.toList());
        if (!player.getCards().containsAll(cardsWithoutJack)) {
            throw new InvalidCardSetException();
        }
        List<Card> jacks = cards.stream().filter(card -> card.getRank() == Rank.JACK).collect(Collectors.toList());
        List<Card> usersJacks = player.getCards().stream().filter(card -> card.getRank() == Rank.JACK).collect(Collectors.toList());
        if (jacks.size() > usersJacks.size()) {
            throw new InvalidCardSetException();
        }

        if (!Turn.isSkipped(cards)) {
            Optional<Card> mbLast = history.getLast();

            if (!mbLast.isPresent()) {
                throw new RuntimeException("Can not find last card in the history");
            }

            Queue<Card> processingQueue = new LinkedList<>(cards);

            // Handling first card from the processing queue separately.
            Card previous = mbLast.get();
            Card current = processingQueue.poll();

            for (Rule rule : current.getRules()) {
                if (!rule.firstCheck(previous, current, getState())) {
                    return false;
                }
            }

            // Handling rest cards from processing queue.
            previous = current;
            current = processingQueue.poll();

            while (current != null) {
                for (Rule rule : current.getRules()) {
                    if (!rule.check(previous, current, getState())) {
                        return false;
                    }
                }

                previous = current;
                current = processingQueue.poll();
            }

            Player nextPlayer = getNextPlayer();
            cards.forEach(card -> {
                if (getState().isRequiresCover()) {
                    getState().setRequiresCover(false);
                }
                applyCard(card, player, nextPlayer);
            });

            history.addCards(cards);
            history.commitMove();

            player.removeCards(cardsWithoutJack);
            int jacksAmount = jacks.size();
            for (Card card : player.getCards()) {
                if (jacksAmount == 0) {
                    break;
                }

                if (card.getRank() == Rank.JACK) {
                    player.removeCard(card);
                    jacksAmount--;
                }
            }
        }

        getState().setFirstTurnFalse();
        player.getSession().send(new MoveResultPacket(MoveResultPacket.Value.SUCCESS));
        server.getSessionRegistry()
                .broadcastToAllExcept(new MovePacket(AbstractCard.signatures(cards)), player.getSession());
        player.setTookCard(false);
        broadcastHandSizes();

        if (player.getCards().size() == 0) {
            endGame(server.getPlayers().get(0), server.getPlayers().get(1));
        } else {
            nextPlayer();
        }

        return true;
    }

    public void bridge()
    {
        List<Card> lastCards = history.getLast(BRIDGE_SEQUENCE_LENGTH);
        if (lastCards.size() < BRIDGE_SEQUENCE_LENGTH) {
            throw new BridgeDoesNotAllowedException();
        }

        Card firstCard = lastCards.get(0);
        for (Card card : lastCards) {
            if (firstCard.getRank() != card.getRank()) {
                throw new BridgeDoesNotAllowedException();
            }
        }

        endGame(server.getPlayers().get(0), server.getPlayers().get(1));
    }

    private void endGame(Player firstPlayer, Player secondPlayer)
    {
        int firstPlayerScore = 0;
        int secondPlayerScore = 0;

        int firstPlayerCardsLength = firstPlayer.getCards().size();
        EndGamePacket.Player.Card[] firstPlayerCards = new EndGamePacket.Player.Card[firstPlayerCardsLength];
        for (int i = 0; i < firstPlayerCardsLength; i++) {
            Card card = firstPlayer.getCards().get(i);
            firstPlayerCards[i] = new EndGamePacket.Player.Card(card.toString(), card.getCost());
            firstPlayerScore += card.getCost();
        }
        int secondPlayerCardsLength = secondPlayer.getCards().size();
        EndGamePacket.Player.Card[] secondPlayerCards = new EndGamePacket.Player.Card[secondPlayerCardsLength];
        for (int i = 0; i < secondPlayerCardsLength; i++) {
            Card card = secondPlayer.getCards().get(i);
            secondPlayerCards[i] = new EndGamePacket.Player.Card(card.toString(), card.getCost());
            secondPlayerScore += card.getCost();
        }

        firstPlayerScore *= deck.getMultiplier();
        secondPlayerScore *= deck.getMultiplier();

        firstPlayer.addScore(firstPlayerScore);
        secondPlayer.addScore(secondPlayerScore);

        EndGamePacket.Player[] players = new EndGamePacket.Player[2];
        players[0] = new EndGamePacket.Player(firstPlayer.getName(), firstPlayerScore, firstPlayerCards);
        players[1] = new EndGamePacket.Player(secondPlayer.getName(), secondPlayerScore, secondPlayerCards);

        server.getSessionRegistry().broadcastToAll(new EndGamePacket(players));

        UpdateScorePacket.PlayerNameScorePair[] scorePairs = new UpdateScorePacket.PlayerNameScorePair[2];
        scorePairs[0] = new UpdateScorePacket.PlayerNameScorePair(firstPlayer.getName(), firstPlayer.getScore());
        scorePairs[1] = new UpdateScorePacket.PlayerNameScorePair(secondPlayer.getName(), secondPlayer.getScore());

        server.getSessionRegistry().broadcastToAll(new UpdateScorePacket(scorePairs));

        // Before start next round it is need to clear player's hands
        firstPlayer.removeAllCards();
        secondPlayer.removeAllCards();

        // ...and history
        history.clear();

        // Finally, start next round
        server.startGame();
    }

    private void applyCard(Card card, Player source, Player target)
    {
        card.getBehaviors().forEach(behavior -> behavior.apply(source, target, this));
    }

    public void takeCard(Player player)
    {
        if (!currentTurnPlayer.equals(player)) {
            throw new IllegalStateException("An another player is moving now.");
        }

        if (player.isTookCard()) {
            throw new IllegalStateException("A player can take card only once during his turn.");
        }

        if (getState().isFirstTurn() && !getState().isRequiresCover()) {
            throw new IllegalStateException("A player can not take card on first turn");
        }

        player.setTookCard(true);
        Card card = deck.pullTopCard();
        player.addCard(card);
        server.getSessionRegistry()
                .broadcastToAll(new DeckSizePacket(deck.size()));
        broadcastHandSizes();
    }

    private void makeMove(Player player, Collection<Card> cards)
    {
        Player nextPlayer = getNextPlayer();
        history.addCards(cards);
        server.getSessionRegistry()
                .broadcastToAll(new MovePacket(AbstractCard.signatures(cards)));
        cards.forEach(card -> applyCard(card, player, nextPlayer));
    }

    private Player getNextPlayer()
    {
        Optional<Player> mbPlayer = server.getPlayers()
                .stream()
                .filter(player -> player != currentTurnPlayer)
                .findFirst();
        if (!mbPlayer.isPresent()) {
            throw new RuntimeException();
        }

        return mbPlayer.get();
    }

    private void nextPlayer()
    {
        Player player = getNextPlayer();
        if (getState().isRequiresCover()) {
            turn(currentTurnPlayer);
            return;
        }
        if (!player.isSkipTurn()) {
            turn(player);
        } else {
            turn(currentTurnPlayer);
            player.setSkipTurn(false);
        }
    }

    private void broadcastHandSizes()
    {
        int length = server.getPlayers().size();
        HandSizePacket.PlayerNameHandSizePair[] pairs = new HandSizePacket.PlayerNameHandSizePair[length];
        for (int i = 0; i < length; i++) {
            Player player = server.getPlayers().get(i);
            pairs[i] = new HandSizePacket.PlayerNameHandSizePair(player.getName(), player.getCards().size());
        }

        server.getSessionRegistry().broadcastToAll(new HandSizePacket(pairs));
    }

    public Deck getDeck()
    {
        return deck;
    }

    public State getState()
    {
        return state;
    }
}
