package com.d3lph1.bridge.client.network.handlers;

import com.d3lph1.bridge.client.Client;
import com.d3lph1.bridge.client.cards.Card;
import com.d3lph1.bridge.common.network.PacketHandler;
import com.d3lph1.bridge.common.network.packets.DealCardPacket;

public class DealCardHandler implements PacketHandler<DealCardPacket>
{
    private Client client;

    public DealCardHandler(Client client)
    {
        this.client = client;
    }

    @Override
    public void handle(DealCardPacket packet)
    {
        Card card = Card.withDefaultImage(packet.getCardSignature());
        client.getHand().addCard(card);
        client.getHand().myTurn();
    }
}
