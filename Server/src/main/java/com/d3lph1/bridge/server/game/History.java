package com.d3lph1.bridge.server.game;

import com.d3lph1.bridge.server.cards.Card;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class History
{
    private List<List<Card>> moves = new ArrayList<>();

    private List<Card> currentMove = new ArrayList<>();

    public void addCard(Card card)
    {
        currentMove.add(card);
    }

    public void addCards(Collection<Card> cards)
    {
        currentMove.addAll(cards);
    }

    public void commitMove()
    {
        if (currentMove.size() == 0) {
            throw new IllegalStateException("Can not commit empty move");
        }
        moves.add(new ArrayList<>(currentMove));
        currentMove.clear();
    }

    public Optional<Card> getLast()
    {
        if (currentMove.size() > 0) {
            return Optional.of(currentMove.get(currentMove.size() - 1));
        }

        if (moves.size() > 0) {
            List<Card> lastList = moves.get(moves.size() - 1);

            return Optional.of(lastList.get(lastList.size() - 1));
        }

        return Optional.empty();
    }

    public List<Card> getLast(int numCards)
    {
        if (numCards < 1) {
            throw new IllegalArgumentException("Argument num cards must be greater or equals 1");
        }

        List<Card> cards = new ArrayList<>();
        for (int i = currentMove.size() - 1; i >= 0; i--) {
            cards.add(currentMove.get(i));
            numCards--;
            if (numCards == 0) {
                return cards;
            }
        }

        for (int i = moves.size() - 1; i >= 0; i--) {
            for (int j = moves.get(i).size() - 1; j >= 0; j--) {
                cards.add(moves.get(i).get(j));
                numCards--;
                if (numCards == 0) {
                    return cards;
                }
            }
        }

        return cards;
    }

    public void clear()
    {
        moves.clear();
        currentMove.clear();
    }
}
