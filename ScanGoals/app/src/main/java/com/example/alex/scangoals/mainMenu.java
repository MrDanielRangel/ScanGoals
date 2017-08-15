package com.example.alex.scangoals;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class mainMenu extends AppCompatActivity {
    // buttons to move on to the next activates
    private Button btnQR;
    private Button btnJournal;
    private Button btnInput;
    private Button btnLogout;
    private Button btnProfile;
    public ImageButton navButton;


    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        //toolbar= (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);
        mDrawer=(DrawerLayout) findViewById(R.id.drawer_layout);
        navButton=(ImageButton) findViewById(R.id.navButton);
        navButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                mDrawer.openDrawer(GravityCompat.START);
            }
        });




        // for the buttons
        btnQR = (Button) findViewById(R.id.btnQR);
        btnJournal = (Button) findViewById(R.id.btnJournal);
        btnInput = (Button) findViewById(R.id.btnInput);
        btnLogout = (Button) findViewById(R.id.logOutBtn);
        btnProfile = (Button) findViewById(R.id.btnProfile);
        // move on to qr scanner
        btnQR.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(mainMenu.this, QRScanner.class));
            }
        });
        // move on to journal
        btnJournal.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(mainMenu.this, Journal.class));
            }
        });
        // move on to input
        btnInput.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(mainMenu.this, InputActivity.class));
            }
        });
        //profile
        btnProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(mainMenu.this, ProfilePage.class));
            }
        });
        // move on to login
        btnLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(mainMenu.this, LoginActivity.class));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return true;
    }
    public void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.nav_QRScan:
                startActivity(new Intent(mainMenu.this, QRScanner.class));
                break;
            case R.id.nav_journal:
                startActivity(new Intent(mainMenu.this, Journal.class));
                break;
            case R.id.nav_profile:
                startActivity(new Intent(mainMenu.this, ProfilePage.class));
                break;
            case R.id.nav_logout:
                startActivity(new Intent(mainMenu.this, LoginActivity.class));
                break;
            default:
                //fragmentClass = FirstFragment.class;
        }
        menuItem.setChecked(true);
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }


}
