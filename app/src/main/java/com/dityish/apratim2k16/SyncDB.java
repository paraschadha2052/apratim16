package com.dityish.apratim2k16;

import android.content.Context;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class SyncDB implements SHARED_CONSTANTS {

    public static void refreshEvent(Context context)  {

        final Database db = new Database(context);


        try {
            JSONObject jsonRootObject = new JSONObject(jsonEvents);

            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonRootObject.optJSONArray("Events");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            //Iterate the jsonArray and print the info of JSONObjects

            int c=0;
            for(int i=0; i < jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int check=0;
                boolean isAllDay=false;
                if (jsonObject.getBoolean("isProfShow"))
                {
                    check=1;
                }

                Date start=dateFormat.parse(jsonObject.getString("start")),end=dateFormat.parse(jsonObject.getString("end"));
                Log.d("adnrs96" ,"Reached Check 1");
                EventModel event = new EventModel(Integer.toString(jsonObject.getInt("id")), jsonObject.getString("eventName").trim(),
                        jsonObject.getString("location"),start,
                        end, jsonObject.getString("desc"), isAllDay,check);
                db.addEvent(event);
                c++;
                Log.d("Event name" +c,jsonObject.getString("eventName"));
            }
        } catch (Exception e) {
            Log.d("adnrs96" ,"Reached Exception in events syncDB");
        }



    }

}
