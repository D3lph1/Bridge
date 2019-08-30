package com.d3lph1.bridge.client.network.handlers;

import com.d3lph1.bridge.client.Client;
import com.d3lph1.bridge.client.forms.EndGameController;
import com.d3lph1.bridge.client.forms.FormUtil;
import com.d3lph1.bridge.common.network.PacketHandler;
import com.d3lph1.bridge.common.network.packets.EndGamePacket;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;

public class EndGameHandler implements PacketHandler<EndGamePacket>
{
    private final Client client;

    public EndGameHandler(Client client)
    {
        this.client = client;
    }

    @Override
    public void handle(EndGamePacket packet)
    {
        Platform.runLater(() -> {
            try {
                Pair<FXMLLoader, Stage> pair = FormUtil.constructEndGameFormStage();
                EndGameController endGameController = pair.getKey().getController();

                EndGamePacket.Player[] players = packet.getPlayers();
                EndGamePacket.Player firstPlayer = players[0];
                EndGamePacket.Player secondPlayer = players[1];

                endGameController.setFirstPlayer(firstPlayer.getName(), firstPlayer.getScore(), firstPlayer.getCards());
                endGameController.setSecondPlayer(secondPlayer.getName(), secondPlayer.getScore(), secondPlayer.getCards());

                pair.getValue().show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
