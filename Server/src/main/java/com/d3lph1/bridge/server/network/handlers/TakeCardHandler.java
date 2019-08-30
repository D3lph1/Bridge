package com.d3lph1.bridge.server.network.handlers;

import com.d3lph1.bridge.common.network.PacketHandler;
import com.d3lph1.bridge.common.network.packets.MessagePacket;
import com.d3lph1.bridge.common.network.packets.TakeCardPacket;
import com.d3lph1.bridge.server.game.Game;
import com.d3lph1.bridge.server.network.Session;

import java.util.Optional;

public class TakeCardHandler implements PacketHandler<TakeCardPacket>
{
    private final Session session;

    public TakeCardHandler(Session session)
    {
        this.session = session;
    }

    @Override
    public void handle(TakeCardPacket packet)
    {
        Optional<Game> mbGame = session.getServer().getGame();
        if (!mbGame.isPresent()) {
            throw new IllegalStateException("Not in game");
        }

        try {
            mbGame.get().takeCard(session.getPlayer());
        } catch (IllegalStateException e) {
            session.send(new MessagePacket(e.getMessage(), "SERVER"));
        }
    }
}
