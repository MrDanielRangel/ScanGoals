package com.example.alex.scangoals;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ProfileRequest extends StringRequest {

    private static final String PROFILE_URL = "https://camelbackspartans.com/scangoals/Profile.php";
    private Map<String, String> params;

    public ProfileRequest(String name, String height, String weight, String age, Response.Listener<String> listener){
        //send data to PHP
        super(Method.POST, PROFILE_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("height", height);
        params.put("weight", weight);
        params.put("age", age);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
