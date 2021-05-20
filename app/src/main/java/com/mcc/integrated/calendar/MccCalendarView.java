package com.mcc.integrated.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.mcc.integrated.R;

import java.util.ArrayList;

public class MccCalendarView extends LinearLayout {

    public MccCalendarView(Context context) {
        super(context);

        inflate(getContext(), R.layout.view_mcc_calendar, this);
    }

    public MccCalendarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MccCalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MccCalendarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setTodoList(ArrayList<Todo> todoList) {
        // TODO: 5/21/21 change ui data with todo list
    }
}
