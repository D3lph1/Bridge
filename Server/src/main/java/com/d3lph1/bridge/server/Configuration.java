package com.d3lph1.bridge.server;

import com.d3lph1.bridge.server.cards.Card;
import com.d3lph1.bridge.server.cards.dealing.CardDealer;
import com.d3lph1.bridge.server.cards.dealing.DefaultCardDealer;

import java.util.List;

public class Configuration
{
    private final int numPlayers;

    private List<Card> cardPool = Card.ALL;

//    private CardDealer cardDealer = new DefinedCardDealer(
//            Stream.of(Card.SJ, Card.S2, Card.C8, Card.S8).collect(Collectors.toList()),
//            Stream.of(Card.C6, Card.S4, Card.S10, Card.C5, Card.D6).collect(Collectors.toList()),
//            Card.S5
//    );

    private CardDealer cardDealer = new DefaultCardDealer();

    public Configuration(int numPlayers)
    {
        this.numPlayers = numPlayers;
    }

    public int getNumPlayers()
    {
        return numPlayers;
    }

    public Configuration setCardPool(List<Card> cardPool)
    {
        this.cardPool = cardPool;

        return this;
    }

    public List<Card> getCardPool()
    {
        return cardPool;
    }

    public CardDealer getCardDealer()
    {
        return cardDealer;
    }

    public Configuration setCardDealer(CardDealer cardDealer)
    {
        this.cardDealer = cardDealer;

        return this;
    }
}
