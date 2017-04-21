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
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class QRScanner extends AppCompatActivity {
    private Button btnBackQR;
    private SurfaceView cameraView;
    private TextView barcodeInfo;
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
    public Activity thisAct = this;
    private boolean permisBool = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnBackQR = (Button) findViewById(R.id.btnBackQR);
        btnBackQR.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(QRScanner.this, mainMenu.class));
            }
        });
        // Here, thisActivity is the current activity

        setContentView(R.layout.content_qrscanner);
        cameraView = (SurfaceView)findViewById(R.id.camera_view);
        barcodeInfo = (TextView)findViewById(R.id.code_info);
        barcodeDetector =
                new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();
        cameraSource = new CameraSource
                .Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640,480)
                .build();

        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try{
                    // Here, thisActivity is the current activity
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
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }

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
