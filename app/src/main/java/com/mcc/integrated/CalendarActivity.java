package com.mcc.integrated;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.mcc.integrated.calendar.CalendarClient;
import com.mcc.integrated.calendar.MccCalendarView;
import com.mcc.integrated.calendar.Todo;

import java.util.ArrayList;

public class CalendarActivity extends AppCompatActivity {
    public static final String KEY_EXTRA_ID = "id";
    public static final String KEY_EXTRA_PW = "pw";

    private MccCalendarView mccCalendarView;
    private TextView loadStatusTv;

    private String id;
    private String pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Intent intent = getIntent();
        id = intent.getStringExtra(KEY_EXTRA_ID);
        pw = intent.getStringExtra(KEY_EXTRA_PW);

        mccCalendarView = findViewById(R.id.mcc_calendar_view);
        loadStatusTv = findViewById(R.id.load_status_tv);

        CalendarClient client = new CalendarClient(new CalendarUIHandler(this));
        client.load(id);
    }

    public class CalendarUIHandler extends UIHandler<ArrayList<Todo>> {

        public CalendarUIHandler(Activity activity) {
            super(activity);
        }

        @Override
        protected void runOnUIThread(ArrayList<Todo> data) {
            loadStatusTv.setText(R.string.load_status_succeed);
            mccCalendarView.setTodoList(data);
        }
    }
}
