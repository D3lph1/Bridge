package com.d3lph1.bridge.client.cards;

import com.d3lph1.bridge.client.forms.MainFormController;
import javafx.application.Platform;

public class Deck
{
    private final MainFormController mainFormController;

    public Deck(MainFormController mainFormController)
    {
        this.mainFormController = mainFormController;
    }

    public void setSize(int deckSize)
    {
        Platform.runLater(() -> mainFormController.getDeckSizeLabel().setText(String.valueOf(deckSize)));
    }
}
