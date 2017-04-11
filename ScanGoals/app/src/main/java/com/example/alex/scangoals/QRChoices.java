package com.example.alex.scangoals;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class QRChoices extends AppCompatActivity {
    private Button btnLogJR;
    private Button btnBackCH;
    private Button btnVideo;
    private int whichWorkout = 2;
    public static Bundle stuffToGrab = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrchoices);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnLogJR = (Button) findViewById(R.id.btnLogJR);
        btnLogJR.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                if(whichWorkout == 1){
                    startActivity(new Intent(QRChoices.this, Treadmill.class));
                }else if (whichWorkout == 2){
                    startActivity(new Intent(QRChoices.this, BenchPress.class));
                }else if (whichWorkout == 3){
                    startActivity(new Intent(QRChoices.this, Squat.class));
                }


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
            @Override
            public void onClick(View v){
                final String videoURL = "";
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                String videoKey1 = jsonResponse.getString("videoKey");
                                stuffToGrab.putString("videoKey12", videoKey1);
                                //AlertDialog.Builder builder = new AlertDialog.Builder(QRChoices.this);
                                //builder.setMessage (setKey.YOUTUBE_VIDEO_ID).show();
                                startActivity(new Intent(QRChoices.this, VideoPage.class));
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(QRChoices.this);
                                builder.setMessage("Failed To Get Key")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                VideoRequest choiceRequest = new VideoRequest(videoURL, responseListener);
                RequestQueue queue = Volley.newRequestQueue(QRChoices.this);
                queue.add(choiceRequest);

            }
        });
    }
}
