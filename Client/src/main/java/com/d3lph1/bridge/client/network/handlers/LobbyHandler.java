package com.d3lph1.bridge.client.network.handlers;

import com.d3lph1.bridge.client.Client;
import com.d3lph1.bridge.client.Player;
import com.d3lph1.bridge.common.network.PacketHandler;
import com.d3lph1.bridge.common.network.packets.LobbyPacket;

public class LobbyHandler implements PacketHandler<LobbyPacket>
{
    private Client client;

    public LobbyHandler(Client client)
    {
        this.client = client;
    }

    @Override
    public void handle(LobbyPacket packet)
    {
        if (packet.isAvailable()) {
            for (LobbyPacket.Player player : packet.getPlayers()) {
                client.getLobby().addPlayer(new Player(
                        player.getName(),
                        player.getScore(),
                        player.getNumCards()
                ));
            }

//            client.getHand().addCard(Card.D3);
//            client.getHand().addCard(Card.C6);
//            client.getHand().addCard(Card.HA);
//            client.getHand().addCard(Card.SK);
//            client.getHand().addCard(Card.CK);
//            client.getHand().addCard(Card.DK);
//            client.getHand().addCard(Card.HK);
//            client.getHand().addCard(Card.CQ);
//            client.getHand().addCard(Card.DQ);
//            client.getHand().addCard(Card.HQ);
//            client.getHand().addCard(Card.SQ);
//
//            client.getHand().addCard(Card.C4);
//            client.getHand().addCard(Card.D4);
//            client.getHand().addCard(Card.H4);
//            client.getHand().addCard(Card.S4);
//
//            client.getHand().addCard(Card.CA);
//            client.getHand().addCard(Card.DA);
//            client.getHand().addCard(Card.SA);
//
//            client.getHand().addCard(Card.CJ);
//            client.getHand().addCard(Card.DJ);
//            client.getHand().addCard(Card.HJ);
//            client.getHand().addCard(Card.SJ);
//
//            client.getHand().addCard(Card.C5);
//            client.getHand().addCard(Card.D5);
//            client.getHand().addCard(Card.H5);
//            client.getHand().addCard(Card.S5);
//
//            /*
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            client.getHand().removeCard(Card.C6);
//            */
//
//            client.getHistory().addCard(Card.CA);
//            client.getHistory().addCard(Card.D5);
//            client.getHistory().addCard(Card.S4);
        }
    }
}
