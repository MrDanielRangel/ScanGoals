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
    public LogToJournalRequest(String input1, String input2, String input3, String username, Response.Listener<String> listener)
    {
        super(Method.POST, JOURNAL_URL, listener, null);
        final String endInput = input1 + " " + input2 + " " + input3;
        params = new HashMap<>();
        params.put("userInput", endInput);
       // params.put("input1", input1);
       // params.put("input2", input2);
       // params.put("input3", input3);
        params.put("username", username);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
