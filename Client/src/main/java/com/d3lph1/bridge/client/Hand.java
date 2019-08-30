package com.d3lph1.bridge.client;

import com.d3lph1.bridge.client.cards.Card;
import com.d3lph1.bridge.client.forms.MainFormController;
import com.d3lph1.bridge.common.cards.Rank;
import com.d3lph1.bridge.common.cards.Signature;
import com.d3lph1.bridge.common.cards.Suit;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Hand
{
    private static final int INIT_CARD_Y = 20;

    private static final int CARD_SHIFT = 50;

    private static final int HOVER_CARD_SHIFT = 30;

    private MainFormController mainFormController;

    private History history;

    private List<Card> cards = new ArrayList<>();

    public Hand(MainFormController mainFormController, History history)
    {
        this.mainFormController = mainFormController;
        this.history = history;
    }

    public void addCard(Card card)
    {
        ImageView imageView = new ImageView(card.getImage());
        imageView.setId(card.toString());
        imageView.getStyleClass().add("img");
        imageView.setX(CARD_SHIFT * (cards.size()));
        imageView.setY(INIT_CARD_Y);
        if (cards.size() != 0) {
            imageView.setOnMouseEntered(e -> imageView.setX(imageView.getX() - HOVER_CARD_SHIFT));
            imageView.setOnMouseExited(e -> imageView.setX(imageView.getX() + HOVER_CARD_SHIFT));
        }
        Platform.runLater(() -> mainFormController.getHandPane().getChildren().add(imageView));

        cards.add(card);
    }

    public void removeCard(Card card)
    {
        Platform.runLater(() -> {
            boolean needShift = false;
            for (Node node : mainFormController.getHandPane().getChildren()) {
                ImageView image = (ImageView) node;
                if (needShift) {
                    image.setX(image.getX() - CARD_SHIFT);
                }
                String[] peaces = image.getId().split(" ", 2);
                if (peaces.length == 1 && peaces[0].equals(card.toString())) {
                    needShift = true;
                } else if (peaces.length == 2 && peaces[1].equals(card.toString())) {
                    needShift = true;
                }
            }

            mainFormController.getHandPane().getChildren().removeIf(node -> {
                String[] peaces = node.getId().split(" ", 2);
                if (peaces.length == 1 && peaces[0].equals(card.toString())) {
                    return true;
                } else if (peaces.length == 2 && peaces[1].equals(card.toString())) {
                    return true;
                }

                return false;
            });

            // To prevent first card shift on hover action
            if (mainFormController.getHandPane().getChildren().size() > 0) {
                Node node = mainFormController.getHandPane().getChildren().get(0);
                if (node != null) {
                    ImageView image = (ImageView) node;
                    image.setOnMouseEntered(null);
                    image.setOnMouseExited(null);
                }
            }
        });
        cards.remove(card);
    }

    public void removeCards(List<Card> cards)
    {
        cards.forEach(this::removeCard);
    }

    public void clear()
    {
        Platform.runLater(() -> mainFormController.getHandPane().getChildren().clear());
        cards.clear();
    }

    public void myTurn()
    {
        Platform.runLater(() -> mainFormController.getHandPane().getChildren().forEach(children -> {
            ImageView imageView = (ImageView) children;
            imageView.setOnMouseClicked(e -> {
                Card card;
                if (imageView.getY() == 0) {
                    String[] peaces = imageView.getId().split(" ", 2);
                    if (peaces.length == 1) {
                        history.removeCard(Card.withDefaultImage(peaces[0]));
                    } else if (peaces.length == 2) {
                        history.removeCard(Card.withDefaultImage(peaces[1]));
                    }
                    imageView.setY(INIT_CARD_Y);
                } else {
                    String[] peaces = imageView.getId().split(" ", 2);
                    Pair<Rank, Suit> rankSuitPair = Signature.splitIntoRankAndSuit(peaces[0]);

                    if (rankSuitPair.getKey() == Rank.JACK) {
                        Optional<Suit> mbSuit = Optional.empty();
                        while (!mbSuit.isPresent()) {
                            mbSuit = openChooseSuitDialog();
                        }

                        card = Card.withDefaultImage(rankSuitPair.getKey(), mbSuit.get());
                        imageView.setId(peaces[0] + " " + card.toString());
                    } else {
                        card = Card.withDefaultImage(imageView.getId());
                    }
                    imageView.setY(0);
                    history.addCard(card);
                }
            });
        }));
    }

    public void opponentTurn()
    {
        Platform.runLater(() -> {
            mainFormController.getHandPane().getChildren().forEach(children -> {
                ImageView imageView = (ImageView) children;
                imageView.setOnMouseClicked(e -> {
                    imageView.setY(INIT_CARD_Y);
                });
            });
        });
    }

    private Optional<Suit> openChooseSuitDialog()
    {
        ChoiceDialog<Suit> dialog = new ChoiceDialog<>(Suit.CLOVERS, Suit.DIAMONDS, Suit.HEARTS, Suit.SPADES);
        dialog.setTitle("Choose suit");
        dialog.setHeaderText("Choose suit");

        return dialog.showAndWait();
    }
}
