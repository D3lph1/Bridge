package com.d3lph1.bridge.common.network.packets;

import com.d3lph1.bridge.common.network.Packet;

import java.util.Arrays;

public class MovePacket implements Packet
{
    private String[] cardsSignatures;

    private boolean tryBridge;

    public MovePacket(String[] cardsSignatures)
    {
        this(cardsSignatures, false);
    }

    public MovePacket(String[] cardsSignatures, boolean tryBridge)
    {
        this.cardsSignatures = cardsSignatures;
        this.tryBridge = tryBridge;
    }

    public String[] getCardsSignatures()
    {
        return cardsSignatures;
    }

    public boolean isTryBridge()
    {
        return tryBridge;
    }

    @Override
    public String toString()
    {
        return String.format(
                "%s(cardSignatures=%s, tryBridge=%s)",
                getClass().getSimpleName(),
                Arrays.toString(cardsSignatures),
                isTryBridge()
        );
    }
}
