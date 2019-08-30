package com.d3lph1.bridge.common.network.codecs;

import com.d3lph1.bridge.common.network.io.DecodingCodec;
import com.d3lph1.bridge.common.network.io.EncodingCodec;
import com.d3lph1.bridge.common.network.packets.EndGamePacket;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

public class EndGameCodec implements EncodingCodec<EndGamePacket>, DecodingCodec<EndGamePacket>
{
    @Override
    public void encode(ByteBuf buf, EndGamePacket packet)
    {
        EndGamePacket.Player[] players = packet.getPlayers();
        int playersLength = players.length;
        buf.writeInt(playersLength);
        for (int i = 0; i < playersLength; i++) {
            EndGamePacket.Player player = players[i];

            buf.writeInt(player.getName().length());
            buf.writeCharSequence(player.getName(), StandardCharsets.UTF_8);
            buf.writeInt(player.getScore());

            EndGamePacket.Player.Card[] cards = player.getCards();
            int cardsLength = cards.length;
            buf.writeInt(cardsLength);
            for (int j = 0; j < cardsLength; j++) {
                EndGamePacket.Player.Card card = cards[j];

                buf.writeInt(card.getSignature().length());
                buf.writeCharSequence(card.getSignature(), StandardCharsets.UTF_8);
                buf.writeInt(card.getCost());
            }
        }
    }

    @Override
    public EndGamePacket decode(ByteBuf buf)
    {
        int playersLength = buf.readInt();
        EndGamePacket.Player[] players = new EndGamePacket.Player[playersLength];
        for (int i = 0; i < playersLength; i++) {
            String playerName = (String) buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8);
            int playerScore = buf.readInt();

            int cardsLength = buf.readInt();
            EndGamePacket.Player.Card[] cards = new EndGamePacket.Player.Card[cardsLength];
            for (int j = 0; j < cardsLength; j++) {
                EndGamePacket.Player.Card card = new EndGamePacket.Player.Card(
                        (String) buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8),
                        buf.readInt()
                );
                cards[j] = card;
            }

            players[i] = new EndGamePacket.Player(playerName, playerScore, cards);
        }

        return new EndGamePacket(players);
    }
}
