package com.d3lph1.bridge.common.network;

public interface PacketHandler<T extends Packet>
{
    void handle(T packet);
}
