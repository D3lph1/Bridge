package com.d3lph1.bridge.server.game.behaviors;

import com.d3lph1.bridge.server.Player;
import com.d3lph1.bridge.server.game.Behavior;
import com.d3lph1.bridge.server.game.Game;

/**
 * The behavior requires source player to cover card which own this behavior.
 */
public class RequiresCoverBehavior implements Behavior
{
    @Override
    public void apply(Player source, Player target, Game game)
    {
        game.getState().setRequiresCover(true);
    }
}
