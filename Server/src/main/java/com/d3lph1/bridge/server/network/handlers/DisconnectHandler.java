package com.d3lph1.bridge.server.network.handlers;

import com.d3lph1.bridge.common.network.PacketHandler;
import com.d3lph1.bridge.common.network.packets.DisconnectPacket;
import com.d3lph1.bridge.common.network.packets.LobbyPacket;
import com.d3lph1.bridge.common.network.packets.MessagePacket;
import com.d3lph1.bridge.common.network.packets.UpdateLobbyPacket;
import com.d3lph1.bridge.server.Player;
import com.d3lph1.bridge.server.network.Session;

import java.util.Collection;

public class DisconnectHandler implements PacketHandler<DisconnectPacket>
{
    private Session session;

    public DisconnectHandler(Session session)
    {
        this.session = session;
    }

    @Override
    public void handle(DisconnectPacket packet)
    {
        session.destroy();

        Collection<Player> players = session.getServer().getPlayers();
        LobbyPacket.Player[] playersToSend = new LobbyPacket.Player[players.size() - 1];
        int i = 0;
        for (Player player : players) {
            if (!player.equals(session.getPlayer())) {
                playersToSend[i++] = new LobbyPacket.Player(player.getName(), player.getScore(), player.getCards().size());
            }
        }

        session
                .getServer()
                .getSessionRegistry()
                .all()
                .stream()
                .filter(s -> !s.isDestroyed())
                .filter(s -> s.getPlayer() != null)
                .forEach(s -> {
                    int numPlayers = session.getServer().getConfiguration().getNumPlayers();
                    s.send(new UpdateLobbyPacket(playersToSend));
                    s.send(new MessagePacket(String.format(
                            "Player %s left the game. %d/%d players are ready.",
                            session.getPlayer().getName(),
                            playersToSend.length,
                            numPlayers
                    ), "SERVER"));
                });
        session.getChannel().close();
    }
}
