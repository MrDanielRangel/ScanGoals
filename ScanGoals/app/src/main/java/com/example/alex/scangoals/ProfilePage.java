package com.example.alex.scangoals;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfilePage extends mainMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        //init nav drawer
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);
        final DrawerLayout mDrawer=(DrawerLayout) findViewById(R.id.drawer_layout);
        ImageButton navButton=(ImageButton) findViewById(R.id.navButton);
        navButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                mDrawer.openDrawer(GravityCompat.START);
            }
        });

        ImageView imgView = (ImageView) findViewById(R.id.imgView);
        imgView.setImageResource(R.drawable.placeholder);

        final Button btnChangePicture = (Button) findViewById(R.id.btnChangePicture);
        final EditText boxName = (EditText) findViewById(R.id.boxName);
        final EditText boxWeight = (EditText) findViewById(R.id.boxWeight);
        final EditText boxHeight = (EditText) findViewById(R.id.boxHeight);
        final EditText boxAge = (EditText) findViewById(R.id.boxAge);

/*      //Not sure what this code was supposed to be... -CW
        final String name = boxName.getText().toString();
        final String height = boxHeight.getText().toString();
        final String weight = boxWeight.getText().toString();
        final String age = boxAge.getText().toString();

        //create response listener
        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if(success){
                        Intent intent = new Intent(ProfilePage.this, LoginActivity.class);
                        ProfilePage.this.startActivity(intent);
                    }
                    else{
                        AlertDialog.Builder log = new AlertDialog.Builder(ProfilePage.this);
                        log.setMessage("Something Went Wrong.").setNegativeButton("Retry", null).create().show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        //create a request
        /*ProfileRequest pRequest = new ProfileRequest(name, height, weight, age, responseListener);
        RequestQueue queue = Volley.newRequestQueue(ProfilePage.this);
        queue.add(pRequest);*/

    }

}
