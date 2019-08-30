package com.d3lph1.bridge.server.cards.dealing;

import com.d3lph1.bridge.server.Player;
import com.d3lph1.bridge.server.cards.Card;
import com.d3lph1.bridge.server.cards.Deck;

import java.util.Collection;
import java.util.List;

public class DefinedCardDealer implements CardDealer
{
    private final Collection<Card> toFirstPlayer;

    private final Collection<Card> toSecondPlayer;

    private final Card firstCard;

    public DefinedCardDealer(Collection<Card> toFirstPlayer, Collection<Card> toSecondPlayer, Card firstCard)
    {
        this.toFirstPlayer = toFirstPlayer;
        this.toSecondPlayer = toSecondPlayer;
        this.firstCard = firstCard;
    }

    @Override
    public void deal(Deck deck, List<Player> players)
    {
        players.get(0).addCards(toFirstPlayer);
        players.get(1).addCards(toSecondPlayer);
        deck.removeCards(toFirstPlayer);
        deck.removeCards(toSecondPlayer);
    }

    @Override
    public Card firstCard(Deck deck)
    {
        return firstCard;
    }
}
