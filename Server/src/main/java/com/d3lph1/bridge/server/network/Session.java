package com.d3lph1.bridge.server.network;

import com.d3lph1.bridge.common.network.MappedPacket;
import com.d3lph1.bridge.common.network.Packet;
import com.d3lph1.bridge.common.network.PacketHandler;
import com.d3lph1.bridge.server.Player;
import com.d3lph1.bridge.server.Server;
import io.netty.channel.Channel;

import java.lang.reflect.InvocationTargetException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Session
{
    private Server server;

    private Channel channel;

    private Player player;

    private final Queue<MappedPacket> pendingToHandle = new ConcurrentLinkedDeque<>();

    private boolean destroyed = false;

    public Session(Server server, Channel channel)
    {
        this.server = server;
        this.channel = channel;
    }

    public void acceptPacket(MappedPacket packet)
    {
        pendingToHandle.add(packet);
    }

    public void send(Packet packet)
    {
        channel.writeAndFlush(packet);
    }

    public void tick()
    {
        MappedPacket mappedPacket;

        while ((mappedPacket = pendingToHandle.poll()) != null) {
            handle(mappedPacket);
        }
    }

    @SuppressWarnings("unchecked")
    private void handle(MappedPacket packet)
    {
        System.out.println("Handling packet " + packet);
        try {
            for (Class<? extends PacketHandler> handlerClass : packet.getMapping().getHandlers()) {
                PacketHandler handler = handlerClass.getConstructor(new Class[] {Session.class}).newInstance(this);
                handler.handle(packet.getPacket());
            }
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void destroy()
    {
        destroyed = true;
    }

    public boolean isDestroyed()
    {
        return destroyed;
    }

    public Server getServer()
    {
        return server;
    }

    public Channel getChannel()
    {
        return channel;
    }

    public void setPlayer(Player player)
    {
        if (this.player != null) {
            throw new RuntimeException("Player already set");
        }

        this.player = player;
    }

    // TODO: Edit this method to return Optional<Player>
    public Player getPlayer()
    {
        return player;
    }
}
