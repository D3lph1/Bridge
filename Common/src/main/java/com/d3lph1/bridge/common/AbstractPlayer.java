package com.d3lph1.bridge.common;

public abstract class AbstractPlayer
{
    private String name;

    private int score;

    public AbstractPlayer(String name, int score)
    {
        this.name = name;
        this.score = score;
    }

    public String getName()
    {
        return name;
    }

    public int getScore()
    {
        return score;
    }

    public void addScore(int val)
    {
        setScore(getScore() + val);
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof AbstractPlayer)) {
            return false;
        }

        return name.equals(((AbstractPlayer) obj).name);
    }

    @Override
    public String toString()
    {
        return String.format("Player(name=\"%s\", score=%d)", getName(), getScore());
    }
}
