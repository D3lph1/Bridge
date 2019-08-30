package com.d3lph1.bridge.server.network;

import com.d3lph1.bridge.common.network.DecodingMapping;
import com.d3lph1.bridge.common.network.EncodingMapping;
import com.d3lph1.bridge.common.network.MappingRegistry;
import com.d3lph1.bridge.common.network.codecs.*;
import com.d3lph1.bridge.common.network.packets.*;
import com.d3lph1.bridge.server.network.handlers.DisconnectHandler;
import com.d3lph1.bridge.server.network.handlers.HandshakeHandler;
import com.d3lph1.bridge.server.network.handlers.MoveHandler;
import com.d3lph1.bridge.server.network.handlers.TakeCardHandler;

public enum Map
{
    HANDSHAKE {
        {
            TO_SERVER.add(new DecodingMapping<>(0x00, HandshakePacket.class, HandshakeCodec.class, HandshakeHandler.class));
        }
    },

    LOBBY {
        {
            TO_CLIENT.add(new EncodingMapping<>(0x01, LobbyPacket.class, LobbyCodec.class));
            TO_SERVER.add(new DecodingMapping<>(0x02, DisconnectPacket.class, DisconnectCodec.class, DisconnectHandler.class));
            TO_CLIENT.add(new EncodingMapping<>(0x03, UpdateLobbyPacket.class, UpdateLobbyCodec.class));
            TO_CLIENT.add(new EncodingMapping<>(0x04, MessagePacket.class, MessageCodec.class));
            TO_CLIENT.add(new EncodingMapping<>(0x05, DealCardPacket.class, DealCardCodec.class));
            TO_CLIENT.add(new EncodingMapping<>(0x06, TurnPacket.class, TurnCodec.class));
            TO_CLIENT.add(new EncodingMapping<>(0x07, MovePacket.class, MoveCodec.class));
            TO_CLIENT.add(new EncodingMapping<>(0x08, GameStartedPacket.class, GameStartedCodec.class));
            TO_SERVER.add(new DecodingMapping<>(0x09, MovePacket.class, MoveCodec.class, MoveHandler.class));
            TO_CLIENT.add(new EncodingMapping<>(0x10, MoveResultPacket.class, MoveResultCodec.class));
            TO_CLIENT.add(new EncodingMapping<>(0x11, DeckSizePacket.class, DeckSizeCodec.class));
            TO_SERVER.add(new DecodingMapping<>(0x12, TakeCardPacket.class, TakeCardCodec.class, TakeCardHandler.class));
            TO_CLIENT.add(new EncodingMapping<>(0x13, HandSizePacket.class, HandSizeCodec.class));
            TO_CLIENT.add(new EncodingMapping<>(0x14, EndGamePacket.class, EndGameCodec.class));
            TO_CLIENT.add(new EncodingMapping<>(0x15, UpdateScorePacket.class, UpdateScoreCodec.class));
        }
    };

    public final MappingRegistry TO_SERVER = new MappingRegistry();
    public final MappingRegistry TO_CLIENT = new MappingRegistry();
}
