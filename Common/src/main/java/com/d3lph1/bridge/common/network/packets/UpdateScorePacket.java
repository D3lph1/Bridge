package com.d3lph1.bridge.common.network.packets;

import com.d3lph1.bridge.common.network.Packet;

import java.util.Arrays;

public class UpdateScorePacket implements Packet
{
    private final PlayerNameScorePair[] pairs;

    public UpdateScorePacket(PlayerNameScorePair[] pairs)
    {
        this.pairs = pairs;
    }

    public PlayerNameScorePair[] getPairs()
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

    public static class PlayerNameScorePair
    {
        private final String playerName;

        private final int score;

        public PlayerNameScorePair(String playerName, int score)
        {
            this.playerName = playerName;
            this.score = score;
        }

        public String getPlayerName()
        {
            return playerName;
        }

        public int getScore()
        {
            return score;
        }

        @Override
        public String toString()
        {
            return String.format("{playerName=\"%s\", score=%d}", getPlayerName(), getScore());
        }
    }
}
