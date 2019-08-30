package com.d3lph1.bridge.client.network;

import com.d3lph1.bridge.client.network.handlers.*;
import com.d3lph1.bridge.common.network.DecodingMapping;
import com.d3lph1.bridge.common.network.EncodingMapping;
import com.d3lph1.bridge.common.network.MappingRegistry;
import com.d3lph1.bridge.common.network.codecs.*;
import com.d3lph1.bridge.common.network.packets.*;

public enum  Map
{
    HANDSHAKE {
        {
            TO_SERVER.add(new EncodingMapping<>(0x00, HandshakePacket.class, HandshakeCodec.class));
        }
    },

    LOBBY {
        {
            TO_CLIENT.add(new DecodingMapping<>(0x01, LobbyPacket.class, LobbyCodec.class, LobbyHandler.class));
            TO_SERVER.add(new EncodingMapping<>(0x02, DisconnectPacket.class, DisconnectCodec.class));
            TO_CLIENT.add(new DecodingMapping<>(0x03, UpdateLobbyPacket.class, UpdateLobbyCodec.class, UpdateLobbyHandler.class));
            TO_CLIENT.add(new DecodingMapping<>(0x04, MessagePacket.class, MessageCodec.class, MessageHandler.class));
            TO_CLIENT.add(new DecodingMapping<>(0x05, DealCardPacket.class, DealCardCodec.class, DealCardHandler.class));
            TO_CLIENT.add(new DecodingMapping<>(0x06, TurnPacket.class, TurnCodec.class, TurnHandler.class));
            TO_CLIENT.add(new DecodingMapping<>(0x07, MovePacket.class, MoveCodec.class, MoveHandler.class));
            TO_CLIENT.add(new DecodingMapping<>(0x08, GameStartedPacket.class, GameStartedCodec.class, GameStartedHandler.class));
            TO_SERVER.add(new EncodingMapping<>(0x09, MovePacket.class, MoveCodec.class));
            TO_CLIENT.add(new DecodingMapping<>(0x10, MoveResultPacket.class, MoveResultCodec.class, MoveResultHandler.class));
            TO_CLIENT.add(new DecodingMapping<>(0x11, DeckSizePacket.class, DeckSizeCodec.class, DeckSizeHandler.class));
            TO_SERVER.add(new EncodingMapping<>(0x12, TakeCardPacket.class, TakeCardCodec.class));
            TO_CLIENT.add(new DecodingMapping<>(0x13, HandSizePacket.class, HandSizeCodec.class, HandSizeHandler.class));
            TO_CLIENT.add(new DecodingMapping<>(0x14, EndGamePacket.class, EndGameCodec.class, EndGameHandler.class));
            TO_CLIENT.add(new DecodingMapping<>(0x15, UpdateScorePacket.class, UpdateScoreCodec.class, UpdateScoreHandler.class));
        }
    };

    public final MappingRegistry TO_SERVER = new MappingRegistry();
    public final MappingRegistry TO_CLIENT = new MappingRegistry();
}
