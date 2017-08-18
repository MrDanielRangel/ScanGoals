package com.example.alex.scangoals;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * Created by santos on 8/15/17.
 */

public class BackgroundTask extends AsyncTask<String, Void, String>
{

    Context ctx;
    AlertDialog alert;

    public static Bundle bgStuff = new Bundle();

    public String megaData;

    BackgroundTask(Context ctx)
    {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute()
    {
        alert = new AlertDialog.Builder(ctx).create();
        alert.setTitle("Profile");

    }

    @Override
    protected String doInBackground(String... params)
    {

        String profile_url = "https://camelbackspartans.com/scangoals/Profile.php";
        String showProfile_url = "https://camelbackspartans.com/scangoals/ProfileView.php";

        String method = params[0];
        if(method.equals("profile"))
        {
            String username = params[1];
            String age = params[2];
            String height = params[3];
            String weight = params[4];

            try {
                URL url = new URL(profile_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bWritter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

                //write information to buffer wrirtter
                String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("age", "UTF-8") + "=" + URLEncoder.encode(age, "UTF-8") + "&" +
                        URLEncoder.encode("height", "UTF-8") + "=" + URLEncoder.encode(height, "UTF-8") + "&" +
                        URLEncoder.encode("weight", "UTF-8") + "=" + URLEncoder.encode(weight, "UTF-8");

                bWritter.write(data);
                bWritter.flush();
                bWritter.close();
                OS.close();

                InputStream IS = httpURLConnection.getInputStream();
                IS.close();

                return "Profile Updated Successfully!";


            }catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        else if(method.equals("showProfile"))
        {
            String username = params[1];
            String age = params[2];
            String height = params[3];
            String weight = params[4];

            try {
                URL url = new URL(showProfile_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                /*String data = URLEncoder.encode("username", "UTF-8") +"="+URLEncoder.encode(username, "UTF-8")+"&"+
                        URLEncoder.encode("age", "UTF-8") +"="+URLEncoder.encode(age, "UTF-8")+"&"+
                        URLEncoder.encode("height", "UTF-8") +"="+URLEncoder.encode(height, "UTF-8")+"&"+
                        URLEncoder.encode("weight", "UTF-8") +"="+URLEncoder.encode(weight, "UTF-8");*/
                String showUsername = URLEncoder.encode("username", "UTF-8") +"="+URLEncoder.encode(username, "UTF-8");
                String showAge = URLEncoder.encode("age", "UTF-8") +"="+URLEncoder.encode(age, "UTF-8");
                String showHeight = URLEncoder.encode("height", "UTF-8") +"="+URLEncoder.encode(height, "UTF-8");
                String showWeight = URLEncoder.encode("weight", "UTF-8") +"="+URLEncoder.encode(weight, "UTF-8");

                //bufferedWriter.write(data);
                bufferedWriter.write(showUsername+"&"+showAge+"&"+showHeight+"&"+showWeight);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                //input stream
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";
                while((line = bufferedReader.readLine()) != null)
                {
                    response += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                megaData = response;

                return response;

            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result)
    {

        if(result.equals("Profile Updated Successfully!")) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            //ProfilePage.boxAge.setText(megaData);
        }

        else
            {
                alert.setMessage(result);
                alert.show();
            }


    }

}
