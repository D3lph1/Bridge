package com.d3lph1.bridge.server.network.pipeline;

import com.d3lph1.bridge.common.network.MappedPacket;
import com.d3lph1.bridge.server.Server;
import com.d3lph1.bridge.server.network.Session;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ChannelHandler extends SimpleChannelInboundHandler<MappedPacket>
{
    private Session session;

    private Server server;

    public ChannelHandler(Server server)
    {
        this.server = server;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        System.out.println("Client connected");
        session = new Session(server, ctx.channel());
        server.getSessionRegistry().add(session);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MappedPacket packet) throws Exception
    {
        if (ctx.channel().isOpen()) {
            session.acceptPacket(packet);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception
    {
        System.out.println("Client disconnected");
        session.destroy();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        session.destroy();
        System.out.println(cause);
        ctx.channel().close();
    }
}
