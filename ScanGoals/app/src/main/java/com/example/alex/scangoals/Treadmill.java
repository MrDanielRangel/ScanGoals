package com.example.alex.scangoals;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Treadmill extends AppCompatActivity {

    private Button submitBtn;
    private Button backBtn;
    private EditText userInputTxt;
    private EditText userInputTxt2;
    private EditText userInputTxt3;
    public String username = LoginActivity.stuffToGrab.getString("username");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treadmill);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        backBtn = (Button) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Treadmill.this, mainMenu.class));
            }
        });
        userInputTxt = (EditText) findViewById(R.id.userInputTxt);
        userInputTxt2 = (EditText) findViewById(R.id.userInputTxt2);
        userInputTxt3 = (EditText) findViewById(R.id.userInputTxt3);
        submitBtn = (Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* final String userInput = "Speed: " + userInputTxt.getText().toString();
                final String userInput2 = "Distance: " + userInputTxt2.getText().toString();
                final String userInput3 = "Time: " + userInputTxt3.getText().toString();*/
                final String userInput = userInputTxt.getText().toString();
                final String userInput2 = userInputTxt2.getText().toString();
                final String userInput3 = userInputTxt3.getText().toString();
                final String lastInput = "Today I ran at " + userInput + "mph for " + userInput2 + " miles in " + userInput3 + " minutes.";

                //create response listener
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                Intent intent = new Intent(Treadmill.this, Journal.class);
                                Treadmill.this.startActivity(intent);
                            }
                            else{
                                AlertDialog.Builder log = new AlertDialog.Builder(Treadmill.this);
                                log.setMessage("Failed To Log To Journal.").setNegativeButton("Retry", null).create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                //create a request
               // LogToJournalRequest logToJournalRequest = new LogToJournalRequest(userInput, userInput2, userInput3, username, responseListener);
                LogToJournalRequest logToJournalRequest = new LogToJournalRequest(lastInput, username, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Treadmill.this);
                queue.add(logToJournalRequest);
            }
        });

    }

}
