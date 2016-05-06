package com.example.vkaryagin.testaplication;

import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by v.karyagin on 5/6/16.
 */
public class HtmlLoader extends AsyncTask<String, Long, String> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... urls) {
        String inputLine;
        StringBuffer response = new StringBuffer();

        try {
            URL url = new URL(urls[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if(conn.getResponseCode() != 200) {
                //Toast.makeText(MainActivity.this, "Response code is not 200. Maybe URL is incorrect.", Toast.LENGTH_SHORT).show();
                //return null;
            }

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            while((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

        } catch (IOException e) {

        }

        return response.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        MainActivity.setTextHTML(result);
    }
}
