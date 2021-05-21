package com.mcc.integrated.calendar;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.material.card.MaterialCardView;
import com.mcc.integrated.R;

public class NothingCardView extends MaterialCardView {
    public NothingCardView(Context context) {
        super(context);
        inflate(getContext(), R.layout.card_view_nothing, this);
    }

    public NothingCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(), R.layout.card_view_nothing, this);
    }

    public NothingCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.card_view_nothing, this);
    }
}
