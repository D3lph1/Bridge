package com.d3lph1.bridge.server.game.behaviors;

import com.d3lph1.bridge.common.network.packets.DeckSizePacket;
import com.d3lph1.bridge.server.Player;
import com.d3lph1.bridge.server.game.Behavior;
import com.d3lph1.bridge.server.game.Game;

public class TakeCardsBehavior implements Behavior
{
    private final int numCards;

    public TakeCardsBehavior(int numCards)
    {
        this.numCards = numCards;
    }

    @Override
    public void apply(Player source, Player target, Game game)
    {
        target.addCards(game.getDeck().pullTopCards(numCards));
        target.getSession()
                .getServer()
                .getSessionRegistry()
                .broadcastToAll(new DeckSizePacket(game.getDeck().size()));
    }
}
