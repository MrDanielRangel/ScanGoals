package com.example.alex.scangoals;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class userInput extends StringRequest{

    private static final String USERINPUT_URL = "https://camelbackspartans.com/scangoals/userInput.php";
    private Map<String, String> params;
    // checks with the data base
    public userInput(String userInput, Response.Listener<String> listener) {
        super(Method.POST, USERINPUT_URL, listener, null);
        params = new HashMap<>();
        params.put("userInput", userInput);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
