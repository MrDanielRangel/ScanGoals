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

public class BenchPress extends AppCompatActivity {
    // buttons
    private Button submitBtn;
    private Button backBtn;
    // takes in the user input
    private EditText userInputTxt;
<<<<<<< HEAD
    private EditText userInputTxt2;
    private EditText userInputTxt3;
=======
    // finds out what the user name is
>>>>>>> ba8c2f22ccf601009feddcce9cb01731da318048
    public String username = LoginActivity.stuffToGrab.getString("username");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bench_press);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // if they press back button the go back to main menu
        backBtn = (Button) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(BenchPress.this, mainMenu.class));
            }
        });
        // allow them to edit text
        userInputTxt = (EditText) findViewById(R.id.userInputTxt);
        userInputTxt2 = (EditText) findViewById(R.id.userInputTxt2);
        userInputTxt3 = (EditText) findViewById(R.id.userInputTxt3);
        submitBtn = (Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userInput = userInputTxt.getText().toString();
                final String userInput2 = userInputTxt2.getText().toString();
                final String userInput3 = userInputTxt3.getText().toString();
                final String lastInput = "Today I benched "+ userInput + "lbs, doing " + userInput3 + " sets of " + userInput2 + " reps.";

                //create response listener
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                    // stores the log to user profile
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                Intent intent = new Intent(BenchPress.this, Journal.class);
                                BenchPress.this.startActivity(intent);
                            }
                            else{
                                AlertDialog.Builder log = new AlertDialog.Builder(BenchPress.this);
                                log.setMessage("Failed To Log To Journal.").setNegativeButton("Retry", null).create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                //create a request
              //  LogToJournalRequest logToJournalRequest = new LogToJournalRequest(userInput, username, responseListener);
                LogToJournalRequest logToJournalRequest = new LogToJournalRequest(lastInput, username, responseListener);
                RequestQueue queue = Volley.newRequestQueue(BenchPress.this);
                queue.add(logToJournalRequest);
            }
        });

    }

}
