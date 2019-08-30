package com.d3lph1.bridge.server.game;

import com.d3lph1.bridge.server.cards.Card;

public interface Rule
{
    /**
     * The method checks previous and current card and detect business logic conflicts.
     * This method will call when will be handling first card from the processing
     * queue.
     *
     * @param previous The previous card with which checking. In this case it always
     *                 will last card from the history.
     * @param current The current card which is checked for conflicts. It will first
     *                card from the processing queue.
     * @param state Game state object to access to additional information about game.
     * @return true - if there are not conflicts, false - else.
     */
    boolean firstCheck(Card previous, Card current, State state);

    /**
     * The method checks previous and current card and detect business logic conflicts.
     *
     * @param previous The previous card with which checking.
     * @param current The current card which is checked for conflicts.
     * @param state Game state object to access to additional information about game.
     * @return true - if there are not conflicts, false - else.
     */
    boolean check(Card previous, Card current, State state);
}
