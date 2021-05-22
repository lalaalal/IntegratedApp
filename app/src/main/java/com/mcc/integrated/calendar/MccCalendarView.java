package com.mcc.integrated.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mcc.integrated.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MccCalendarView extends LinearLayout {

    private ArrayList<Todo> todoList = new ArrayList<>();

    private CalendarView calendarView;
    private TodoCardViewGroup todoCardViewGroup;

    public MccCalendarView(Context context) {
        super(context);
        init();
    }

    public MccCalendarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MccCalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_mcc_calendar, this);

        calendarView = findViewById(R.id.calendar_view);
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            ArrayList<Todo> todoList = findTodoListByDate(new CalendarViewDate(year, month, dayOfMonth));
            todoCardViewGroup.setTodoList(todoList);
        });

        todoCardViewGroup = findViewById(R.id.todo_card_view_group);
    }

    public void setTodoList(ArrayList<Todo> todoList) {
        this.todoList = todoList;

        Date currentDate = new Date(calendarView.getDate());
        ArrayList<Todo> visibleTodoList = findTodoListByDate(new CalendarViewDate(currentDate));
        todoCardViewGroup.setTodoList(visibleTodoList);
    }

    private ArrayList<Todo> findTodoListByDate(CalendarViewDate deadline) {
        ArrayList<Todo> list = new ArrayList<>();

        for (Todo todo : todoList) {
            if (deadline.isSameDate(todo.deadline))
                list.add(todo);
        }

        return list;
    }

    public static class CalendarViewDate {
        public int year;
        public int month;
        public int dayOfMonth;

        public CalendarViewDate(Date date) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        }

        public CalendarViewDate(int year, int month, int dayOfMonth) {
            this.year = year;
            this.month = month;
            this.dayOfMonth = dayOfMonth;
        }

        public boolean isSameDate(Date date) {
            return equals(new CalendarViewDate(date));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CalendarViewDate that = (CalendarViewDate) o;

            if (year != that.year) return false;
            if (month != that.month) return false;
            return dayOfMonth == that.dayOfMonth;
        }

        @Override
        public int hashCode() {
            int result = year;
            result = 31 * result + month;
            result = 31 * result + dayOfMonth;
            return result;
        }
    }
}
