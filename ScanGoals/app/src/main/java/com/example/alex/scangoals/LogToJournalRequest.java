package com.example.alex.scangoals;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class LogToJournalRequest extends StringRequest {

    private static final String JOURNAL_URL = "https://camelbackspartans.com/scangoals/Journal.php";
    private Map<String, String> params;

    public LogToJournalRequest(String userInput, String username, Response.Listener<String> listener) {
        super(Method.POST, JOURNAL_URL, listener, null);
        params = new HashMap<>();
        params.put("userInput", userInput);
        params.put("username", username);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
