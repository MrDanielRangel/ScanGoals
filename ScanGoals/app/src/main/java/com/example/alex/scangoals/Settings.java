package com.example.alex.scangoals;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {
    private Button btnBackSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnBackSet = (Button) findViewById(R.id.btnBackSet);
        btnBackSet.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Settings.this, mainMenu.class));
            }
        });


    }

}
