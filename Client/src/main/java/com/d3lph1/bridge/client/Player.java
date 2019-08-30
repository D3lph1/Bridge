package com.d3lph1.bridge.client;

import com.d3lph1.bridge.common.AbstractPlayer;

public class Player extends AbstractPlayer
{
    private int handSize;

    public Player(String name, int score, int handSize)
    {
        super(name, score);
        this.handSize = handSize;
    }

    public Player(String name)
    {
        this(name, 0, 0);
    }

    public int getHandSize()
    {
        return handSize;
    }

    public void setHandSize(int handSize)
    {
        this.handSize = handSize;
    }
}
