package com.d3lph1.bridge.client.forms;

import com.d3lph1.bridge.client.Client;
import com.d3lph1.bridge.client.Player;
import com.d3lph1.bridge.client.cards.Card;
import com.d3lph1.bridge.common.cards.AbstractCard;
import com.d3lph1.bridge.common.network.packets.MovePacket;
import com.d3lph1.bridge.common.network.packets.TakeCardPacket;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MainFormController
{
    @FXML
    private TableView<Player> playersTableView;

    @FXML
    private Pane handPane;

    @FXML
    private ImageView deckImageView;

    @FXML
    private Label deckSizeLabel;

    @FXML
    private Pane historyPane;

    @FXML
    private Button exitButton;

    @FXML
    private Button bridgeButton;

    @FXML
    private Button confirmButton;

    @FXML
    private Button takeACardButton;

    @FXML
    private TextArea chatTextArea;

    private Client client;

    private boolean tryBridge = false;

    @FXML
    public void initialize()
    {
        TableColumn<Player, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(105);
        nameColumn.setMaxWidth(105);
        nameColumn.setSortable(false);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Player, Integer> scoreColumn = new TableColumn<>("Score");
        scoreColumn.setMaxWidth(60);
        scoreColumn.setSortType(TableColumn.SortType.ASCENDING);
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        TableColumn<Player, Integer> cardsNumColumn = new TableColumn<>("Cards");
        cardsNumColumn.setMaxWidth(60);
        cardsNumColumn.setSortable(false);
        cardsNumColumn.setCellValueFactory(new PropertyValueFactory<>("handSize"));

        getPlayersTableView().getColumns().add(nameColumn);
        getPlayersTableView().getColumns().add(scoreColumn);
        getPlayersTableView().getColumns().add(cardsNumColumn);

        deckImageView.setImage(new Image(getClass().getResourceAsStream("/shirts/green_back.png")));
        deckSizeLabel.setText("0");
    }

    @FXML
    public void onExitClicked(ActionEvent event) throws IOException
    {
        exitButton.setDisable(true);
        client.disconnect();
        Stage stage = FormUtil.constructConnectionFormStage();
        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    public void onBridgeClicked()
    {
        tryBridge = true;
        bridgeButton.setDisable(true);
    }

    @FXML
    public void onConfirmClicked()
    {
        List<Card> cardsToSend = client.getHistory().getCurrentMove();
        String[] signatures = AbstractCard.signatures(cardsToSend);
        client.getConnection().send(new MovePacket(signatures, tryBridge));
    }

    @FXML
    public void onTakeACardClicked()
    {
        client.getConnection().send(new TakeCardPacket());
        takeACardButton.setDisable(true);
    }

    public void setClient(Client client)
    {
        this.client = client;
    }

    public TableView<Player> getPlayersTableView()
    {
        return playersTableView;
    }

    public Pane getHandPane()
    {
        return handPane;
    }

    public Label getDeckSizeLabel()
    {
        return deckSizeLabel;
    }

    public Pane getHistoryPane()
    {
        return historyPane;
    }

    public TextArea getChatTextArea()
    {
        return chatTextArea;
    }

    public Button getConfirmButton()
    {
        return confirmButton;
    }

    public Button getTakeACardButton()
    {
        return takeACardButton;
    }

    public Button getBridgeButton()
    {
        return bridgeButton;
    }

    public void setTryBridge(boolean tryBridge)
    {
        this.tryBridge = tryBridge;
    }
}
