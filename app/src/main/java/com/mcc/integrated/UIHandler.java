package com.mcc.integrated;

import android.app.Activity;

public abstract class UIHandler<T> {
    protected final Activity activity;

    public UIHandler(Activity activity) {
        this.activity = activity;
    }

    public abstract void updateUI(T data);
}
