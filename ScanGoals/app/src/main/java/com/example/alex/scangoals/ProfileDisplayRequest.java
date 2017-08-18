package com.example.alex.scangoals;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ProfileDisplayRequest extends StringRequest {
    private static final String ProfileView = "https://camelbackspartans.com/scangoals/ProfileView.php";
    private Map<String, String> params;
    // checks with the data base
    public ProfileDisplayRequest(String username, String age, Response.Listener<String> listener) {
        super(Method.POST, ProfileView, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("age", age);
        //params.put("height", height);
        //params.put("weight", weight);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
