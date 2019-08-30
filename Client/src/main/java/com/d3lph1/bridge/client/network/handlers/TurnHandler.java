package com.d3lph1.bridge.client.network.handlers;

import com.d3lph1.bridge.client.Client;
import com.d3lph1.bridge.common.network.PacketHandler;
import com.d3lph1.bridge.common.network.packets.TurnPacket;

public class TurnHandler implements PacketHandler<TurnPacket>
{
    private Client client;

    public TurnHandler(Client client)
    {
        this.client = client;
    }

    @Override
    public void handle(TurnPacket packet)
    {
        client.myTurn();
    }
}
