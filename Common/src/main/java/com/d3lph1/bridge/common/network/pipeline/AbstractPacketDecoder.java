package com.d3lph1.bridge.common.network.pipeline;

import com.d3lph1.bridge.common.network.DecodingMapping;
import com.d3lph1.bridge.common.network.MappedPacket;
import com.d3lph1.bridge.common.network.MappingRegistry;
import com.d3lph1.bridge.common.network.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DecoderException;

import java.util.List;

public abstract class AbstractPacketDecoder<M> extends ByteToMessageDecoder
{
    protected M state;

    public AbstractPacketDecoder(M state)
    {
        this.state = state;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception
    {
        int id = -1;
        try {
            id = buf.readInt();
        } catch (DecoderException e) {
            buf.skipBytes(buf.readableBytes());
        }
        MappingRegistry registry = getInitMappingRegistry();
        DecodingMapping<Packet> mapping = null;
        Packet packet = null;

        if (registry.has(id)) {
            mapping = (DecodingMapping<Packet>) registry.get(id);
            try {
                packet = mapping.getCodec().newInstance().decode(buf);
            } catch (DecoderException e) {
                e.printStackTrace();
                return;
            }

            System.out.println("Packet received: " + packet);
        } else {
            System.out.println(id);
            System.out.println("Unexpected bytes sequence. Skipping...");
            buf.skipBytes(buf.readableBytes());
        }

        if (mapping != null && packet != null) {
            out.add(new MappedPacket(mapping, packet));

            changeStateIfNeed(ctx, packet);
        }
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
