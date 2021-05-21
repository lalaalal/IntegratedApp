package com.mcc.integrated;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.mcc.integrated.calendar.CalendarClient;
import com.mcc.integrated.calendar.MccCalendarView;
import com.mcc.integrated.calendar.Todo;

import java.util.ArrayList;

public class CalendarActivity extends AppCompatActivity {
    private static final String KEY_BUNDLE_ID = "id";
    private static final String KEY_BUNDLE_PW = "pw";

    private MccCalendarView mccCalendarView;
    private TextView loadStatusTv;

    private String id;
    private String pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        id = savedInstanceState.getString(KEY_BUNDLE_ID);
        pw = savedInstanceState.getString(KEY_BUNDLE_PW);

        mccCalendarView = findViewById(R.id.mcc_calendar_view);
        loadStatusTv = findViewById(R.id.load_status_tv);

        CalendarClient client = new CalendarClient(new CalendarUIHandler(this));
        client.load(id);
    }

    public class CalendarUIHandler {
        private final Activity activity;

        public CalendarUIHandler(Activity activity) {
            this.activity = activity;
        }

        public void updateUI(ArrayList<Todo> todoList) {
            loadStatusTv.setText(R.string.load_status_succeed);
            activity.runOnUiThread(() -> mccCalendarView.setTodoList(todoList));
        }
    }
}
