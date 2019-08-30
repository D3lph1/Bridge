package com.d3lph1.bridge.client;

import com.d3lph1.bridge.client.forms.MainFormController;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Lobby
{
    private MainFormController mainFormController;

    private List<Player> players = new ArrayList<>();

    public Lobby(MainFormController mainFormController)
    {
        this.mainFormController = mainFormController;
    }

    public List<Player> getPlayers()
    {
        return new ArrayList<>(players);
    }

    public void addPlayer(Player player)
    {
        players.add(player);
        Platform.runLater(() -> mainFormController.getPlayersTableView().getItems().add(player));
    }

    public void addPlayers(Collection<Player> players)
    {
        for (Player player : players) {
            addPlayer(player);
        }
    }

    public void removePlayer(Player player)
    {
        players.remove(player);
        Platform.runLater(() -> mainFormController.getPlayersTableView().getItems().remove(player));
    }

    public void removeAllPlayers()
    {
        players.clear();
        Platform.runLater(() -> mainFormController.getPlayersTableView().getItems().clear());
    }

    public void myTurn()
    {
        Platform.runLater(() -> {
            mainFormController.getConfirmButton().setDisable(false);
            mainFormController.getConfirmButton().setText("Confirm");
            mainFormController.getTakeACardButton().setDisable(false);
        });
    }

    public void opponentTurn()
    {
        Platform.runLater(() -> {
            mainFormController.getConfirmButton().setDisable(true);
            mainFormController.getConfirmButton().setText("Your's opponent turn");
            mainFormController.getTakeACardButton().setDisable(true);
        });
    }

    public void setHandSize(Player player, int handSize)
    {
        player.setHandSize(handSize);
        List<Player> tmp = new ArrayList<>(players);
        removeAllPlayers();
        addPlayers(tmp);
    }

    public void setScore(Player player, int score)
    {
        player.setScore(score);
        List<Player> tmp = new ArrayList<>(players);
        removeAllPlayers();
        addPlayers(tmp);
    }
}
