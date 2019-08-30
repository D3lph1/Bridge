package com.d3lph1.bridge.common.cards;

import java.util.Optional;

public enum Rank
{
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ACE("A");

    private String symbol;

    Rank(String symbol)
    {
        this.symbol = symbol;
    }

    public static Optional<Rank> fromSymbol(String symbol)
    {
        for (Rank rank : Rank.values()) {
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
