package com.d3lph1.bridge.common.network.codecs;

import com.d3lph1.bridge.common.network.io.DecodingCodec;
import com.d3lph1.bridge.common.network.io.EncodingCodec;
import com.d3lph1.bridge.common.network.packets.DeckSizePacket;
import io.netty.buffer.ByteBuf;

public class DeckSizeCodec implements EncodingCodec<DeckSizePacket>, DecodingCodec<DeckSizePacket>
{
    @Override
    public void encode(ByteBuf buf, DeckSizePacket packet)
    {
        buf.writeInt(packet.getSize());
    }

    @Override
    public DeckSizePacket decode(ByteBuf buf)
    {
        return new DeckSizePacket(buf.readInt());
    }
}
