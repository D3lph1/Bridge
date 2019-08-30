package com.d3lph1.bridge.common.network.packets;

import com.d3lph1.bridge.common.network.Packet;

import java.util.Arrays;

public class LobbyPacket implements Packet
{
    private final boolean available;

    private final Player[] players;

    public LobbyPacket(boolean available, Player[] players)
    {
        this.available = available;
        this.players = players;
    }

    public boolean isAvailable()
    {
        return available;
    }

    public Player[] getPlayers()
    {
        return players;
    }

    @Override
    public String toString()
    {
        return String.format(
                "%s(available=%s, players=%s)",
                getClass().getSimpleName(),
                Boolean.toString(available),
                Arrays.toString(players)
        );
    }

    public static class Player
    {
        private final String name;

        private final int score;

        private final int numCards;

        public Player(String name, int score, int numCards)
        {
            this.name = name;
            this.score = score;
            this.numCards = numCards;
        }


        public String getName()
        {
            return name;
        }

        public int getScore()
        {
            return score;
        }

        public int getNumCards()
        {
            return numCards;
        }

        @Override
        public String toString()
        {
            return String.format("{name=\"%s\", score=%d, numCards=%d}", name, score, numCards);
        }
    }
}
