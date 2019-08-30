package com.d3lph1.bridge.server.network.pipeline;

import com.d3lph1.bridge.common.network.MappingRegistry;
import com.d3lph1.bridge.common.network.pipeline.AbstractPacketEncoder;
import com.d3lph1.bridge.server.network.Map;
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
        return state.TO_CLIENT;
    }

    public void changeState(ChannelHandlerContext ctx, Map newState)
    {
        ctx.pipeline().get(PacketDecoder.class).setState(newState);
        ctx.pipeline().get(PacketEncoder.class).setState(newState);
    }
}
