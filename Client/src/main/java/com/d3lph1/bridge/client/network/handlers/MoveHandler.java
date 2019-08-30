package com.d3lph1.bridge.client.network.handlers;

import com.d3lph1.bridge.client.Client;
import com.d3lph1.bridge.client.cards.Card;
import com.d3lph1.bridge.common.game.Turn;
import com.d3lph1.bridge.common.network.PacketHandler;
import com.d3lph1.bridge.common.network.packets.MovePacket;

import java.util.Arrays;

public class MoveHandler implements PacketHandler<MovePacket>
{
    private Client client;

    public MoveHandler(Client client)
    {
        this.client = client;
    }

    @Override
    public void handle(MovePacket packet)
    {
        Card[] cards = Arrays.stream(packet.getCardsSignatures()).map(Card::withDefaultImage).toArray(Card[]::new);
        client.getHistory().addCards(cards);
        if (!Turn.isSkipped(cards)) {
            client.getHistory().commitMove();
        }
    }
}
