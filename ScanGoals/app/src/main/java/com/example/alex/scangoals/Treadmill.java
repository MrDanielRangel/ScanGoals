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
    // buttons
    private Button submitBtn;
    private Button backBtn;
    // takes in the user input
    private EditText userInputTxt;
    // finds out what the user name is
    public String username = LoginActivity.stuffToGrab.getString("username");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treadmill);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // if they press back button the go back to main menu
        backBtn = (Button) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Treadmill.this, mainMenu.class));
            }
        });
        // allow them to edit text
        userInputTxt = (EditText) findViewById(R.id.userInputTxt);
        submitBtn = (Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userInput = userInputTxt.getText().toString();

                //create response listener
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        // stores the log to user profile
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
                LogToJournalRequest logToJournalRequest = new LogToJournalRequest(userInput, username, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Treadmill.this);
                queue.add(logToJournalRequest);
            }
        });

    }

}
