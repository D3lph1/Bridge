package com.d3lph1.bridge.client.network;

import com.d3lph1.bridge.client.Client;
import com.d3lph1.bridge.client.network.pipeline.ChannelHandler;
import com.d3lph1.bridge.client.network.pipeline.PacketDecoder;
import com.d3lph1.bridge.client.network.pipeline.PacketEncoder;
import io.netty.channel.socket.SocketChannel;

public class ChannelInitializer extends io.netty.channel.ChannelInitializer<SocketChannel>
{
    private Client client;

    public ChannelInitializer(Client client)
    {
        this.client = client;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception
    {
        ch.pipeline()
                .addLast(new PacketEncoder(Map.HANDSHAKE))
                .addLast(new PacketDecoder(Map.HANDSHAKE))
                .addLast(new ChannelHandler(client));
    }
}
