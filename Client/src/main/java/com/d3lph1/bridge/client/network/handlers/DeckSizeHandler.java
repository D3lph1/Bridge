package com.d3lph1.bridge.client.network.handlers;

import com.d3lph1.bridge.client.Client;
import com.d3lph1.bridge.common.network.PacketHandler;
import com.d3lph1.bridge.common.network.packets.DeckSizePacket;

public class DeckSizeHandler implements PacketHandler<DeckSizePacket>
{
    private final Client client;

    public DeckSizeHandler(Client client)
    {
        this.client = client;
    }

    @Override
    public void handle(DeckSizePacket packet)
    {
        client.getDeck().setSize(packet.getSize());
    }
}
