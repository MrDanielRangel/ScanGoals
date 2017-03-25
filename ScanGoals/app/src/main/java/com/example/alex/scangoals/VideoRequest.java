package com.example.alex.scangoals;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class VideoRequest extends StringRequest {
    private static final String Video_URL = "https://camelbackspartans.com/scangoals/video.php";
    private Map<String, String> params;

    public VideoRequest(String videoKey, Response.Listener<String> listener) {
        super(Method.POST, Video_URL, listener, null);
        params = new HashMap<>();
        params.put("videoKey", videoKey);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
