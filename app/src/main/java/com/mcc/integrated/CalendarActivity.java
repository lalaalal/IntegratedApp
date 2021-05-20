package com.mcc.integrated;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.mcc.integrated.calendar.CalendarClient;
import com.mcc.integrated.calendar.MccCalendarView;
import com.mcc.integrated.calendar.Todo;

import java.util.ArrayList;

public class CalendarActivity extends AppCompatActivity {
    private MccCalendarView mccCalendarView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mccCalendarView = findViewById(R.id.mcc_calendar_view);
    }

    public class CalendarUIHandler {

        public void updateUI(ArrayList<Todo> todoList) {
            mccCalendarView.setTodoList(todoList);
        }
    }
}
