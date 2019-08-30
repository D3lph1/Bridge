package com.d3lph1.bridge.client.network.pipeline;

import com.d3lph1.bridge.client.network.Map;
import com.d3lph1.bridge.common.network.MappingRegistry;
import com.d3lph1.bridge.common.network.pipeline.AbstractPacketDecoder;
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
        return state.TO_CLIENT;
    }

    public void changeState(ChannelHandlerContext ctx, Map newState)
    {
        ctx.pipeline().get(PacketDecoder.class).setState(newState);
        ctx.pipeline().get(PacketEncoder.class).setState(newState);
    }
}
