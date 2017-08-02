package com.example.alex.scangoals;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.TextView;

public class ProfilePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView imgView = (ImageView) findViewById(R.id.imgView);
        imgView.setImageResource(R.drawable.placeholder);

        final Button btnChangePicture = (Button) findViewById(R.id.btnChangePicture);
        final EditText boxName = (EditText) findViewById(R.id.boxName);
        final EditText boxWeight = (EditText) findViewById(R.id.boxWeight);
        final EditText boxHeight = (EditText) findViewById(R.id.boxHeight);
        final EditText boxAge = (EditText) findViewById(R.id.boxAge);
        //Intent intent= getIntent();
        //TextView lbsDisplay = (TextView) findViewById((R.id.lbsDisplay));

        boxWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().contains(" lbs")){
                    boxWeight.append(" lbs");
                    Selection.setSelection(boxWeight.getText(), boxWeight.getText().length());
                }
            }
        });
    }

}
