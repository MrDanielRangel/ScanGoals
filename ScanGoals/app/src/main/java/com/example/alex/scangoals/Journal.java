package com.example.alex.scangoals;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class Journal extends AppCompatActivity {

    private Button btnBackJR;
    private Button refreshBtn;
    private TextView journalDisplay;
    public String username = LoginActivity.stuffToGrab.getString("username");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnBackJR = (Button) findViewById(R.id.btnBackJR);
        btnBackJR.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Journal.this, mainMenu.class));
            }
        });
        journalDisplay = (TextView) findViewById(R.id.journalDisplay);
        refreshBtn = (Button) findViewById(R.id.RefreshBtn);
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                String displayToTxt = jsonResponse.getString("entry");
                                journalDisplay.setText(displayToTxt);

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Journal.this);
                                builder.setMessage("Failed to load entry's")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                JournalDisplayRequest journalDisplayRequest = new JournalDisplayRequest(username, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Journal.this);
                queue.add(journalDisplayRequest);
            }
        });
    }
}
