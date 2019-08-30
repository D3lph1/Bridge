package com.d3lph1.bridge.common.network.codecs;

import com.d3lph1.bridge.common.network.io.DecodingCodec;
import com.d3lph1.bridge.common.network.io.EncodingCodec;
import com.d3lph1.bridge.common.network.packets.UpdateScorePacket;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

public class UpdateScoreCodec implements EncodingCodec<UpdateScorePacket>, DecodingCodec<UpdateScorePacket>
{
    @Override
    public void encode(ByteBuf buf, UpdateScorePacket packet)
    {
        UpdateScorePacket.PlayerNameScorePair[] pairs = packet.getPairs();
        buf.writeInt(pairs.length);
        for (UpdateScorePacket.PlayerNameScorePair pair : pairs) {
            buf.writeInt(pair.getPlayerName().length());
            buf.writeCharSequence(pair.getPlayerName(), StandardCharsets.UTF_8);
            buf.writeInt(pair.getScore());
        }
    }

    @Override
    public UpdateScorePacket decode(ByteBuf buf)
    {
        int pairsLength = buf.readInt();
        UpdateScorePacket.PlayerNameScorePair[] pairs = new UpdateScorePacket.PlayerNameScorePair[pairsLength];
        for (int i = 0; i < pairsLength; i++) {
            String playerName = (String) buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8);
            int score = buf.readInt();
            pairs[i] = new UpdateScorePacket.PlayerNameScorePair(playerName, score);
        }

        return new UpdateScorePacket(pairs);
    }
}
