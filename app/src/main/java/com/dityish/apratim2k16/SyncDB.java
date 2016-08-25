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


public class SyncDB {

    public static void refreshEvent(Context context) {

        final Database db = new Database(context);

        String jsonEvents = "{Events:[\n" +
                "  {\n" +
                "    id:1,\n" +
                "    eventName:\"Hackathon\",\n" +
                "    location:\"CCET\",\n" +
                "    start:\"2015-10-29T11:23\",\n" +
                "    end:\"2015-10-29T12:23\",\n" +
                "    desc:\"HEre goes the Description\",\n" +
                "    isProfShow:FALSE\n" +
                "  }\n" +
                "  ]}";
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

    public static void refreshResult(Context context) {

        final Database db = new Database(context);


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Results");

        query.setLimit(300);
        query.findInBackground(new FindCallback<ParseObject>() {

            @SuppressWarnings("unchecked")
            @Override
            public void done(List<ParseObject> resultList, ParseException e) {

                if (e == null) {
                    for (ParseObject parseResult : resultList) {

                        ResultModel result = new ResultModel(parseResult.getObjectId(), parseResult.getString("eventName"),
                                parseResult.getUpdatedAt(), parseResult.getString("result"));

                        db.addResult(result);
                    }

                }
            }
        });
    }

    public static void refreshSponsors(Context context) {

        final Database db = new Database(context);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Sponsors");

        query.findInBackground(new FindCallback<ParseObject>() {

            @SuppressWarnings("unchecked")
            @Override
            public void done(List<ParseObject> sponsorsList, ParseException e) {

                if (e == null) {
                    for (ParseObject parseResult : sponsorsList) {

                        SponsorsModel sponsor = new SponsorsModel(parseResult.getObjectId(), parseResult.getString("heading"),
                                parseResult.getNumber("PR").intValue(), parseResult.getString("URL"));

                        Log.d("URL",parseResult.getString("URL"));
                        db.addSponsor(sponsor);
                    }

                }
            }
        });
    }





}
