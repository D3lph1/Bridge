package com.d3lph1.bridge.common.network.packets;

import com.d3lph1.bridge.common.network.Packet;

public class DeckSizePacket implements Packet
{
    private final int size;

    public DeckSizePacket(int size)
    {
        this.size = size;
    }

    public int getSize()
    {
        return size;
    }

    @Override
    public String toString()
    {
        return String.format("%s(size=%d)", getClass().getSimpleName(), getSize());
    }
}
