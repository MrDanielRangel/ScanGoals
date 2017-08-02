package com.example.alex.scangoals;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class JournalDisplayRequest extends StringRequest {
    private static final String JournalView = "https://camelbackspartans.com/scangoals/JournalView.php";
    private Map<String, String> params;
    // checks with the data base
    public JournalDisplayRequest(String username, Response.Listener<String> listener) {
        super(Method.POST, JournalView, listener, null);
        params = new HashMap<>();
        params.put("username", username);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
