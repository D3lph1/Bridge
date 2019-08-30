package com.d3lph1.bridge.common.network.codecs;

import com.d3lph1.bridge.common.network.io.DecodingCodec;
import com.d3lph1.bridge.common.network.io.EncodingCodec;
import com.d3lph1.bridge.common.network.packets.TurnPacket;
import io.netty.buffer.ByteBuf;

public class TurnCodec implements EncodingCodec<TurnPacket>, DecodingCodec<TurnPacket>
{
    @Override
    public TurnPacket decode(ByteBuf buf)
    {
        return new TurnPacket();
    }

    @Override
    public void encode(ByteBuf buf, TurnPacket packet)
    {
        //
    }
}
