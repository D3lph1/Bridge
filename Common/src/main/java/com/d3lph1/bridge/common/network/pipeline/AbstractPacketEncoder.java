package com.d3lph1.bridge.common.network.pipeline;

import com.d3lph1.bridge.common.network.EncodingMapping;
import com.d3lph1.bridge.common.network.Mapping;
import com.d3lph1.bridge.common.network.MappingRegistry;
import com.d3lph1.bridge.common.network.Packet;
import com.d3lph1.bridge.common.network.io.EncodingCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public abstract class AbstractPacketEncoder<M> extends MessageToByteEncoder<Packet>
{
    protected M state;

    public AbstractPacketEncoder(M state)
    {
        this.state = state;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) throws Exception
    {
        MappingRegistry registry = getInitMappingRegistry();
        Class<? extends Packet> packetClass = packet.getClass();

        Mapping mapping = registry.getMappingByPacketClass(packetClass);
        if (mapping == null) {
            throw new Exception("Mapping for packet " + packet.getClass().getName() + " not found");
        }

        EncodingCodec<Packet> codec = ((EncodingMapping<Packet>) mapping).getCodec().newInstance();
        out.writeInt(mapping.getPacketId());
        codec.encode(out, packet);

        System.out.println("Packet sent: " + packet);
        changeStateIfNeed(ctx, packet);
    }

    protected abstract MappingRegistry getInitMappingRegistry();

    protected void changeStateIfNeed(ChannelHandlerContext ctx, Packet packet)
    {
        //
    }

    public abstract void changeState(ChannelHandlerContext ctx, M newState);

    public void setState(M state)
    {
        this.state = state;
    }
}
