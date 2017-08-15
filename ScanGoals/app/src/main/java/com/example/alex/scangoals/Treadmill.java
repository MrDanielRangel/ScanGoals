package com.example.alex.scangoals;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Treadmill extends mainMenu {
    // buttons
    private Button submitBtn;
    // takes in the user input
    private EditText userInputTxt;
    private EditText userInputTxt2;
    private EditText userInputTxt3;
    // finds out what the user name is

    public String username = LoginActivity.stuffToGrab.getString("username");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treadmill);
        //init nav drawer
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);
        final DrawerLayout mDrawer=(DrawerLayout) findViewById(R.id.drawer_layout);
        ImageButton navButton=(ImageButton) findViewById(R.id.navButton);
        navButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                mDrawer.openDrawer(GravityCompat.START);
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
               // LogToJournalRequest logToJournalRequest = new LogToJournalRequest(userInput, userInput2, userInput3, username, responseListener);
                LogToJournalRequest logToJournalRequest = new LogToJournalRequest(lastInput, username, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Treadmill.this);
                queue.add(logToJournalRequest);
            }
        });

    }

}
