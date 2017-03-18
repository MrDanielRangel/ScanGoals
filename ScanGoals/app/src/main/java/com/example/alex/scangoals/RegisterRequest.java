package com.example.alex.scangoals;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_URL = "https://camelbackspartans.com/scangoals/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String username, String password, Response.Listener<String> listener){
        //send data to PHP
        super(Method.POST, REGISTER_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
