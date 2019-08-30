package com.d3lph1.bridge.server.game.rules;

import com.d3lph1.bridge.server.cards.Card;
import com.d3lph1.bridge.server.game.State;

public class RegularRule extends AbstractRule
{
    @Override
    public boolean firstCheck(Card previous, Card current, State state)
    {
        // If previous card requires cover, it is allows playing by card with same suit
        // or by card that requires cover too.
        if (requiresCover.contains(previous)) {
            if (requiresCover.contains(current)) {
                return true;
            }

            return isSameSuits(previous, current);
        }

        if (anySuit.contains(previous)) {
            return isSameRanks(previous, current) || isSameSuits(previous, current);
        }

        if (state.isFirstTurn()) {
            return firstTurn(previous, current);
        }

        return notFirstTurn(previous, current);
    }

    @Override
    public boolean check(Card previous, Card current, State state)
    {
        // If previous card requires cover, it is allows playing by card with same suit
        // or by card that requires cover too.
        if (requiresCover.contains(previous)) {
            if (requiresCover.contains(current)) {
                return true;
            }

            return isSameSuits(previous, current);
        }

        if (anySuit.contains(previous)) {
            return isSameRanks(previous, current) || isSameSuits(previous, current);
        }

        return isSameRanks(previous, current);
    }

    private boolean firstTurn(Card previous, Card current)
    {
        return isSameRanks(previous, current);
    }

    private boolean notFirstTurn(Card previous, Card current)
    {
        return isSameRanks(previous, current) || isSameSuits(previous, current);
    }
}
