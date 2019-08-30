package com.d3lph1.bridge.client.network;

import com.d3lph1.bridge.client.Client;
import com.d3lph1.bridge.common.network.Packet;
import com.d3lph1.bridge.common.network.packets.HandshakePacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Connection
{
    private EventLoopGroup group;

    private Channel channel;

    public Connection(Client client, String ip, int port)
    {
        group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap().group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer(client));

            channel = bootstrap.connect(ip, port).sync().channel();
            send(new HandshakePacket(client.getPlayer().getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(Packet packet)
    {
        channel.writeAndFlush(packet);
    }

    public void shutdown()
    {
        group.shutdownGracefully();
    }

    public Channel getChannel()
    {
        return channel;
    }
}
