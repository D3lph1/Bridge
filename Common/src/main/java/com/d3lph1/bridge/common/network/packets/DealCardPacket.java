package com.d3lph1.bridge.common.network.packets;

import com.d3lph1.bridge.common.network.Packet;

public class DealCardPacket implements Packet
{
    private final String cardSignature;

    public DealCardPacket(String cardSignature)
    {
        this.cardSignature = cardSignature;
    }

    public String getCardSignature()
    {
        return cardSignature;
    }

    @Override
    public String toString()
    {
        return String.format(
                "%s(cardSignature=\"%s\")",
                getClass().getSimpleName(),
                cardSignature
        );
    }
}
