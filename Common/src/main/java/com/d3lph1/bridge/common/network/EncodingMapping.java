package com.d3lph1.bridge.common.network;

import com.d3lph1.bridge.common.network.io.EncodingCodec;

public class EncodingMapping<T extends Packet> extends Mapping<T>
{
    private Class<? extends EncodingCodec<T>> codec;

    public EncodingMapping(int packetId, Class<T> packetClass, Class<? extends EncodingCodec<T>> codec)
    {
        super(packetId, packetClass);
        this.codec = codec;
    }

    public Class<? extends EncodingCodec<T>> getCodec()
    {
        return codec;
    }
}
