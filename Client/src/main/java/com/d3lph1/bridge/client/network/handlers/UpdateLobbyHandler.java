package com.d3lph1.bridge.client.network.handlers;

import com.d3lph1.bridge.client.Client;
import com.d3lph1.bridge.client.Player;
import com.d3lph1.bridge.common.network.PacketHandler;
import com.d3lph1.bridge.common.network.packets.LobbyPacket;
import com.d3lph1.bridge.common.network.packets.UpdateLobbyPacket;

public class UpdateLobbyHandler implements PacketHandler<UpdateLobbyPacket>
{
    private Client client;

    public UpdateLobbyHandler(Client client)
    {
        this.client = client;
    }

    @Override
    public void handle(UpdateLobbyPacket packet)
    {
        client.getLobby().removeAllPlayers();
        for (LobbyPacket.Player player : packet.getPlayers()) {
            client.getLobby().addPlayer(new Player(
                    player.getName(),
                    player.getScore(),
                    player.getNumCards()
            ));
        }
    }
}
