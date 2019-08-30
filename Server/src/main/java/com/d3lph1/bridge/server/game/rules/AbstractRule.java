package com.d3lph1.bridge.server.game.rules;

import com.d3lph1.bridge.server.cards.Card;
import com.d3lph1.bridge.server.game.Rule;

import java.util.Collection;
import java.util.HashSet;

public abstract class AbstractRule implements Rule
{
    protected final Collection<Card> requiresCover = new HashSet<Card>() {{
        add(Card.C6);
        add(Card.D6);
        add(Card.H6);
        add(Card.S6);
    }};

    protected final Collection<Card> anySuit = new HashSet<Card>() {{
        add(Card.CJ);
        add(Card.DJ);
        add(Card.HJ);
        add(Card.SJ);
    }};

    protected boolean isSameRanks(Card first, Card second)
    {
        return first.getRank() == second.getRank();
    }

    protected boolean isSameSuits(Card first, Card second)
    {
        return first.getSuit() == second.getSuit();
    }
}
