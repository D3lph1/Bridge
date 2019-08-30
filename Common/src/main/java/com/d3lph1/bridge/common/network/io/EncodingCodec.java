package com.d3lph1.bridge.common.network.io;

import com.d3lph1.bridge.common.network.Packet;
import io.netty.buffer.ByteBuf;

public interface EncodingCodec<T extends Packet>
{
    void encode(ByteBuf buf, T packet);
}
