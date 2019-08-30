package com.d3lph1.bridge.common.network.codecs;

import com.d3lph1.bridge.common.network.io.DecodingCodec;
import com.d3lph1.bridge.common.network.io.EncodingCodec;
import com.d3lph1.bridge.common.network.packets.GameStartedPacket;
import io.netty.buffer.ByteBuf;

public class GameStartedCodec implements EncodingCodec<GameStartedPacket>, DecodingCodec<GameStartedPacket>
{
    @Override
    public void encode(ByteBuf buf, GameStartedPacket packet)
    {
        //
    }

    @Override
    public GameStartedPacket decode(ByteBuf buf)
    {
        return new GameStartedPacket();
    }
}
