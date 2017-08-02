package com.example.alex.scangoals;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class mainMenu extends AppCompatActivity {
    // buttons to move on to the next activates
    private Button btnQR;
    private Button btnJournal;
    private Button btnInput;
    private Button btnLogout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // for the buttons
        btnQR = (Button) findViewById(R.id.btnQR);
        btnJournal = (Button) findViewById(R.id.btnJournal);
        btnInput = (Button) findViewById(R.id.btnInput);
        btnLogout = (Button) findViewById(R.id.logOutBtn);
        // move on to qr scanner
        btnQR.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(mainMenu.this, QRScanner.class));
            }
        });
        // move on to journal
        btnJournal.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(mainMenu.this, Journal.class));
            }
        });
        // move on to input
        btnInput.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(mainMenu.this, InputActivity.class));
            }
        });
        // move on to login
        btnLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(mainMenu.this, LoginActivity.class));
            }
        });
    }

}
