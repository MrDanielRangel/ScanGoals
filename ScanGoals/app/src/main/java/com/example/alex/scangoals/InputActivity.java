package com.example.alex.scangoals;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class InputActivity extends AppCompatActivity {
    Button btnSubmit;
    Button btnBack;
    EditText editTxt;
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

    }

    public void sendResult(View view)
    {
        String result = editTxt.getText().toString();
        editTxt.setText("Thank You!");
        Log.v("Input", result);
        //return result;

    }


}
