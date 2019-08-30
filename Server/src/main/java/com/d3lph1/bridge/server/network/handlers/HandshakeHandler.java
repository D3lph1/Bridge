package com.d3lph1.bridge.server.network.handlers;

import com.d3lph1.bridge.common.network.PacketHandler;
import com.d3lph1.bridge.common.network.packets.HandshakePacket;
import com.d3lph1.bridge.common.network.packets.LobbyPacket;
import com.d3lph1.bridge.common.network.packets.MessagePacket;
import com.d3lph1.bridge.common.network.packets.UpdateLobbyPacket;
import com.d3lph1.bridge.server.Player;
import com.d3lph1.bridge.server.network.Session;
import com.d3lph1.bridge.server.network.SessionRegistry;

import java.util.Collection;

public class HandshakeHandler implements PacketHandler<HandshakePacket>
{
    private Session session;

    public HandshakeHandler(Session session)
    {
        this.session = session;
    }

    @Override
    public void handle(HandshakePacket packet)
    {
        session.setPlayer(new Player(session, packet.getName(), 0));

        Collection<Player> players = session.getServer().getPlayers();
        LobbyPacket.Player[] playersToSend = new LobbyPacket.Player[players.size()];
        int i = 0;
        for (Player player : players) {
            playersToSend[i++] = new LobbyPacket.Player(player.getName(), player.getScore(), player.getCards().size());
        }

        session.send(new LobbyPacket(true, playersToSend));
        SessionRegistry registry = session.getServer().getSessionRegistry();
        registry
                .all()
                .stream()
                .filter(s -> s.getPlayer() != null)
                .filter(s -> !s.getPlayer().equals(session.getPlayer()))
                .forEach(s -> s.send(new UpdateLobbyPacket(playersToSend)));

        int numPlayers = session.getServer().getConfiguration().getNumPlayers();
        registry.broadcastToAll(
                new MessagePacket(String.format(
                        "Player %s has joined the game. %d/%d players are ready.",
                        session.getPlayer().getName(),
                        players.size(),
                        numPlayers
                ), "SERVER")
        );

        if (players.size() == numPlayers) {
            session.getServer().startGame();
        }
    }
}
