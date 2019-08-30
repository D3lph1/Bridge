package com.d3lph1.bridge.server.network;

import com.d3lph1.bridge.server.Server;
import com.d3lph1.bridge.server.network.pipeline.ChannelHandler;
import com.d3lph1.bridge.server.network.pipeline.PacketDecoder;
import com.d3lph1.bridge.server.network.pipeline.PacketEncoder;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public class ChannelInitializer extends io.netty.channel.ChannelInitializer<SocketChannel>
{
    private Server server;

    public ChannelInitializer(Server server)
    {
        this.server = server;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception
    {
        ch
                .pipeline()
                .addLast(new PacketEncoder(Map.HANDSHAKE))
                .addLast(new PacketDecoder(Map.HANDSHAKE))
                .addLast(new ChannelHandler(server))
                .addLast(new IdleStateHandler(20, 20, 0));
    }
}
