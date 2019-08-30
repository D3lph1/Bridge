package com.d3lph1.bridge.server;

public class Main
{
    public static void main(String[] args)
    {
        Server server = new Server(new Configuration(2));
        server.start();
    }
}
