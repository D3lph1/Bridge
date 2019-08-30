package com.d3lph1.bridge.common.network.packets;

import com.d3lph1.bridge.common.network.Packet;

import java.util.Arrays;

public class UpdateLobbyPacket implements Packet
{
    private LobbyPacket.Player[] players;

    public UpdateLobbyPacket(LobbyPacket.Player[] players)
    {
        this.players = players;
    }

    public LobbyPacket.Player[] getPlayers()
    {
        return players;
    }

    @Override
    public String toString()
    {
        return String.format(
                "%s(players=%s)",
                getClass().getSimpleName(),
                Arrays.toString(players)
        );
    }
}
