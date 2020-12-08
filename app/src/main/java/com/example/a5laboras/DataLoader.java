package com.example.a5laboras;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.os.AsyncTask;
import java.io.IOException;

public class DataLoader extends AsyncTask<Void, Void, Void> {
    public static InputStream dUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        return conn.getInputStream();
    }
    @Override
    protected Void doInBackground(Void... voids) {
        Parser.parsing ();
        return null;
    }
}