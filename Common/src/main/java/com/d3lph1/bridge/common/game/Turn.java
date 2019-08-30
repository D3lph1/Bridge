package com.d3lph1.bridge.common.game;

import com.d3lph1.bridge.common.cards.AbstractCard;

import java.util.Collection;

public class Turn
{
    private Turn()
    {
    }

    public static boolean isSkipped(Collection<? extends AbstractCard> cards)
    {
        return cards.size() == 0;
    }

    public static boolean isSkipped(AbstractCard[] cards)
    {
        return cards.length == 0;
    }
}
