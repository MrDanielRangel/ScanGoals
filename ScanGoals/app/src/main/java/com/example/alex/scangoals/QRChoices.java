package com.example.alex.scangoals;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
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
    // butons for the qr choices
    private Button btnLogJR;
    private Button btnBackCH;
    private Button btnVideo;
    // a bundle so that we can grab information
    public static Bundle stuffToGrab = new Bundle();
    // This grabs which workout equipment the user scaned for
    private String whichWorkout =  InputActivity.stuffToGrab1.getString("userInput1");

    // Grabs qr choice
    private String whichWorkoutQR = QRScanner.stuffToGrab2.getString("userInput2");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // on create
        super.onCreate(savedInstanceState);
        // set the view
        setContentView(R.layout.activity_qrchoices);
        // grab toolbar
        InputActivity.stuffToGrab1.putString("userInput1", "0");
        QRScanner.stuffToGrab2.putString("userInput2", "0");


        // when log button has been pressed
        btnLogJR = (Button) findViewById(R.id.btnLogJR);
        btnLogJR.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // check to see which equipment the user scanned for and then pull up the right one
                if("1".equals(whichWorkout) || "1".equals(whichWorkoutQR)){
                    startActivity(new Intent(QRChoices.this, Squat.class));
                }else if ("2".equals(whichWorkout) || "2".equals(whichWorkoutQR)){
                    startActivity(new Intent(QRChoices.this, BenchPress.class));
                }else if ("3".equals(whichWorkout) || "3".equals(whichWorkoutQR)){
                    startActivity(new Intent(QRChoices.this, Treadmill.class));
                }


            }
        });
        // if the back button is pressed go back to main menu
        btnBackCH = (Button) findViewById(R.id.btnBackCH);
        btnBackCH.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(QRChoices.this, mainMenu.class));
            }
        });
        // if the video button is pressed pull of the right video
        btnVideo = (Button) findViewById(R.id.btnVideo);
        btnVideo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String videoURL = "";
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            // check to see if the json is a success or not
                            // check with database to see what vidoe will get played
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            // if the database return true
                            if (success) {
                                // set the correct video key to the variable
                                if("1".equals(whichWorkout) || "1".equals(whichWorkoutQR)){
                                    stuffToGrab.putString("videoKey12", "jh8ixeIyhJw");
                                }else if ("2".equals(whichWorkout) || "2".equals(whichWorkoutQR)){
                                    stuffToGrab.putString("videoKey12", "_BnnyuO1QpY");
                                }else if ("3".equals(whichWorkout) || "3".equals(whichWorkoutQR)){
                                    stuffToGrab.putString("videoKey12", "VYQdWftVWNY");
                                }
                                // start next activity
                                startActivity(new Intent(QRChoices.this, VideoPage.class));
                            } else {
                                // tell user fail to get key
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
                //  set these in video request
                VideoRequest choiceRequest = new VideoRequest(videoURL, responseListener);
                RequestQueue queue = Volley.newRequestQueue(QRChoices.this);
                queue.add(choiceRequest);

            }
        });
    }
}
