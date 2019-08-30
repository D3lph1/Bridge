package com.d3lph1.bridge.client;

import com.d3lph1.bridge.client.cards.Card;
import com.d3lph1.bridge.client.forms.MainFormController;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class History
{
    private static final int CARD_SHIFT = 20;

    private MainFormController mainFormController;

    private List<List<Card>> moves = new ArrayList<>();

    private List<Card> currentMove = new ArrayList<>();

    public History(MainFormController mainFormController)
    {
        this.mainFormController = mainFormController;
    }

    public void addCard(Card card)
    {
        ImageView imageView = new ImageView(card.getImage());
        imageView.setId(card.toString());
        if (moves.size() == 0 && currentMove.size() == 0) {
            imageView.setX(0);
        } else {
            imageView.setX(CARD_SHIFT * visibleSize());
        }
        Platform.runLater(() -> mainFormController.getHistoryPane().getChildren().add(imageView));

        currentMove.add(card);
    }

    public void addCards(Card... cards)
    {
        for (Card card : cards) {
            addCard(card);
        }
    }

    public void removeCard(Card card)
    {
        Platform.runLater(() -> {
            boolean needShift = false;
            for (Node node : mainFormController.getHistoryPane().getChildren()) {
                ImageView image = (ImageView) node;
                if (needShift) {
                    image.setX(image.getX() - CARD_SHIFT);
                }
                if (image.getId().equals(card.toString())) {
                    needShift = true;
                }
            }

            mainFormController.getHistoryPane().getChildren().removeIf(node -> node.getId().equals(card.toString()));
        });
        currentMove.remove(card);
    }

    public void commitMove()
    {
        List<Card> previousMove = moves.size() == 0 ? new ArrayList<>() : moves.get(moves.size() - 1);
        moves.add(new ArrayList<>(currentMove));
        currentMove.clear();
        Platform.runLater(() -> {
            int i = 0;
            Iterator<Node> node = mainFormController.getHistoryPane().getChildren().iterator();
            while (node.hasNext()) {
                ImageView image = (ImageView) node.next();
                String cardSignature = image.getId();
                Card card = Card.withDefaultImage(cardSignature);
                //TODO: Simplify this condition
                if (!((previousMove.contains(card) && previousMove.indexOf(card) == previousMove.size() - 1) || moves.get(moves.size() - 1).contains(card))) {
                    node.remove();
                } else {
                    image.setX(CARD_SHIFT * i);
                    i++;
                }
            }
        });
    }

    public List<Card> getCurrentMove()
    {
        return new ArrayList<>(currentMove);
    }

    public int visibleSize()
    {
        return (moves.size() == 0 ? 0 : moves.get(moves.size() - 1).size() + 1) + currentMove.size();
    }

    public void clear()
    {
        moves.clear();
        moves.add(new ArrayList<>());
        currentMove.clear();
        Platform.runLater(() -> mainFormController.getHistoryPane().getChildren().clear());
    }
}
