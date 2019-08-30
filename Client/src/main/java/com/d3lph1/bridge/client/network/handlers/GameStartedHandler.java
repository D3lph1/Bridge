package com.d3lph1.bridge.client.network.handlers;

import com.d3lph1.bridge.client.Client;
import com.d3lph1.bridge.common.network.PacketHandler;
import com.d3lph1.bridge.common.network.packets.GameStartedPacket;

public class GameStartedHandler implements PacketHandler<GameStartedPacket>
{
    private Client client;

    public GameStartedHandler(Client client)
    {
        this.client = client;
    }

    @Override
    public void handle(GameStartedPacket packet)
    {
        client.getHistory().clear();
        client.getHand().clear();
        client.opponentTurn();
    }
}
