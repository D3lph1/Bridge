package com.d3lph1.bridge.common.network.codecs;

import com.d3lph1.bridge.common.network.io.DecodingCodec;
import com.d3lph1.bridge.common.network.io.EncodingCodec;
import com.d3lph1.bridge.common.network.packets.HandshakePacket;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

public class HandshakeCodec implements EncodingCodec<HandshakePacket>, DecodingCodec<HandshakePacket>
{
    @Override
    public void encode(ByteBuf buf, HandshakePacket packet)
    {
        buf.writeInt(packet.getName().length());
        buf.writeCharSequence(packet.getName(), StandardCharsets.UTF_8);
    }

    @Override
    public HandshakePacket decode(ByteBuf buf)
    {
        return new HandshakePacket((String) buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8));
    }
}
