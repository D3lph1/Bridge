package com.d3lph1.bridge.server.game.behaviors;

import com.d3lph1.bridge.server.Player;
import com.d3lph1.bridge.server.game.Behavior;
import com.d3lph1.bridge.server.game.Game;

public class SkipTurnBehavior implements Behavior
{
    @Override
    public void apply(Player source, Player target, Game game)
    {
        target.setSkipTurn(true);
    }
}
