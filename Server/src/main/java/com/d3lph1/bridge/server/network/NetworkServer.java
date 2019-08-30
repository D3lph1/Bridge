package com.d3lph1.bridge.server.network;

import io.netty.channel.Channel;

public interface NetworkServer
{
    void start(int port);

    Channel getChannel();
}
