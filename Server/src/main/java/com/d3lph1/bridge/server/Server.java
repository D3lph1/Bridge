package com.d3lph1.bridge.server;

import com.d3lph1.bridge.server.game.Game;
import com.d3lph1.bridge.server.network.NetworkServer;
import com.d3lph1.bridge.server.network.SessionRegistry;
import com.d3lph1.bridge.server.network.TcpNetworkServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Server
{
    private Configuration configuration;

    private NetworkServer networkServer;

    private SessionRegistry sessionRegistry = new SessionRegistry();

    private ScheduledExecutorService scheduler;

    private Game game;

    public static final int TICK_TIME_MILLIS = 200;

    public Server(Configuration configuration)
    {
        this.configuration = configuration;
        networkServer = new TcpNetworkServer(this);
        scheduler = Executors.newScheduledThreadPool(3);
    }

    public void start()
    {
        new Thread(() -> networkServer.start(25565)).start();
        scheduler.scheduleAtFixedRate(this::tick, 0, TICK_TIME_MILLIS, TimeUnit.MILLISECONDS);
    }

    private void tick()
    {
        getSessionRegistry().removeDestroyed();
        getSessionRegistry().tick();
    }

    public Configuration getConfiguration()
    {
        return configuration;
    }

    public NetworkServer getNetworkServer()
    {
        return networkServer;
    }

    public SessionRegistry getSessionRegistry()
    {
        return sessionRegistry;
    }

    public List<Player> getPlayers()
    {
        List<Player> players = new ArrayList<>();
        getSessionRegistry()
                .all()
                .stream()
                .filter(session -> session.getPlayer() != null)
                .forEach(session -> players.add(session.getPlayer()));

        return players;
    }

    public void startGame()
    {
        game = new Game(this);
    }

    public Optional<Game> getGame()
    {
        return Optional.of(game);
    }
}
