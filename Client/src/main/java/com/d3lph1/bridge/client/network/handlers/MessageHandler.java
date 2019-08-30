package com.d3lph1.bridge.client.network.handlers;

import com.d3lph1.bridge.client.Client;
import com.d3lph1.bridge.common.network.PacketHandler;
import com.d3lph1.bridge.common.network.packets.MessagePacket;

public class MessageHandler implements PacketHandler<MessagePacket>
{
    private Client client;

    public MessageHandler(Client client)
    {
        this.client = client;
    }

    @Override
    public void handle(MessagePacket packet)
    {
        client.getChat().display(packet.getContent(), packet.getSource());
    }
}
