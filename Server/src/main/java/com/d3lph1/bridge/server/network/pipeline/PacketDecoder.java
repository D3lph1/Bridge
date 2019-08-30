package com.d3lph1.bridge.server.network.pipeline;

import com.d3lph1.bridge.common.network.MappingRegistry;
import com.d3lph1.bridge.common.network.Packet;
import com.d3lph1.bridge.common.network.packets.HandshakePacket;
import com.d3lph1.bridge.common.network.pipeline.AbstractPacketDecoder;
import com.d3lph1.bridge.server.network.Map;
import io.netty.channel.ChannelHandlerContext;

public class PacketDecoder extends AbstractPacketDecoder<Map>
{
    public PacketDecoder(Map state)
    {
        super(state);
    }

    @Override
    protected MappingRegistry getInitMappingRegistry()
    {
        return state.TO_SERVER;
    }

    @Override
    protected void changeStateIfNeed(ChannelHandlerContext ctx, Packet packet)
    {
        if (packet instanceof HandshakePacket) {
            changeState(ctx, Map.LOBBY);
        }
    }

    public void changeState(ChannelHandlerContext ctx, Map newState)
    {
        ctx.pipeline().get(PacketDecoder.class).setState(newState);
        ctx.pipeline().get(PacketEncoder.class).setState(newState);
    }
}
