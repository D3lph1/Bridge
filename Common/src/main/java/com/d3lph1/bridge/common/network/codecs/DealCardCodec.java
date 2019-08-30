package com.d3lph1.bridge.common.network.codecs;

import com.d3lph1.bridge.common.network.io.DecodingCodec;
import com.d3lph1.bridge.common.network.io.EncodingCodec;
import com.d3lph1.bridge.common.network.packets.DealCardPacket;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

public class DealCardCodec implements EncodingCodec<DealCardPacket>, DecodingCodec<DealCardPacket>
{
    @Override
    public void encode(ByteBuf buf, DealCardPacket packet)
    {
        buf.writeInt(packet.getCardSignature().length());
        buf.writeCharSequence(packet.getCardSignature(), StandardCharsets.UTF_8);
    }

    @Override
    public DealCardPacket decode(ByteBuf buf)
    {
        return new DealCardPacket((String) buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8));
    }
}
