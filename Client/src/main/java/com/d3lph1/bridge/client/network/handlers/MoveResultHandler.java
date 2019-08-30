package com.d3lph1.bridge.client.network.handlers;

import com.d3lph1.bridge.client.Client;
import com.d3lph1.bridge.common.game.Turn;
import com.d3lph1.bridge.common.network.PacketHandler;
import com.d3lph1.bridge.common.network.packets.MoveResultPacket;

public class MoveResultHandler implements PacketHandler<MoveResultPacket>
{
    private Client client;

    public MoveResultHandler(Client client)
    {
        this.client = client;
    }

    @Override
    public void handle(MoveResultPacket packet)
    {
        if (packet.getValue() == MoveResultPacket.Value.SUCCESS) {
            client.getHand().removeCards(client.getHistory().getCurrentMove());
            if (!Turn.isSkipped(client.getHistory().getCurrentMove())) {
                client.getHistory().commitMove();
            }
            client.opponentTurn();
        }
    }
}
