package com.d3lph1.bridge.common.network.io;

import com.d3lph1.bridge.common.network.Packet;
import io.netty.buffer.ByteBuf;

public interface DecodingCodec<T extends Packet>
{
    T decode(ByteBuf buf);
}
