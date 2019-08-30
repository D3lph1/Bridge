package com.d3lph1.bridge.client.network.pipeline;

import com.d3lph1.bridge.client.network.Map;
import com.d3lph1.bridge.common.network.MappingRegistry;
import com.d3lph1.bridge.common.network.Packet;
import com.d3lph1.bridge.common.network.packets.HandshakePacket;
import com.d3lph1.bridge.common.network.pipeline.AbstractPacketEncoder;
import io.netty.channel.ChannelHandlerContext;

public class PacketEncoder extends AbstractPacketEncoder<Map>
{
    public PacketEncoder(Map state)
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

    public void setState(Map state)
    {
        this.state = state;
    }
}
