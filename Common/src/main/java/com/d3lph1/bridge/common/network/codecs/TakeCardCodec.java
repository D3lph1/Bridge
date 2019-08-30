package com.d3lph1.bridge.common.network.codecs;

import com.d3lph1.bridge.common.network.io.DecodingCodec;
import com.d3lph1.bridge.common.network.io.EncodingCodec;
import com.d3lph1.bridge.common.network.packets.TakeCardPacket;
import io.netty.buffer.ByteBuf;

public class TakeCardCodec implements EncodingCodec<TakeCardPacket>, DecodingCodec<TakeCardPacket>
{
    @Override
    public void encode(ByteBuf buf, TakeCardPacket packet)
    {
        //
    }

    @Override
    public TakeCardPacket decode(ByteBuf buf)
    {
        return new TakeCardPacket();
    }
}
