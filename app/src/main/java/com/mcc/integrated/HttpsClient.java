package com.mcc.integrated;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class HttpsClient {

    private String makeParamString(Map<String, String> param) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String key : param.keySet()) {
            String value = param.get(key);
            stringBuilder.append('&').append(key).append('=').append(value);
        }
        stringBuilder.deleteCharAt(0);

        return stringBuilder.toString();
    }

    public Response get(String url, Map<String, String> param) throws IOException {
        String paramString = makeParamString(param);
        url += '?' + paramString;

        return get(url);
    }

    public Response get(String url) throws IOException {
        URL URL = new URL(url);
        HttpsURLConnection connection = (HttpsURLConnection) URL.openConnection();
        connection.setRequestMethod("GET");

        return new Response(connection);
    }

    public Response post(String url, Map<String, String> data) throws IOException {
        String dataString = makeParamString(data);

        URL URL = new URL(url);
        HttpsURLConnection connection = (HttpsURLConnection) URL.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        send(connection.getOutputStream(), dataString);
        return new Response(connection);
    }

    private void send(OutputStream os, String content) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8))) {
            writer.write(content);
        } catch (IOException ignored) {

        }
    }

    public static class Response {
        public final int responseCode;
        public final String body;

        public Response(HttpsURLConnection connection) throws IOException {
            responseCode = connection.getResponseCode();
            body = receive(connection.getInputStream());
        }

        private String receive(InputStream is) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null)
                    stringBuilder.append(line).append('\n');

                return stringBuilder.toString();
            } catch (IOException e) {
                return "";
            }
        }
    }
}
