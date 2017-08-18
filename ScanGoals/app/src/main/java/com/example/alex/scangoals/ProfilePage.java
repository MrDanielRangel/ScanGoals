package com.example.alex.scangoals;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
<<<<<<< HEAD
import android.support.v7.app.AppCompatActivity;
=======
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
>>>>>>> 2924cb76e73b098849a3bcf47f6210143e02cf93
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
<<<<<<< HEAD
import java.io.ByteArrayOutputStream;
import android.util.Base64;
import android.provider.MediaStore;
import java.io.IOException;
import android.app.ProgressDialog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.Request;
import android.widget.Toast;
import com.android.volley.VolleyError;
import java.util.Hashtable;
import com.android.volley.AuthFailureError;
import java.util.Map;
import java.lang.String;

public class ProfilePage extends AppCompatActivity implements View.OnClickListener{

    private Button backButton, sButton;
    private Button btnBuscar, btnSubir;
    private ImageView imageView;
    private Bitmap bitmap;
    private int PICK_IMAGE_REQUEST = 1;
    private String UPLOAD_URL = "https://camelbackspartans.com/scangoals/upload.php";
    private String KEY_IMAGEN = "foto";

    public String username = LoginActivity.stuffToGrab.getString("username");
    public static EditText boxName, boxWeight, boxHeight, boxAge;
    String name, weight, height, age;
    public static Bundle stuffy = new Bundle();
    public static Bitmap userimg;
=======

public class ProfilePage extends mainMenu {
>>>>>>> 2924cb76e73b098849a3bcf47f6210143e02cf93

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

<<<<<<< HEAD
        boxName = (EditText) findViewById(R.id.boxName);
        boxWeight = (EditText) findViewById(R.id.boxWeight);
        boxHeight = (EditText) findViewById(R.id.boxHeight);
        boxAge = (EditText) findViewById(R.id.boxAge);
        backButton = (Button) findViewById(R.id.bButton);
=======
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
>>>>>>> 2924cb76e73b098849a3bcf47f6210143e02cf93

        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnSubir = (Button) findViewById(R.id.btnSubir);
        imageView = (ImageView) findViewById(R.id.imageView);

        btnBuscar.setOnClickListener(this);
        btnSubir.setOnClickListener(this);

        backButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(ProfilePage.this, mainMenu.class));
            }
        });

        boxName.setText(username);
        imageView.setImageBitmap(userimg);
        boxWeight.setText(stuffy.getString("weight"));
        boxHeight.setText(stuffy.getString("height"));
        boxAge.setText(stuffy.getString("age"));
        

    }

    public String getStringImagen(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        return encodedImage;
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onClick(View v) {

        if(v == btnBuscar){
            showFileChooser();
        }

        if(v == btnSubir){
            uploadImage();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //map of gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Config image view
                imageView.setImageBitmap(bitmap);
                userimg = bitmap;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage(){
        //Mostrar el diálogo de progreso
        final ProgressDialog loading = ProgressDialog.show(this,"Uploading...","Wait a Sec...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Descartar el diálogo de progreso
                        loading.dismiss();
                        //Mostrando el mensaje de la respuesta
                        Toast.makeText(ProfilePage.this, s , Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Descartar el diálogo de progreso
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(ProfilePage.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Convertir bits a cadena
                String imagen = getStringImagen(bitmap);

                //Creación de parámetros
                Map<String,String> params = new Hashtable<String, String>();

                //Agregando de parámetros
                params.put(KEY_IMAGEN, imagen);
                params.put(username, username);

                //Parámetros de retorno
                return params;
            }
        };

        //Creación de una cola de solicitudes
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Agregar solicitud a la cola
        requestQueue.add(stringRequest);
    }

   public void updateProfile(View view)
    {
        name = boxName.getText().toString();
        age = boxAge.getText().toString();
        height = boxHeight.getText().toString();
        weight = boxWeight.getText().toString();

        String method = "profile";

        stuffy.putString("age", age);
        stuffy.putString("height", height);
        stuffy.putString("weight", weight);


        BackgroundTask bgTask = new BackgroundTask(this);
        bgTask.execute(method, username, age, height, weight);


    }

    /*public void showProfile(View view)
    {
        name = boxName.getText().toString();
        age = boxAge.getText().toString();
        height = boxHeight.getText().toString();
        weight = boxWeight.getText().toString();

        String method = "showProfile";

        BackgroundTask bgTask = new BackgroundTask(this);
        bgTask.execute(method, username, age, height, weight);

    }
    */




}
