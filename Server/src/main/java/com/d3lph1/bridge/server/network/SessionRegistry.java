package com.d3lph1.bridge.server.network;

import com.d3lph1.bridge.common.network.Packet;

import java.util.Arrays;
import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class SessionRegistry
{
    private final Queue<Session> sessions = new ConcurrentLinkedDeque<>();

    public void tick()
    {
        sessions.forEach(Session::tick);
    }

    public void add(Session session)
    {
        if (sessions.contains(session)) {
            throw new RuntimeException("Registry already contains this session");
        }

        sessions.add(session);
    }

    public void broadcastToAll(Packet packet)
    {
        sessions.forEach(session -> session.send(packet));
    }

    public void broadcastToAllExcept(Packet packet, Session... exceptions)
    {
        broadcastToAllExcept(packet, Arrays.asList(exceptions));
    }

    public void broadcastToAllExcept(Packet packet, Collection<Session> exceptions)
    {
        sessions.stream()
                .filter(session -> !exceptions.contains(session))
                .forEach(session -> session.send(packet));
    }

    public void remove(Session session)
    {
        sessions.remove(session);
    }

    public void removeDestroyed()
    {
        sessions.removeIf(Session::isDestroyed);
    }

    public Queue<Session> all()
    {
        return sessions;
    }
}
