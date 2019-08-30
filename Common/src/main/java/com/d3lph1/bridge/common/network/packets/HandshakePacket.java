package com.d3lph1.bridge.common.network.packets;

import com.d3lph1.bridge.common.network.Packet;

public class HandshakePacket implements Packet
{
    private final String name;

    public HandshakePacket(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return String.format(
                "%s(name=\"%s\")",
                getClass().getSimpleName(),
                getName()
        );
    }
}
