package com.d3lph1.bridge.server.game.ex;

import com.d3lph1.bridge.server.game.Game;

public class BridgeDoesNotAllowedException extends IllegalStateException
{
    public BridgeDoesNotAllowedException()
    {
        super("It is possible to make \"Bridge!\" only when there are " + Game.BRIDGE_SEQUENCE_LENGTH
                + " cards with the same rank in the history.");
    }
}
