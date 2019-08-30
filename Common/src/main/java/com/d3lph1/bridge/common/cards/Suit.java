package com.d3lph1.bridge.common.cards;

import java.util.Optional;

public enum Suit
{
    CLOVERS("C"),
    DIAMONDS("D"),
    HEARTS("H"),
    SPADES("S");

    private String symbol;

    Suit(String symbol)
    {
        this.symbol = symbol;
    }

    public static Optional<Suit> fromSymbol(String symbol)
    {
        for (Suit rank : Suit.values()) {
            if (rank.getSymbol().equals(symbol)) {
                return Optional.of(rank);
            }
        }

        return Optional.empty();
    }

    public String getSymbol()
    {
        return symbol;
    }
}
