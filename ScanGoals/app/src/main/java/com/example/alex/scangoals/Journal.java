package com.example.alex.scangoals;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class Journal extends mainMenu {

    private Button refreshBtn;
    private TextView journalDisplay;
    public String username = LoginActivity.stuffToGrab.getString("username");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

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

        journalDisplay = (TextView) findViewById(R.id.journalDisplay);
        refreshBtn = (Button) findViewById(R.id.RefreshBtn);
        // when pressed loads the journals from the data base
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
