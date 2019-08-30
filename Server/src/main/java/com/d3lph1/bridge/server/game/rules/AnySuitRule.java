package com.d3lph1.bridge.server.game.rules;

import com.d3lph1.bridge.server.cards.Card;
import com.d3lph1.bridge.server.game.State;

public class AnySuitRule extends AbstractRule
{
    @Override
    public boolean firstCheck(Card previous, Card current, State state)
    {
        if (state.isFirstTurn()) {
            return isSameRanks(previous, current);
        }

        // It is not matter what suit has previous card if it is not first turn.
        return true;
    }

    @Override
    public boolean check(Card previous, Card current, State state)
    {
        if (state.isFirstTurn()) {
            return isSameRanks(previous, current);
        }

        // It is not matter what suit has previous card if it is not first turn.
        return true;
    }
}
