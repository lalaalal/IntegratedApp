package com.mcc.integrated;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.mcc.integrated.calendar.CalendarClient;
import com.mcc.integrated.calendar.Todo;

import java.util.ArrayList;

public class CalendarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
    }

    public class CalendarUIHandler {

        public void updateUI(ArrayList<Todo> todoList) {
            // TODO: 5/21/21 update ui with todo list 
        }
    }
}
