package com.d3lph1.bridge.common.network.codecs;

import com.d3lph1.bridge.common.network.io.DecodingCodec;
import com.d3lph1.bridge.common.network.io.EncodingCodec;
import com.d3lph1.bridge.common.network.packets.MessagePacket;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

public class MessageCodec implements EncodingCodec<MessagePacket>, DecodingCodec<MessagePacket>
{
    @Override
    public MessagePacket decode(ByteBuf buf)
    {
        return new MessagePacket(
                (String) buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8),
                (String) buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8)
        );
    }

    @Override
    public void encode(ByteBuf buf, MessagePacket packet)
    {
        buf.writeInt(packet.getContent().length());
        buf.writeCharSequence(packet.getContent(), StandardCharsets.UTF_8);

        buf.writeInt(packet.getSource().length());
        buf.writeCharSequence(packet.getSource(), StandardCharsets.UTF_8);
    }
}
