package com.mcc.integrated.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import com.google.android.material.card.MaterialCardView;
import com.mcc.integrated.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class TodoCardView extends MaterialCardView {

    private Todo todo;
    private TextView courseTv;
    private TextView messageTv;
    private TextView deadlineTv;

    public TodoCardView(Context context) {
        super(context);
        init();
    }

    public TodoCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TodoCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        inflate(getContext(), R.layout.card_view_todo, this);

        courseTv = findViewById(R.id.course_tv);
        messageTv = findViewById(R.id.message_tv);
        deadlineTv = findViewById(R.id.deadline_tv);
    }

    private void updateView() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd a hh:mm", Locale.KOREAN);
        String deadline = dateFormat.format(todo.deadline);

        courseTv.setText(todo.course);
        messageTv.setText(todo.message);
        deadlineTv.setText(deadline);
    }

    public void setTodo(Todo todo) {
        this.todo = todo;

        updateView();
    }

    public Todo getTodo() {
        return todo;
    }
}
