package com.example.alex.scangoals;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class InputActivity extends AppCompatActivity {
    Button btnSubmit;
    Button btnBack;
    EditText editTxt;
    public static Bundle stuffToGrab1 = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        editTxt = (EditText) findViewById(R.id.editText);
        btnSubmit = (Button) findViewById(R.id.submitButton);
        btnBack = (Button) findViewById(R.id.backButton);

        btnBack.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(InputActivity.this, mainMenu.class));
            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //final int userInputCode = Integer.parseInt(editTxt.getText().toString());
                final String userInputCode = editTxt.getText().toString();
                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                String code = jsonResponse.getString("userInput");
                                stuffToGrab1.putString("userInput1", code);
                                startActivity(new Intent(InputActivity.this, QRChoices.class));
                                //Intent intent = new Intent(LoginActivity.this, mainMenu.class);
                                //intent.putExtra("username", username);
                                //LoginActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(InputActivity.this);
                                builder.setMessage("Wrong Input")
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
                RequestQueue queue = Volley.newRequestQueue(InputActivity.this);
                queue.add(userInput);
            }
        });
    }

    public void sendResult(View view)
    {
        String result = editTxt.getText().toString();
        editTxt.setText("Thank You!");
        Log.v("Input", result);
        //return result;

    }


}
