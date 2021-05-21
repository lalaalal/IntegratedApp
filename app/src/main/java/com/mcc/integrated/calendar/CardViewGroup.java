package com.mcc.integrated.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class CardViewGroup extends LinearLayout {
    private ArrayList<MaterialCardView> cards = new ArrayList<>();

    public CardViewGroup(Context context) {
        super(context);
        setOrientation(VERTICAL);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public CardViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public CardViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public void setCards(ArrayList<MaterialCardView> cards) {
        removeAllViews();
        if (cards.isEmpty())
            cards.add(new NothingCardView(getContext()));

        this.cards = cards;

        for (MaterialCardView card : cards)
            addView(card);
    }

    public void addCard(MaterialCardView card) {
        cards.add(card);
        addView(card);
    }

    public ArrayList<MaterialCardView> getCards() {
        return cards;
    }
}
