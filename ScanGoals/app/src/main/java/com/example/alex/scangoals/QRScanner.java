package com.example.alex.scangoals;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class QRScanner extends AppCompatActivity {
    private Button btnBackQR;
    private Button btnQRscan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnBackQR = (Button) findViewById(R.id.btnBackQR);
        btnQRscan = (Button) findViewById(R.id.btnQRscan);

        btnBackQR.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(QRScanner.this, mainMenu.class));
            }
        });
        btnQRscan.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(QRScanner.this, QRChoices.class));
            }
        });


    }

}
