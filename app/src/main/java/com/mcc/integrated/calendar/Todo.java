package com.mcc.integrated.calendar;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Todo {
    public enum Category {
        ASSIGNMENT("assignment");//, NOTICE("notice");

        public final String KEY_NAME;

        private Category(String keyName) {
            this.KEY_NAME = keyName;
        }
    }
    
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_COURSE = "course";
    private static final String KEY_DEADLINE = "deadline";

    public String message;
    public String category;
    public String course;
    public Date deadline;

    public Todo(String message, String category, String course, Date deadline) {
        this.message = message;
        this.category = category;
        this.course = course;
        this.deadline = deadline;
    }

    public Todo(JSONObject jsonObject, Category category) throws JSONException, ParseException {
        this.message = jsonObject.getString(KEY_MESSAGE);
        this.category = category.KEY_NAME;
        this.course = jsonObject.getString(KEY_COURSE);
        String deadlineStr = jsonObject.getString(KEY_DEADLINE);
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd a hh:mm", Locale.KOREAN);
        deadline = format.parse(deadlineStr);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "message='" + message + '\'' +
                ", category='" + category + '\'' +
                ", course='" + course + '\'' +
                ", deadline=" + deadline +
                '}';
    }
}
