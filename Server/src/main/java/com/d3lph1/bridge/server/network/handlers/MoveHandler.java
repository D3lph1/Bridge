package com.d3lph1.bridge.server.network.handlers;

import com.d3lph1.bridge.common.network.PacketHandler;
import com.d3lph1.bridge.common.network.packets.MessagePacket;
import com.d3lph1.bridge.common.network.packets.MovePacket;
import com.d3lph1.bridge.common.network.packets.MoveResultPacket;
import com.d3lph1.bridge.server.cards.Card;
import com.d3lph1.bridge.server.game.Game;
import com.d3lph1.bridge.server.game.ex.BridgeDoesNotAllowedException;
import com.d3lph1.bridge.server.game.ex.InvalidCardSetException;
import com.d3lph1.bridge.server.game.ex.SkippingTurnWithoutTakingCardException;
import com.d3lph1.bridge.server.network.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MoveHandler implements PacketHandler<MovePacket>
{
    private Session session;

    public MoveHandler(Session session)
    {
        this.session = session;
    }

    @Override
    public void handle(MovePacket packet)
    {
        List<Card> cards = new ArrayList<>();
        for (String signature : packet.getCardsSignatures()) {
            Optional<Card> mbCard = Card.fromSignature(signature);
            if (mbCard.isPresent()) {
                cards.add(mbCard.get());
            } else {
                throw new RuntimeException("Invalid card signature: " + signature);
            }
        }

        Optional<Game> mbGame = session.getServer().getGame();
        if (!mbGame.isPresent()) {
            throw new IllegalStateException("This action allowed only in the game");
        }

        try {
            if (!mbGame.get().acceptMove(session.getPlayer(), cards)) {
                session.send(new MoveResultPacket(MoveResultPacket.Value.INVALID_CARD_COMBINATION));
                session.send(new MessagePacket("Invalid card combination.", "SERVER"));
            }
        } catch (SkippingTurnWithoutTakingCardException e) {
            session.send(new MessagePacket(e.getMessage(), "SERVER"));
        } catch (InvalidCardSetException e) {
            session.send(new MoveResultPacket(MoveResultPacket.Value.INVALID_CARD_SET));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (packet.isTryBridge()) {
                mbGame.get().bridge();
            }
        } catch (BridgeDoesNotAllowedException e) {
            session.send(new MessagePacket(e.getMessage(), "SERVER"));
        }
    }
}
