package com.example.alex.scangoals;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class QRChoices extends AppCompatActivity {
    private Button btnLogJR;
    private Button btnBackCH;
    private Button btnVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrchoices);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnLogJR = (Button) findViewById(R.id.btnLogJR);
        btnLogJR.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(QRChoices.this, Journal.class));
            }
        });
        btnBackCH = (Button) findViewById(R.id.btnBackCH);
        btnBackCH.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(QRChoices.this, mainMenu.class));
            }
        });
        btnVideo = (Button) findViewById(R.id.btnVideo);
        btnVideo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(QRChoices.this, VideoPage.class));
            }
        });



    }

}
