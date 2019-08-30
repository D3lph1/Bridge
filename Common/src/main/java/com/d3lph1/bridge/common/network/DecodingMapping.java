package com.d3lph1.bridge.common.network;

import com.d3lph1.bridge.common.network.io.DecodingCodec;

import java.util.Set;

public class DecodingMapping<T extends Packet> extends Mapping<T>
{
    private Class<? extends DecodingCodec<T>> codec;

    public DecodingMapping(int packetId, Class<T> packetClass, Class<? extends DecodingCodec<T>> codec, Class<? extends PacketHandler<T>> handler)
    {
        super(packetId, packetClass, handler);
        this.codec = codec;
    }

    public DecodingMapping(int packetId, Class<T> packetClass, Class<? extends DecodingCodec<T>> codec, Set<Class<? extends PacketHandler<T>>> handlers)
    {
        super(packetId, packetClass, handlers);
        this.codec = codec;
    }

    public Class<? extends DecodingCodec<T>> getCodec()
    {
        return codec;
    }
}
