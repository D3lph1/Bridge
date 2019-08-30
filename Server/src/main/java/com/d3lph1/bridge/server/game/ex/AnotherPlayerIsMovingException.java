package com.d3lph1.bridge.server.game.ex;

public class AnotherPlayerIsMovingException extends IllegalStateException
{
    public AnotherPlayerIsMovingException()
    {
        super("An another player is moving now");
    }
}
