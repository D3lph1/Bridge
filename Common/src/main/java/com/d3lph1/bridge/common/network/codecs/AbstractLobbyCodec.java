package com.d3lph1.bridge.common.network.codecs;

import com.d3lph1.bridge.common.network.packets.LobbyPacket;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

abstract class AbstractLobbyCodec
{
    LobbyPacket.Player[] readPlayersFromBuffer(ByteBuf buf)
    {
        int numPlayers = buf.readInt();
        LobbyPacket.Player[] players = new LobbyPacket.Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            players[i] = new LobbyPacket.Player(
                    (String) buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8),
                    buf.readInt(),
                    buf.readInt()
            );
        }

        return players;
    }

    void writePlayersInBuffer(ByteBuf buf, LobbyPacket.Player[] players)
    {
        buf.writeInt(players.length);
        for (LobbyPacket.Player player : players) {
            buf.writeInt(player.getName().length());
            buf.writeCharSequence(player.getName(), StandardCharsets.UTF_8);
            buf.writeInt(player.getScore());
            buf.writeInt(player.getNumCards());
        }
    }
}
