package com.d3lph1.bridge.server.cards;

import com.d3lph1.bridge.common.cards.AbstractCard;
import com.d3lph1.bridge.common.cards.Rank;
import com.d3lph1.bridge.common.cards.Signature;
import com.d3lph1.bridge.common.cards.Suit;
import com.d3lph1.bridge.server.game.Behavior;
import com.d3lph1.bridge.server.game.Rule;
import com.d3lph1.bridge.server.game.behaviors.RequiresCoverBehavior;
import com.d3lph1.bridge.server.game.behaviors.SkipTurnBehavior;
import com.d3lph1.bridge.server.game.behaviors.TakeCardsBehavior;
import com.d3lph1.bridge.server.game.rules.AnySuitRule;
import com.d3lph1.bridge.server.game.rules.RegularRule;
import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Card extends AbstractCard
{
    public static final Card C2 = new Card(Rank.TWO, Suit.CLOVERS);
    public static final Card D2 = new Card(Rank.TWO, Suit.DIAMONDS);
    public static final Card H2 = new Card(Rank.TWO, Suit.HEARTS);
    public static final Card S2 = new Card(Rank.TWO, Suit.SPADES);

    public static final Card C3 = new Card(Rank.THREE, Suit.CLOVERS);
    public static final Card D3 = new Card(Rank.THREE, Suit.DIAMONDS);
    public static final Card H3 = new Card(Rank.THREE, Suit.HEARTS);
    public static final Card S3 = new Card(Rank.THREE, Suit.SPADES);

    public static final Card C4 = new Card(Rank.FOUR, Suit.CLOVERS);
    public static final Card D4 = new Card(Rank.FOUR, Suit.DIAMONDS);
    public static final Card H4 = new Card(Rank.FOUR, Suit.HEARTS);
    public static final Card S4 = new Card(Rank.FOUR, Suit.SPADES);

    public static final Card C5 = new Card(Rank.FIVE, Suit.CLOVERS);
    public static final Card D5 = new Card(Rank.FIVE, Suit.DIAMONDS);
    public static final Card H5 = new Card(Rank.FIVE, Suit.HEARTS);
    public static final Card S5 = new Card(Rank.FIVE, Suit.SPADES);

    public static final Card C6 = new Card(Rank.SIX, Suit.CLOVERS);
    public static final Card D6 = new Card(Rank.SIX, Suit.DIAMONDS);
    public static final Card H6 = new Card(Rank.SIX, Suit.HEARTS);
    public static final Card S6 = new Card(Rank.SIX, Suit.SPADES);

    public static final Card C7 = new Card(Rank.SEVEN, Suit.CLOVERS);
    public static final Card D7 = new Card(Rank.SEVEN, Suit.DIAMONDS);
    public static final Card H7 = new Card(Rank.SEVEN, Suit.HEARTS);
    public static final Card S7 = new Card(Rank.SEVEN, Suit.SPADES);

    public static final Card C8 = new Card(Rank.EIGHT, Suit.CLOVERS);
    public static final Card D8 = new Card(Rank.EIGHT, Suit.DIAMONDS);
    public static final Card H8 = new Card(Rank.EIGHT, Suit.HEARTS);
    public static final Card S8 = new Card(Rank.EIGHT, Suit.SPADES);

    public static final Card C9 = new Card(Rank.NINE, Suit.CLOVERS);
    public static final Card D9 = new Card(Rank.NINE, Suit.DIAMONDS);
    public static final Card H9 = new Card(Rank.NINE, Suit.HEARTS);
    public static final Card S9 = new Card(Rank.NINE, Suit.SPADES);

    public static final Card C10 = new Card(Rank.TEN, Suit.CLOVERS, 10);
    public static final Card D10 = new Card(Rank.TEN, Suit.DIAMONDS, 10);
    public static final Card H10 = new Card(Rank.TEN, Suit.HEARTS, 10);
    public static final Card S10 = new Card(Rank.TEN, Suit.SPADES, 10);


    public static final Card CJ = new Card(Rank.JACK, Suit.CLOVERS, 25);
    public static final Card DJ = new Card(Rank.JACK, Suit.DIAMONDS, 25);
    public static final Card HJ = new Card(Rank.JACK, Suit.HEARTS, 25);
    public static final Card SJ = new Card(Rank.JACK, Suit.SPADES, 25);

    public static final Card CQ = new Card(Rank.QUEEN, Suit.CLOVERS, 10);
    public static final Card DQ = new Card(Rank.QUEEN, Suit.DIAMONDS, 10);
    public static final Card HQ = new Card(Rank.QUEEN, Suit.HEARTS, 10);
    public static final Card SQ = new Card(Rank.QUEEN, Suit.SPADES, 25);

    public static final Card CK = new Card(Rank.KING, Suit.CLOVERS, 10);
    public static final Card DK = new Card(Rank.KING, Suit.DIAMONDS, 10);
    public static final Card HK = new Card(Rank.KING, Suit.HEARTS, 10);
    public static final Card SK = new Card(Rank.KING, Suit.SPADES, 10);

    public static final Card CA = new Card(Rank.ACE, Suit.CLOVERS, 15);
    public static final Card DA = new Card(Rank.ACE, Suit.DIAMONDS, 15);
    public static final Card HA = new Card(Rank.ACE, Suit.HEARTS, 15);
    public static final Card SA = new Card(Rank.ACE, Suit.SPADES, 15);

    static {
        C2.setRules(new RegularRule());
        D2.setRules(new RegularRule());
        H2.setRules(new RegularRule());
        S2.setRules(new RegularRule());

        C3.setRules(new RegularRule());
        D3.setRules(new RegularRule());
        H3.setRules(new RegularRule());
        S3.setRules(new RegularRule());

        C4.setRules(new RegularRule());
        D4.setRules(new RegularRule());
        H4.setRules(new RegularRule());
        S4.setRules(new RegularRule());

        C5.setRules(new RegularRule());
        D5.setRules(new RegularRule());
        H5.setRules(new RegularRule());
        S5.setRules(new RegularRule());

        C6.setRules(new RegularRule())
                .setBehaviors(new RequiresCoverBehavior());
        D6.setRules(new RegularRule())
                .setBehaviors(new RequiresCoverBehavior());
        H6.setRules(new RegularRule())
                .setBehaviors(new RequiresCoverBehavior());
        S6.setRules(new RegularRule())
                .setBehaviors(new RequiresCoverBehavior());

        C7.setRules(new RegularRule())
                .setBehaviors(new TakeCardsBehavior(1));
        D7.setRules(new RegularRule())
                .setBehaviors(new TakeCardsBehavior(1));
        H7.setRules(new RegularRule())
                .setBehaviors(new TakeCardsBehavior(1));
        S7.setRules(new RegularRule())
                .setBehaviors(new TakeCardsBehavior(1));

        C8.setRules(new RegularRule())
                .setBehaviors(new TakeCardsBehavior(2), new SkipTurnBehavior());
        D8.setRules(new RegularRule())
                .setBehaviors(new TakeCardsBehavior(2), new SkipTurnBehavior());
        H8.setRules(new RegularRule())
                .setBehaviors(new TakeCardsBehavior(2), new SkipTurnBehavior());
        S8.setRules(new RegularRule())
                .setBehaviors(new TakeCardsBehavior(2), new SkipTurnBehavior());

        C9.setRules(new RegularRule());
        D9.setRules(new RegularRule());
        H9.setRules(new RegularRule());
        S9.setRules(new RegularRule());

        C10.setRules(new RegularRule());
        D10.setRules(new RegularRule());
        H10.setRules(new RegularRule());
        S10.setRules(new RegularRule());

        CJ.setRules(new AnySuitRule());
        DJ.setRules(new AnySuitRule());
        HJ.setRules(new AnySuitRule());
        SJ.setRules(new AnySuitRule());

        CQ.setRules(new RegularRule());
        DQ.setRules(new RegularRule());
        HQ.setRules(new RegularRule());
        SQ.setRules(new RegularRule())
                .setBehaviors(new TakeCardsBehavior(5));

        CK.setRules(new RegularRule());
        DK.setRules(new RegularRule());
        HK.setRules(new RegularRule());
        SK.setRules(new RegularRule());

        CA.setRules(new RegularRule())
                .setBehaviors(new SkipTurnBehavior());
        DA.setRules(new RegularRule())
                .setBehaviors(new SkipTurnBehavior());
        HA.setRules(new RegularRule())
                .setBehaviors(new SkipTurnBehavior());
        SA.setRules(new RegularRule())
                .setBehaviors(new SkipTurnBehavior());
    }

    public static final List<Card> ALL = new ArrayList<Card>()
    {{
        add(C2);
        add(D2);
        add(H2);
        add(S2);

        add(C3);
        add(D3);
        add(H3);
        add(S3);

        add(C4);
        add(D4);
        add(H4);
        add(S4);

        add(C5);
        add(D5);
        add(H5);
        add(S5);

        add(C6);
        add(D6);
        add(H6);
        add(S6);

        add(C7);
        add(D7);
        add(H7);
        add(S7);

        add(C8);
        add(D8);
        add(H8);
        add(S8);

        add(C9);
        add(D9);
        add(H9);
        add(S9);

        add(C10);
        add(D10);
        add(H10);
        add(S10);

        add(CJ);
        add(DJ);
        add(HJ);
        add(SJ);

        add(CQ);
        add(DQ);
        add(HQ);
        add(SQ);

        add(CK);
        add(DK);
        add(HK);
        add(SK);

        add(CA);
        add(DA);
        add(HA);
        add(SA);
    }};

    public static final List<Card> REDUCED = new ArrayList<Card>()
    {{
        add(C6);
        add(D6);
        add(H6);
        add(S6);

        add(C7);
        add(D7);
        add(H7);
        add(S7);

        add(C8);
        add(D8);
        add(H8);
        add(S8);

        add(C9);
        add(D9);
        add(H9);
        add(S9);

        add(C10);
        add(D10);
        add(H10);
        add(S10);

        add(CJ);
        add(DJ);
        add(HJ);
        add(SJ);

        add(CQ);
        add(DQ);
        add(HQ);
        add(SQ);

        add(CK);
        add(DK);
        add(HK);
        add(SK);

        add(CA);
        add(DA);
        add(HA);
        add(SA);
    }};

    private int cost;

    private Set<Rule> rules = new HashSet<>();

    private Set<Behavior> behaviors = new HashSet<>();

    public Card(Rank rank, Suit suit)
    {
        this(rank, suit, 0);
    }

    public Card(Rank rank, Suit suit, int cost)
    {
        super(rank, suit);
        this.cost = cost;
    }

    public static Optional<Card> fromSignature(String signature)
    {
        Pair<Rank, Suit> rankSuitPair = Signature.splitIntoRankAndSuit(signature);
        for (Card card : ALL) {
            if (card.getRank() == rankSuitPair.getKey() && card.getSuit() == rankSuitPair.getValue()) {
                return Optional.of(card);
            }
        }

        return Optional.empty();
    }

    public int getCost()
    {
        return cost;
    }

    public Set<Rule> getRules()
    {
        return new HashSet<>(rules);
    }

    public Card setRules(Rule... rules)
    {
        return setRules(Stream.of(rules).collect(Collectors.toSet()));
    }

    public Card setRules(Set<Rule> rules)
    {
        this.rules = rules;

        return this;
    }

    public Set<Behavior> getBehaviors()
    {
        return new HashSet<>(behaviors);
    }

    public Card setBehaviors(Behavior... behaviors)
    {
        return setBehaviors(Stream.of(behaviors).collect(Collectors.toSet()));
    }

    public Card setBehaviors(Set<Behavior> behaviors)
    {
        this.behaviors = behaviors;

        return this;
    }
}
