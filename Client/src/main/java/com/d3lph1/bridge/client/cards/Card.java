package com.d3lph1.bridge.client.cards;

import com.d3lph1.bridge.common.cards.AbstractCard;
import com.d3lph1.bridge.common.cards.Rank;
import com.d3lph1.bridge.common.cards.Signature;
import com.d3lph1.bridge.common.cards.Suit;
import javafx.scene.image.Image;
import javafx.util.Pair;

public class Card extends AbstractCard
{
    public static final Card C2 = withDefaultImage(Rank.TWO, Suit.CLOVERS);
    public static final Card D2 = withDefaultImage(Rank.TWO, Suit.DIAMONDS);
    public static final Card H2 = withDefaultImage(Rank.TWO, Suit.HEARTS);
    public static final Card S2 = withDefaultImage(Rank.TWO, Suit.SPADES);

    public static final Card C3 = withDefaultImage(Rank.THREE, Suit.CLOVERS);
    public static final Card D3 = withDefaultImage(Rank.THREE, Suit.DIAMONDS);
    public static final Card H3 = withDefaultImage(Rank.THREE, Suit.HEARTS);
    public static final Card S3 = withDefaultImage(Rank.THREE, Suit.SPADES);

    public static final Card C4 = withDefaultImage(Rank.FOUR, Suit.CLOVERS);
    public static final Card D4 = withDefaultImage(Rank.FOUR, Suit.DIAMONDS);
    public static final Card H4 = withDefaultImage(Rank.FOUR, Suit.HEARTS);
    public static final Card S4 = withDefaultImage(Rank.FOUR, Suit.SPADES);

    public static final Card C5 = withDefaultImage(Rank.FIVE, Suit.CLOVERS);
    public static final Card D5 = withDefaultImage(Rank.FIVE, Suit.DIAMONDS);
    public static final Card H5 = withDefaultImage(Rank.FIVE, Suit.HEARTS);
    public static final Card S5 = withDefaultImage(Rank.FIVE, Suit.SPADES);

    public static final Card C6 = withDefaultImage(Rank.SIX, Suit.CLOVERS);
    public static final Card D6 = withDefaultImage(Rank.SIX, Suit.DIAMONDS);
    public static final Card H6 = withDefaultImage(Rank.SIX, Suit.HEARTS);
    public static final Card S6 = withDefaultImage(Rank.SIX, Suit.SPADES);

    public static final Card C7 = withDefaultImage(Rank.SEVEN, Suit.CLOVERS);
    public static final Card D7 = withDefaultImage(Rank.SEVEN, Suit.DIAMONDS);
    public static final Card H7 = withDefaultImage(Rank.SEVEN, Suit.HEARTS);
    public static final Card S7 = withDefaultImage(Rank.SEVEN, Suit.SPADES);

    public static final Card C8 = withDefaultImage(Rank.EIGHT, Suit.CLOVERS);
    public static final Card D8 = withDefaultImage(Rank.EIGHT, Suit.DIAMONDS);
    public static final Card H8 = withDefaultImage(Rank.EIGHT, Suit.HEARTS);
    public static final Card S8 = withDefaultImage(Rank.EIGHT, Suit.SPADES);

    public static final Card C9 = withDefaultImage(Rank.NINE, Suit.CLOVERS);
    public static final Card D9 = withDefaultImage(Rank.NINE, Suit.DIAMONDS);
    public static final Card H9 = withDefaultImage(Rank.NINE, Suit.HEARTS);
    public static final Card S9 = withDefaultImage(Rank.NINE, Suit.SPADES);

    public static final Card C10 = withDefaultImage(Rank.TEN, Suit.CLOVERS);
    public static final Card D10 = withDefaultImage(Rank.TEN, Suit.DIAMONDS);
    public static final Card H10 = withDefaultImage(Rank.TEN, Suit.HEARTS);
    public static final Card S10 = withDefaultImage(Rank.TEN, Suit.SPADES);

    public static final Card CJ = withDefaultImage(Rank.JACK, Suit.CLOVERS);
    public static final Card DJ = withDefaultImage(Rank.JACK, Suit.DIAMONDS);
    public static final Card HJ = withDefaultImage(Rank.JACK, Suit.HEARTS);
    public static final Card SJ = withDefaultImage(Rank.JACK, Suit.SPADES);

    public static final Card CQ = withDefaultImage(Rank.QUEEN, Suit.CLOVERS);
    public static final Card DQ = withDefaultImage(Rank.QUEEN, Suit.DIAMONDS);
    public static final Card HQ = withDefaultImage(Rank.QUEEN, Suit.HEARTS);
    public static final Card SQ = withDefaultImage(Rank.QUEEN, Suit.SPADES);

    public static final Card CK = withDefaultImage(Rank.KING, Suit.CLOVERS);
    public static final Card DK = withDefaultImage(Rank.KING, Suit.DIAMONDS);
    public static final Card HK = withDefaultImage(Rank.KING, Suit.HEARTS);
    public static final Card SK = withDefaultImage(Rank.KING, Suit.SPADES);

    public static final Card CA = withDefaultImage(Rank.ACE, Suit.CLOVERS);
    public static final Card DA = withDefaultImage(Rank.ACE, Suit.DIAMONDS);
    public static final Card HA = withDefaultImage(Rank.ACE, Suit.HEARTS);
    public static final Card SA = withDefaultImage(Rank.ACE, Suit.SPADES);

    private final Image image;

    public Card(Rank rank, Suit suit, Image image)
    {
        super(rank, suit);
        this.image = image;
    }

    public static Card withDefaultImage(Rank rank, Suit suit)
    {
        return new Card(rank, suit, new Image(String.format("/cards/%s%s.png", rank.getSymbol(), suit.getSymbol())));
    }

    public static Card withDefaultImage(String signature)
    {
        Pair<Rank, Suit> rankSuitPair = Signature.splitIntoRankAndSuit(signature);

        return withDefaultImage(rankSuitPair.getKey(), rankSuitPair.getValue());
    }

    public Image getImage()
    {
        return image;
    }
}
