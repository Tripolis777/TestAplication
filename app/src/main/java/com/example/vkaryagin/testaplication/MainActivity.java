package com.example.vkaryagin.testaplication;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    private static TextView textHTML;
    private static Button urlButton;
    private static EditText urlField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textHTML = (TextView) findViewById(R.id.textHTML);
        urlButton = (Button) findViewById(R.id.urlButton);
        urlField = (EditText) findViewById(R.id.urlField);

        textHTML.setText("Please Enter URL and click Connect button.");

        urlButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String urlStr = urlField.getText().toString();
                if(urlStr == null) {
                    createAlertDialog("URL is empty");
                }

                HtmlLoader loader = new HtmlLoader();
                loader.execute(urlStr);
            }

        });
    }

    private void createAlertDialog(String message) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        dialogBuilder.setTitle("Error")
                .setMessage(message)
                .setCancelable(true)
                .setNeutralButton("OK", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int id){
                        dialogInterface.cancel();
                    }
                });
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    public static void setTextHTML(String text) {
        textHTML.setText(text);
    }
}
