package com.d3lph1.bridge.client.network.handlers;

import com.d3lph1.bridge.client.Client;
import com.d3lph1.bridge.client.Player;
import com.d3lph1.bridge.common.network.PacketHandler;
import com.d3lph1.bridge.common.network.packets.UpdateScorePacket;

import java.util.Optional;

public class UpdateScoreHandler implements PacketHandler<UpdateScorePacket>
{
    private final Client client;

    public UpdateScoreHandler(Client client)
    {
        this.client = client;
    }

    @Override
    public void handle(UpdateScorePacket packet)
    {
        for (UpdateScorePacket.PlayerNameScorePair pair: packet.getPairs()) {
            Optional<Player> mbPlayer = client.getLobby()
                    .getPlayers()
                    .stream()
                    .filter(player -> player.getName().equals(pair.getPlayerName()))
                    .findFirst();
            if (!mbPlayer.isPresent()) {
                throw new RuntimeException("Player with name \"" + pair.getPlayerName() + "\" not found");
            }

            client.getLobby().setScore(mbPlayer.get(), pair.getScore());
        }
    }
}
