package com.example.alex.scangoals;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class QRScanner extends AppCompatActivity {
    // buttons for qr
    private Button btnBackQR;
    private Button btnSubmit;
    // where the qr will be
    private SurfaceView cameraView;
    // shows what got scanded
    private TextView barcodeInfo;
    // for detecting the qr
    private BarcodeDetector barcodeDetector;
    // for camera
    private CameraSource cameraSource;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
    public Activity thisAct = this;
    private boolean permisBool = false;
    public static Bundle stuffToGrab2 = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);


        // set the camera to the slot and display it to user
        setContentView(R.layout.content_qrscanner);
        cameraView = (SurfaceView)findViewById(R.id.camera_view);
        barcodeInfo = (TextView)findViewById(R.id.code_info);
        barcodeDetector =
                new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();
        cameraSource = new CameraSource
                .Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640,300)
                .build();

        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try{
                    // allows access to camera and make it set the field that is set for the camera
                    if (ContextCompat.checkSelfPermission(thisAct, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(thisAct, Manifest.permission.CAMERA)) {

                        } else {

                            ActivityCompat.requestPermissions(thisAct,
                                    new String[]{Manifest.permission.CAMERA},
                                    MY_PERMISSIONS_REQUEST_CAMERA);
                        }
                    }else{
                        permisBool = true;
                    }
                    if(permisBool == true) {
                        cameraSource.start(cameraView.getHolder());
                    }
                }catch (IOException ie){
                    Log.e("CAMERA SOURCE", ie.getMessage());
                }
            }
            // if the surface was changed
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                // go back
                btnBackQR = (Button) findViewById(R.id.btnBackQR);
                btnBackQR.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        startActivity(new Intent(QRScanner.this, mainMenu.class));
                    }
                });
                // this butten check to see if the qr code is the correct one
                btnSubmit = (Button) findViewById(R.id.submitButton);
                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //final int userInputCode = Integer.parseInt(editTxt.getText().toString());
                        final String userInputCode = barcodeInfo.getText().toString();
                        // Response received from the server
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    // checks data base to see if it was correct
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");

                                    if (success) {
                                        String code = jsonResponse.getString("userInput");
                                        stuffToGrab2.putString("userInput2", code);
                                        startActivity(new Intent(QRScanner.this, QRChoices.class));
                                    } else {
                                        // if it is a wrong code
                                        AlertDialog.Builder builder = new AlertDialog.Builder(QRScanner.this);
                                        builder.setMessage("Wrong QR code")
                                                .setNegativeButton("Retry", null)
                                                .create()
                                                .show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        userInput userInput = new userInput (userInputCode, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(QRScanner.this);
                        queue.add(userInput);
                    }
                });
            }
            // stop using camera
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }
            // this detects the barcode
            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() != 0) {
                    barcodeInfo.post(new Runnable() { // Use the post method of the TextView
                        public void run() {
                            barcodeInfo.setText(    // Update the TextView
                                    barcodes.valueAt(0).displayValue
                            );
                        }
                    });
                }
            }
        });
    }
}
