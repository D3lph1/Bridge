package com.d3lph1.bridge.server.cards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Deck
{
    private List<Card> originalCards;

    private List<Card> cards;

    private int multiplier = 1;

    public Deck(List<Card> cards)
    {
        this.originalCards = new ArrayList<>(cards);
        this.cards = new ArrayList<>(cards);
    }

    public static Deck createWith52Cards()
    {
        Deck deck = new Deck(Card.ALL);
        deck.shuffle();

        return deck;
    }

    public static Deck createWith36Cards()
    {
        Deck deck = new Deck(Card.REDUCED);
        deck.shuffle();

        return deck;
    }

    public static Deck create(List<Card> cards)
    {
        Deck deck = new Deck(cards);
        deck.shuffle();

        return deck;
    }

    public void shuffle()
    {
        Collections.shuffle(cards);
    }

    public Card pullTopCard()
    {
        Card card = cards.remove(cards.size() - 1);
        if (cards.size() == 0) {
            cards = new ArrayList<>(originalCards);
            multiplier++;
        }

        return card;
    }

    public List<Card> pullTopCards(int numCards)
    {
        List<Card> result = new ArrayList<>();
        for (int i = 0; i < numCards; i++) {
            result.add(pullTopCard());
        }

        return result;
    }

    public void removeCards(Collection<Card> cardsToRemove)
    {
        cards.removeAll(cardsToRemove);
    }

    public int size()
    {
        return cards.size();
    }

    public int getMultiplier()
    {
        return multiplier;
    }
}
