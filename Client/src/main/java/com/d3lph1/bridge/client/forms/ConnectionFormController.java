package com.d3lph1.bridge.client.forms;

import com.d3lph1.bridge.client.Client;
import com.d3lph1.bridge.client.Player;
import com.d3lph1.bridge.client.network.Connection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;

public class ConnectionFormController
{
    @FXML
    private ImageView image;

    @FXML
    private TextField name;

    @FXML
    private TextField ip;

    @FXML
    private TextField port;

    @FXML
    private Button connectBtn;

    @FXML
    public void initialize()
    {
        image.setImage(new Image(getClass().getResourceAsStream("/menu.jpg")));
    }

    @FXML
    public void onConnectClicked(ActionEvent event) throws IOException
    {
        connectBtn.setDisable(true);
        connectBtn.setText("Connecting...");

        Pair<FXMLLoader, Stage> pair = FormUtil.constructMainFormStageAndGetWithLoader();
        MainFormController mainFormController = pair.getKey().getController();
        Client client = new Client(new Player(name.getText()), mainFormController);
        client.setConnection(new Connection(client, ip.getText(), Integer.valueOf(port.getText())));
        mainFormController.setClient(client);

        pair.getValue().setOnCloseRequest(e -> {
            try {
                Stage stage = FormUtil.constructConnectionFormStage();
                stage.show();
                stage.setOnCloseRequest(e1 -> {
                    Platform.exit();
                    System.exit(0);
                });
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            client.disconnect();
        });

        pair.getValue().show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
