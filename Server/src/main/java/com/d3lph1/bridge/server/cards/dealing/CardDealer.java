package com.d3lph1.bridge.server.cards.dealing;

import com.d3lph1.bridge.server.Player;
import com.d3lph1.bridge.server.cards.Card;
import com.d3lph1.bridge.server.cards.Deck;

import java.util.List;

public interface CardDealer
{
    void deal(Deck deck, List<Player> players);

    Card firstCard(Deck deck);
}
