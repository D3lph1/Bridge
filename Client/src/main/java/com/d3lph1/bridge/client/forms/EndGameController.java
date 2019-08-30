package com.d3lph1.bridge.client.forms;

import com.d3lph1.bridge.client.cards.Card;
import com.d3lph1.bridge.common.network.packets.EndGamePacket;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class EndGameController
{
    private static final int CARD_SHIFT = 60;

    private static final int INIT_CARD_Y = 10;

    @FXML
    private Label firstPlayerName;

    @FXML
    private Label secondPlayerName;

    @FXML
    private Pane firstPlayerPane;

    @FXML
    private Pane secondPlayerPane;

    public void setFirstPlayer(String playerName, int score, EndGamePacket.Player.Card[] cards)
    {
        firstPlayerName.setText(playerName + ": " + score);
        setCardsPaneContent(firstPlayerPane, cards);
    }

    public void setSecondPlayer(String playerName, int score, EndGamePacket.Player.Card[] cards)
    {
        secondPlayerName.setText(playerName + ": " + score);
        setCardsPaneContent(secondPlayerPane, cards);
    }

    private void setCardsPaneContent(Pane cardsPane, EndGamePacket.Player.Card[] cards)
    {
        int i = 0;
        for (EndGamePacket.Player.Card cardWithCost : cards) {
            Card card = Card.withDefaultImage(cardWithCost.getSignature());

            ImageView imageView = new ImageView(card.getImage());
            imageView.setId(card.toString());
            imageView.setX(CARD_SHIFT * i);
            imageView.setY(INIT_CARD_Y);
            Platform.runLater(() -> cardsPane.getChildren().add(imageView));

            Label costLabel = new Label(String.valueOf(cardWithCost.getCost()));
            costLabel.setLayoutX((imageView.getImage().getWidth() / 2) * (i + 1));
            costLabel.setLayoutY(imageView.getImage().getHeight() + 20);
            Platform.runLater(() -> cardsPane.getChildren().add(costLabel));

            i++;
        }
    }
}
