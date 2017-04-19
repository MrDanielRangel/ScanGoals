package com.example.alex.scangoals;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class mainMenu extends AppCompatActivity {
    private Button btnQR;
    private Button btnJournal;
    private Button btnInput;
    private Button btnLogout;
    //final EditText etUsername = (EditText) findViewById(R.id.etUsername);
    //final TextView welcomeMsg = (TextView) findViewById(R.id.welcomeMsg);

   // Intent intent = getIntent();
    //String username = intent.getStringExtra("username");
    //String message = username + " welcome to Scan Goals";

    //output username message


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnQR = (Button) findViewById(R.id.btnQR);
        btnJournal = (Button) findViewById(R.id.btnJournal);
        btnInput = (Button) findViewById(R.id.btnInput);
        btnLogout = (Button) findViewById(R.id.logOutBtn);

        btnQR.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(mainMenu.this, QRScanner.class));
            }
        });
        btnJournal.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(mainMenu.this, Journal.class));
            }
        });
        btnInput.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(mainMenu.this, InputActivity.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(mainMenu.this, LoginActivity.class));
            }
        });
    }

}
