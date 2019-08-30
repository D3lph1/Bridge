package com.d3lph1.bridge.server.game;

public class State
{
    private boolean firstTurn = true;

    private boolean requiresCover = false;

    public boolean isFirstTurn()
    {
        return firstTurn;
    }

    public void setFirstTurnFalse()
    {
        firstTurn = false;
    }

    public boolean isRequiresCover()
    {
        return requiresCover;
    }

    public void setRequiresCover(boolean requiresCover)
    {
        this.requiresCover = requiresCover;
    }
}
