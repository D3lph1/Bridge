package com.d3lph1.bridge.common.network.codecs;

import com.d3lph1.bridge.common.network.io.DecodingCodec;
import com.d3lph1.bridge.common.network.io.EncodingCodec;
import com.d3lph1.bridge.common.network.packets.MovePacket;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

public class MoveCodec implements EncodingCodec<MovePacket>, DecodingCodec<MovePacket>
{
    @Override
    public void encode(ByteBuf buf, MovePacket packet)
    {
        buf.writeInt(packet.getCardsSignatures().length);
        for (String signature : packet.getCardsSignatures()) {
            buf.writeInt(signature.length());
            buf.writeCharSequence(signature, StandardCharsets.UTF_8);
        }
        buf.writeBoolean(packet.isTryBridge());
    }

    @Override
    public MovePacket decode(ByteBuf buf)
    {
        int size = buf.readInt();
        String[] signatures = new String[size];
        for (int i = 0; i < size; i++) {
            signatures[i] = (String) buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8);
        }

        return new MovePacket(signatures, buf.readBoolean());
    }
}
