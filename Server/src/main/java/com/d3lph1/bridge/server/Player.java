package com.d3lph1.bridge.server;

import com.d3lph1.bridge.common.AbstractPlayer;
import com.d3lph1.bridge.common.network.packets.DealCardPacket;
import com.d3lph1.bridge.server.cards.Card;
import com.d3lph1.bridge.server.network.Session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Player extends AbstractPlayer
{
    private Session session;

    private List<Card> cards = new ArrayList<>();

    private boolean tookCard = false;

    private boolean skipTurn = false;

    public Player(Session session, String name, int score)
    {
        super(name, score);
        this.session = session;
    }

    public Session getSession()
    {
        return session;
    }

    public List<Card> getCards()
    {
        return new ArrayList<>(cards);
    }

    public void addCard(Card card)
    {
        cards.add(card);
        session.send(new DealCardPacket(card.toString()));
    }

    public void addCards(Collection<Card> cards)
    {
        for (Card card: cards) {
            addCard(card);
        }
    }

    public void removeCard(Card card)
    {
        this.cards.remove(card);
    }

    public void removeCards(Collection<Card> cards)
    {
        this.cards.removeAll(cards);
    }

    public void removeAllCards()
    {
        cards.clear();
    }

    public boolean isTookCard()
    {
        return tookCard;
    }

    public void setTookCard(boolean tookCard)
    {
        this.tookCard = tookCard;
    }

    public boolean isSkipTurn()
    {
        return skipTurn;
    }

    public void setSkipTurn(boolean skipTurn)
    {
        this.skipTurn = skipTurn;
    }
}
