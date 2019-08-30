package com.d3lph1.bridge.common.network.codecs;

import com.d3lph1.bridge.common.network.io.DecodingCodec;
import com.d3lph1.bridge.common.network.io.EncodingCodec;
import com.d3lph1.bridge.common.network.packets.LobbyPacket;
import io.netty.buffer.ByteBuf;

public class LobbyCodec extends AbstractLobbyCodec implements EncodingCodec<LobbyPacket>, DecodingCodec<LobbyPacket>
{
    @Override
    public LobbyPacket decode(ByteBuf buf)
    {
        return new LobbyPacket(
                buf.readBoolean(),
                readPlayersFromBuffer(buf)
        );
    }

    @Override
    public void encode(ByteBuf buf, LobbyPacket packet)
    {
        buf.writeBoolean(packet.isAvailable());
        writePlayersInBuffer(buf, packet.getPlayers());
    }
}
