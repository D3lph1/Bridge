package com.d3lph1.bridge.client.network.pipeline;

import com.d3lph1.bridge.client.Client;
import com.d3lph1.bridge.common.network.MappedPacket;
import com.d3lph1.bridge.common.network.PacketHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ChannelHandler extends SimpleChannelInboundHandler<MappedPacket>
{
    private Client client;

    public ChannelHandler(Client client)
    {
        this.client = client;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void channelRead0(ChannelHandlerContext ctx, MappedPacket packet) throws Exception
    {
        for (Class<? extends PacketHandler> handlerClass : packet.getMapping().getHandlers()) {
            PacketHandler handler = handlerClass.getConstructor(new Class[] {Client.class}).newInstance(client);
            handler.handle(packet.getPacket());
        }
    }
}
