package com.mcc.integrated;

import android.app.Activity;

public abstract class UIHandler<T> {
    protected final Activity activity;

    public UIHandler(Activity activity) {
        this.activity = activity;
    }

    public void updateUI(T data) {
        activity.runOnUiThread(() -> runOnUIThread(data));
    }

    protected abstract void runOnUIThread(T data);
}
