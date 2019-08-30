package com.d3lph1.bridge.common.network.codecs;

import com.d3lph1.bridge.common.network.io.DecodingCodec;
import com.d3lph1.bridge.common.network.io.EncodingCodec;
import com.d3lph1.bridge.common.network.packets.HandSizePacket;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

public class HandSizeCodec implements EncodingCodec<HandSizePacket>, DecodingCodec<HandSizePacket>
{
    @Override
    public void encode(ByteBuf buf, HandSizePacket packet)
    {
        HandSizePacket.PlayerNameHandSizePair[] pairs = packet.getPairs();
        buf.writeInt(pairs.length);
        for (HandSizePacket.PlayerNameHandSizePair pair : pairs) {
            buf.writeInt(pair.getPlayerName().length());
            buf.writeCharSequence(pair.getPlayerName(), StandardCharsets.UTF_8);
            buf.writeInt(pair.getHandSize());
        }
    }

    @Override
    public HandSizePacket decode(ByteBuf buf)
    {
        int pairsLength = buf.readInt();
        HandSizePacket.PlayerNameHandSizePair[] pairs = new HandSizePacket.PlayerNameHandSizePair[pairsLength];
        for (int i = 0; i < pairsLength; i++) {
            String playerName = (String) buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8);
            int handSize = buf.readInt();
            pairs[i] = new HandSizePacket.PlayerNameHandSizePair(playerName, handSize);
        }

        return new HandSizePacket(pairs);
    }
}
