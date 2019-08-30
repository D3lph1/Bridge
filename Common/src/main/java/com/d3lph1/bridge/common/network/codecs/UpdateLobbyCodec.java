package com.d3lph1.bridge.common.network.codecs;

import com.d3lph1.bridge.common.network.io.DecodingCodec;
import com.d3lph1.bridge.common.network.io.EncodingCodec;
import com.d3lph1.bridge.common.network.packets.UpdateLobbyPacket;
import io.netty.buffer.ByteBuf;

public class UpdateLobbyCodec extends AbstractLobbyCodec implements EncodingCodec<UpdateLobbyPacket>, DecodingCodec<UpdateLobbyPacket>
{
    @Override
    public UpdateLobbyPacket decode(ByteBuf buf)
    {
        return new UpdateLobbyPacket(readPlayersFromBuffer(buf));
    }

    @Override
    public void encode(ByteBuf buf, UpdateLobbyPacket packet)
    {
        writePlayersInBuffer(buf, packet.getPlayers());
    }
}
