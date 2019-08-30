package com.d3lph1.bridge.common.network.codecs;

import com.d3lph1.bridge.common.network.io.DecodingCodec;
import com.d3lph1.bridge.common.network.io.EncodingCodec;
import com.d3lph1.bridge.common.network.packets.DisconnectPacket;
import io.netty.buffer.ByteBuf;

public class DisconnectCodec implements EncodingCodec<DisconnectPacket>, DecodingCodec<DisconnectPacket>
{
    @Override
    public DisconnectPacket decode(ByteBuf buf)
    {
        return new DisconnectPacket();
    }

    @Override
    public void encode(ByteBuf buf, DisconnectPacket packet)
    {
        //
    }
}
