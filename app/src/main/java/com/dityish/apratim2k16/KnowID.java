package com.dityish.apratim2k16;


import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class KnowID extends android.support.v4.app.Fragment {
    ProgressBar loading;
    EditText email;
    TextView pcode;
    HttpURLConnection con;
    JSONObject json;
    String result;
    String emailID;
    SharedPreferences prefs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_know_id, container, false);
        Button button=(Button) view.findViewById(R.id.button);
        email=(EditText) view.findViewById(R.id.email);
        pcode=(TextView) view.findViewById(R.id.emailID);
        loading=(ProgressBar) view.findViewById(R.id.loading);
        loading.setVisibility(View.INVISIBLE);
        prefs = getActivity().getSharedPreferences("KNOWID", Context.MODE_PRIVATE);
      if(!prefs.getString("email", "").isEmpty())
      {
          pcode.setText("Your  ID : " + prefs.getString("email", ""));
      }



        if(!isNetworkAvailable())
        {
            Toast.makeText(getActivity(),"No internet Connectivity", Toast.LENGTH_LONG).show();
            pcode.setText("No Internet Connectivity");
            return view;
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailID = email.getText().toString().trim();

                final String msg = "Success";
                loading.setVisibility(View.VISIBLE);

                new AsyncTask<Void, Void, String>() {
                    @Override
                    protected String doInBackground(Void... params) {

                        try {
                            URL url = new URL("http://bits-oasis.org/2015/pcode_json/?email=" + emailID);
                            con = (HttpURLConnection) url.openConnection();
                            con.setRequestMethod("GET");
                            con.setRequestProperty("Content-length", "0");
                            con.setUseCaches(false);
                            con.setAllowUserInteraction(false);
                            con.setConnectTimeout(15000);
                            con.setReadTimeout(15000);
                            con.connect();
                            int responseCode = con.getResponseCode();

                            if (responseCode == HttpURLConnection.HTTP_OK) {
                                BufferedReader in = new BufferedReader(new InputStreamReader(
                                        con.getInputStream()));
                                String inputLine;
                                StringBuilder response = new StringBuilder();

                                while ((inputLine = in.readLine()) != null) {
                                    response.append(inputLine);
                                }
                                in.close();
                                json = new JSONObject(response.toString());
                                result = json.getString("pcode");
                                Log.d("Response code", " " + responseCode + " " + response);

                            }
                        } catch (Exception e) {

                            Log.d("Exception", e.toString());
                        }

                        return msg;
                    }

                    @Override
                    protected void onPostExecute(String s) {

                                loading.setVisibility(View.GONE);

                        prefs.edit().putString("email",result ).apply();

                        if (result != null)
                            pcode.setText("Your ID: " + result);
                        else
                            pcode.setText("You have not registered for Oasis 2015");

                    }
                }.execute(null, null, null);

        }


});
        return view;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
