package com.d3lph1.bridge.server.game;

import com.d3lph1.bridge.server.Player;

/**
 * Implementation of this interface represents any additional logic which must be
 * applied to players or game after cards pass validation (conflict checking).
 *
 * Each card can contains one or more or not contain at all behaviors.
 */
public interface Behavior
{
    /**
     * Apply any additional logic to players or game.
     *
     * @param source The player who made the move.
     * @param target The source's opponent.
     * @param game Game object for this move.
     */
    void apply(Player source, Player target, Game game);
}
