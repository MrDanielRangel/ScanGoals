package com.example.alex.scangoals;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


public class Journal extends AppCompatActivity {

    private Button btnBackJR;

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


    }

}
