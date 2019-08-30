package com.d3lph1.bridge.common.network.packets;

import com.d3lph1.bridge.common.network.Packet;

import java.util.Arrays;

public class EndGamePacket implements Packet
{
    private final Player[] players;

    public EndGamePacket(Player[] players)
    {
        this.players = players;
    }

    public Player[] getPlayers()
    {
        return players;
    }

    @Override
    public String toString()
    {
        return String.format(
                "%s(players=%s)",
                getClass().getSimpleName(),
                Arrays.toString(getPlayers())
        );
    }

    public static class Player
    {
        private final String name;

        private final int score;

        private final Card[] cards;

        public Player(String name, int score, Card[] cards)
        {
            this.name = name;
            this.score = score;
            this.cards = cards;
        }

        public String getName()
        {
            return name;
        }

        public int getScore()
        {
            return score;
        }

        public Card[] getCards()
        {
            return cards;
        }

        @Override
        public String toString()
        {
            return String.format(
                    "{name=\"%s\", score=%d, cards=%s}",
                    getName(),
                    getScore(),
                    Arrays.toString(cards)
            );
        }

        public static class Card
        {
            private final String signature;

            private final int cost;

            public Card(String signature, int cost)
            {
                this.signature = signature;
                this.cost = cost;
            }

            public String getSignature()
            {
                return signature;
            }

            public int getCost()
            {
                return cost;
            }

            @Override
            public String toString()
            {
                return String.format("{signature=\"%s\", cost=%d}", getSignature(), getCost());
            }
        }
    }
}
