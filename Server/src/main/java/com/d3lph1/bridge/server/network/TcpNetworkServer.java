package com.d3lph1.bridge.server.network;

import com.d3lph1.bridge.server.Server;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TcpNetworkServer implements NetworkServer
{
    private Server server;

    private Channel channel;

    private EventLoopGroup bossGroup;

    private EventLoopGroup workerGroup;

    public TcpNetworkServer(Server server)
    {
        this.server = server;
    }

    public void start(int port)
    {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer(server))
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = b.bind(port).sync();
            channel = f.channel();

            channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shutdown();
        }
    }

    public void shutdown()
    {
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }

    public Channel getChannel()
    {
        return channel;
    }
}
