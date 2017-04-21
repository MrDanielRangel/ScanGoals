package com.example.alex.scangoals;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import static com.example.alex.scangoals.QRScanner.MY_PERMISSIONS_REQUEST_CAMERA;

public class CheckForUserPermission extends AppCompatActivity {
    public Activity thisAct = this;
    private Button moveBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_for_user_permission);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (ContextCompat.checkSelfPermission(thisAct, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(thisAct, Manifest.permission.CAMERA)) {

            } else {

                ActivityCompat.requestPermissions(thisAct,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_CAMERA);
            }
        }
        moveBtn = (Button) findViewById(R.id.MoveBtn);
        moveBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(CheckForUserPermission.this, QRScanner.class));
            }
        });
    }

}
