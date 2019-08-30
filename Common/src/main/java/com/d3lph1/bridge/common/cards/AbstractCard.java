package com.d3lph1.bridge.common.cards;

import java.util.Collection;

public abstract class AbstractCard
{
    private final Rank rank;

    private final Suit suit;

    public AbstractCard(Rank rank, Suit suit)
    {
        this.rank = rank;
        this.suit = suit;
    }

    public static String[] signatures(Collection<? extends AbstractCard> cards)
    {
        return cards.stream().map(AbstractCard::toString).toArray(String[]::new);
    }

    public Rank getRank()
    {
        return rank;
    }

    public Suit getSuit()
    {
        return suit;
    }

    public String getSignature()
    {
        return getRank().getSymbol() + getSuit().getSymbol();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof AbstractCard) {
            return ((AbstractCard) obj).getSignature().equals(this.getSignature());
        }

        return false;
    }

    @Override
    public String toString()
    {
        return getSignature();
    }
}
