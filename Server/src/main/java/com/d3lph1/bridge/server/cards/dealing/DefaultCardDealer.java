package com.d3lph1.bridge.server.cards.dealing;

import com.d3lph1.bridge.server.Player;
import com.d3lph1.bridge.server.cards.Card;
import com.d3lph1.bridge.server.cards.Deck;

import java.util.List;

public class DefaultCardDealer implements CardDealer
{
    private static final int CARDS_AMOUNT_FOR_FIRST_PLAYER = 4;

    private static final int CARDS_AMOUNT_FOR_SECOND_PLAYER = 5;

    @Override
    public void deal(Deck deck, List<Player> players)
    {
        players.get(1).addCards(deck.pullTopCards(CARDS_AMOUNT_FOR_SECOND_PLAYER));
        players.get(0).addCards(deck.pullTopCards(CARDS_AMOUNT_FOR_FIRST_PLAYER));
    }

    @Override
    public Card firstCard(Deck deck)
    {
        return deck.pullTopCard();
    }
}
