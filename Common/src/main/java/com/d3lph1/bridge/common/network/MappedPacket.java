package com.d3lph1.bridge.common.network;

public class MappedPacket
{
    private final DecodingMapping<Packet> mapping;

    private final Packet packet;

    public MappedPacket(DecodingMapping<Packet> mapping, Packet packet)
    {
        this.mapping = mapping;
        this.packet = packet;
    }

    public DecodingMapping<Packet> getMapping()
    {
        return mapping;
    }

    public Packet getPacket()
    {
        return packet;
    }
}
