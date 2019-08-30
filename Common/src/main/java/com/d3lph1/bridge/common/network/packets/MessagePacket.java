package com.d3lph1.bridge.common.network.packets;

import com.d3lph1.bridge.common.network.Packet;

public class MessagePacket implements Packet
{
    private final String content;

    // TODO: Add class to represent source
    private final String source;

    public MessagePacket(String content, String source)
    {
        this.content = content;
        this.source = source;
    }

    public String getContent()
    {
        return content;
    }

    public String getSource()
    {
        return source;
    }

    @Override
    public String toString()
    {
        return String.format(
                "%s(content=\"%s\", source=\"%s\")",
                getClass().getSimpleName(),
                getContent(),
                getSource()
        );
    }
}
