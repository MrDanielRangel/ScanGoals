package com.example.alex.scangoals;

import android.*;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.alex.scangoals.QRScanner.MY_PERMISSIONS_REQUEST_CAMERA;

public class LoginActivity extends AppCompatActivity {
    public static Bundle stuffToGrab = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final Button registerBtn = (Button) findViewById(R.id.registerBtn);

        //whenever user click the register link, then will redirect user to register page
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, Register.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

      bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              final String username = etUsername.getText().toString();
              final String password = etPassword.getText().toString();

              // Response received from the server
              Response.Listener<String> responseListener = new Response.Listener<String>() {
                  @Override
                  public void onResponse(String response) {
                      try {
                          JSONObject jsonResponse = new JSONObject(response);
                          boolean success = jsonResponse.getBoolean("success");

                          if (success) {
                              String username = jsonResponse.getString("username");
                              stuffToGrab.putString("username", username);
                              startActivity(new Intent(LoginActivity.this, mainMenu.class));

                          } else {
                              AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                              builder.setMessage("Login Failed")
                                      .setNegativeButton("Retry", null)
                                      .create()
                                      .show();
                          }

                      } catch (JSONException e) {
                          e.printStackTrace();
                      }
                  }
              };

              LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
              RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
              queue.add(loginRequest);
          }
        });

        //The following section checks for camera permissions. This happens only once on first runtime.
        //TODO: Add an explanation to why camera perms are needed if user denies them.
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.CAMERA)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_CAMERA);
            }
        }
    }
}