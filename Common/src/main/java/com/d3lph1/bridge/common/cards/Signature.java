package com.d3lph1.bridge.common.cards;

import javafx.util.Pair;

import java.util.Optional;

public class Signature
{
    private Signature()
    {
    }

    public static Pair<Rank, Suit> splitIntoRankAndSuit(String signature)
    {
        String rankSymbol;
        String suitSymbol;
        if (signature.length() == 3) {
            rankSymbol = String.valueOf(signature.charAt(0)) + signature.charAt(1);
            suitSymbol = String.valueOf(signature.charAt(2));
        } else {
            rankSymbol = String.valueOf(signature.charAt(0));
            suitSymbol = String.valueOf(signature.charAt(1));
        }

        Optional<Rank> mbRank = Rank.fromSymbol(rankSymbol);
        if (!mbRank.isPresent()) {
            throw new IllegalArgumentException(String.format("Card rank with signature \"%s\" not found", signature));
        }
        Optional<Suit> mbSuit = Suit.fromSymbol(suitSymbol);
        if (!mbSuit.isPresent()) {
            throw new IllegalArgumentException(String.format("Card suit with signature \"%s\" not found", signature));
        }

        return new Pair<>(mbRank.get(), mbSuit.get());
    }
}
