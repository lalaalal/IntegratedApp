package com.mcc.integrated.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class TodoCardViewGroup extends CardViewGroup {
    public TodoCardViewGroup(Context context) {
        super(context);
    }

    public TodoCardViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TodoCardViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void addTodo(Todo todo) {
        TodoCardView todoCardView = new TodoCardView(getContext());
        todoCardView.setTodo(todo);

        addCard(todoCardView);
    }

    public void setTodoList(ArrayList<Todo> todoList) {
        ArrayList<MaterialCardView> cardViews = new ArrayList<>();
        for (Todo todo : todoList) {
            TodoCardView todoCardView = new TodoCardView(getContext());
            todoCardView.setTodo(todo);
            cardViews.add(todoCardView);
        }

        setCards(cardViews);
    }
}
