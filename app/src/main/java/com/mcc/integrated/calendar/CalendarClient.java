package com.mcc.integrated.calendar;

import com.mcc.integrated.CalendarActivity;
import com.mcc.integrated.HttpsClient;
import com.mcc.integrated.UIHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CalendarClient {
    private static final String URL_GET = "https://kuf1h967mc.execute-api.us-east-2.amazonaws.com/mcc/assignment/get";
    private static final String URL_STATUS = "https://kuf1h967mc.execute-api.us-east-2.amazonaws.com/mcc/assignment/status";
    private static final String PARAM_KEY_ID = "userId";
    private static final String PARAM_KEY_PW = "passwd";

    private final UIHandler<ArrayList<Todo>> uiHandler;

    public CalendarClient(UIHandler<ArrayList<Todo>> uiHandler) {
        this.uiHandler = uiHandler;
    }

    public void update(String id, String pw) {
        Map<String, String> params = new HashMap<>();
        params.put(PARAM_KEY_ID, id);
        params.put(PARAM_KEY_PW, pw);

        Thread thread = new Thread(new BackgroundTask(URL_GET, params));
        thread.start();
    }

    public void load(String id) {
        Map<String, String> params = new HashMap<>();
        params.put(PARAM_KEY_ID, id);

        Thread thread = new Thread(new BackgroundTask(URL_STATUS, params));
        thread.start();
    }

    private class BackgroundTask implements Runnable {
        private static final String STATUS_CODE_KEY = "statusCode";
        private static final String STATUS_CODE_OK = "200";

        private final Map<String, String> params;
        private final String url;

        public BackgroundTask(String url, Map<String, String> params) {
            this.params = params;
            this.url = url;
        }

        @Override
        public void run() {
            try {
                HttpsClient httpsClient = new HttpsClient();
                HttpsClient.Response response = httpsClient.get(url, params);
                ArrayList<Todo> todoList = parseJSON(response);
                uiHandler.updateUI(todoList);
            } catch (IOException | JSONException | ParseException e) {
                e.printStackTrace();
            }
        }

        private ArrayList<Todo> parseJSON(HttpsClient.Response response) throws JSONException, ParseException {
            ArrayList<Todo> todoList = new ArrayList<>();
            JSONObject root = new JSONObject(response.body);
            if (!root.getString(STATUS_CODE_KEY).equals(STATUS_CODE_OK))
                return todoList;

            JSONObject body = root.getJSONObject("body");
            for (Todo.Category category : Todo.Category.values()) {
                JSONArray array = body.getJSONArray(category.KEY_NAME);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject todoObject = array.getJSONObject(i);
                    Todo todo = new Todo(todoObject, category);
                    todoList.add(todo);
                }
            }
            return todoList;
        }
    }
}
