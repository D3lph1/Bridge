package com.d3lph1.bridge.server.game.ex;

public class SkippingTurnWithoutTakingCardException extends IllegalStateException
{
    public SkippingTurnWithoutTakingCardException()
    {
        super("The player must take card before skip turn.");
    }
}
