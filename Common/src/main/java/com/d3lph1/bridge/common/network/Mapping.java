package com.d3lph1.bridge.common.network;

import java.util.HashSet;
import java.util.Set;

public abstract class Mapping<T extends Packet>
{
    private int packetId;

    private Class<T> packetClass;

    private Set<Class<? extends PacketHandler<T>>> handlers;

    public Mapping(int packetId, Class<T> packetClass, Class<? extends PacketHandler<T>> handler)
    {
        this.packetId = packetId;
        this.packetClass = packetClass;
        handlers = new HashSet<>();
        handlers.add(handler);
    }

    public Mapping(int packetId, Class<T> packetClass, Set<Class<? extends PacketHandler<T>>> handlers)
    {
        this.packetId = packetId;
        this.packetClass = packetClass;
        this.handlers = handlers;
    }

    public Mapping(int packetId, Class<T> packetClass)
    {
        this.packetId = packetId;
        this.packetClass = packetClass;
    }

    public int getPacketId()
    {
        return packetId;
    }

    public Class<T> getPacketClass()
    {
        return packetClass;
    }

    public Set<Class<? extends PacketHandler<T>>> getHandlers()
    {
        return handlers;
    }
}
