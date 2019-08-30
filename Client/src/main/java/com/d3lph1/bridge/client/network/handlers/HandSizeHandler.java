package com.d3lph1.bridge.client.network.handlers;

import com.d3lph1.bridge.client.Client;
import com.d3lph1.bridge.client.Player;
import com.d3lph1.bridge.common.network.PacketHandler;
import com.d3lph1.bridge.common.network.packets.HandSizePacket;

import java.util.Optional;

public class HandSizeHandler implements PacketHandler<HandSizePacket>
{
    private final Client client;

    public HandSizeHandler(Client client)
    {
        this.client = client;
    }

    @Override
    public void handle(HandSizePacket packet)
    {
        for (HandSizePacket.PlayerNameHandSizePair pair: packet.getPairs()) {
            Optional<Player> mbPlayer = client.getLobby()
                    .getPlayers()
                    .stream()
                    .filter(player -> player.getName().equals(pair.getPlayerName()))
                    .findFirst();
            if (!mbPlayer.isPresent()) {
                throw new RuntimeException("Player with name \"" + pair.getPlayerName() + "\" not found");
            }

            client.getLobby().setHandSize(mbPlayer.get(), pair.getHandSize());
        }
    }
}
