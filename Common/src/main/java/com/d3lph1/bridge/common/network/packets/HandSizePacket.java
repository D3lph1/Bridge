package com.d3lph1.bridge.common.network.packets;

import com.d3lph1.bridge.common.network.Packet;

import java.util.Arrays;

public class HandSizePacket implements Packet
{
    private final PlayerNameHandSizePair[] pairs;

    public HandSizePacket(PlayerNameHandSizePair[] pairs)
    {
        this.pairs = pairs;
    }

    public PlayerNameHandSizePair[] getPairs()
    {
        return pairs;
    }

    @Override
    public String toString()
    {
        return String.format(
                "%s(pairs=%s)",
                getClass().getSimpleName(),
                Arrays.toString(pairs)
        );
    }

    public static class PlayerNameHandSizePair
    {
        private final String playerName;

        private final int handSize;

        public PlayerNameHandSizePair(String playerName, int handSize)
        {
            this.playerName = playerName;
            this.handSize = handSize;
        }

        public String getPlayerName()
        {
            return playerName;
        }

        public int getHandSize()
        {
            return handSize;
        }

        @Override
        public String toString()
        {
            return String.format("{playerName=\"%s\", handSize=%d}", getPlayerName(), getHandSize());
        }
    }
}
