package com.d3lph1.bridge.client;

import com.d3lph1.bridge.client.cards.Deck;
import com.d3lph1.bridge.client.forms.MainFormController;
import com.d3lph1.bridge.client.network.Connection;
import com.d3lph1.bridge.common.network.packets.DisconnectPacket;

public class Client
{
    private final MainFormController mainFormController;

    private Connection connection;

    private final Player player;

    private final Lobby lobby;

    private final Hand hand;

    private final History history;

    private final Deck deck;

    private final Chat chat;

    public Client(Player player, MainFormController mainFormController)
    {
        this.mainFormController = mainFormController;
        this.player = player;
        lobby = new Lobby(mainFormController);
        history = new History(mainFormController);
        hand = new Hand(mainFormController, history);
        deck = new Deck(mainFormController);
        chat = new Chat(mainFormController);
    }

    public void myTurn()
    {
        enableBridge();
        lobby.myTurn();
        hand.myTurn();
    }

    public void opponentTurn()
    {
        disableBridge();
        lobby.opponentTurn();
        hand.opponentTurn();
    }

    public void disableBridge()
    {
        mainFormController.setTryBridge(false);
        mainFormController.getBridgeButton().setDisable(true);
    }

    public void enableBridge()
    {
        mainFormController.setTryBridge(false);
        mainFormController.getBridgeButton().setDisable(false);
    }

    public void disconnect()
    {
        getConnection().send(new DisconnectPacket());
    }

    public Connection getConnection()
    {
        return connection;
    }

    public void setConnection(Connection connection)
    {
        this.connection = connection;
    }

    public Player getPlayer()
    {
        return player;
    }

    public Lobby getLobby()
    {
        return lobby;
    }

    public Hand getHand()
    {
        return hand;
    }

    public History getHistory()
    {
        return history;
    }

    public Deck getDeck()
    {
        return deck;
    }

    public Chat getChat()
    {
        return chat;
    }
}
