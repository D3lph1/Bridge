package com.d3lph1.bridge.common.network.codecs;

import com.d3lph1.bridge.common.network.io.DecodingCodec;
import com.d3lph1.bridge.common.network.io.EncodingCodec;
import com.d3lph1.bridge.common.network.packets.MoveResultPacket;
import io.netty.buffer.ByteBuf;

import java.util.Optional;

public class MoveResultCodec implements EncodingCodec<MoveResultPacket>, DecodingCodec<MoveResultPacket>
{
    @Override
    public void encode(ByteBuf buf, MoveResultPacket packet)
    {
        buf.writeInt(packet.getValue().getCode());
    }

    @Override
    public MoveResultPacket decode(ByteBuf buf)
    {
        int code = buf.readInt();
        Optional<MoveResultPacket.Value> mbValue = MoveResultPacket.Value.fromCode(code);
        if (!mbValue.isPresent()) {
            throw new RuntimeException("Illegal value of MoveResultPacket code");
        }

        return new MoveResultPacket(mbValue.get());
    }
}
