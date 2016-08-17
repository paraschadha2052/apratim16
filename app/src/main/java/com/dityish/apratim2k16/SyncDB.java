package com.dityish.apratim2k16;

import android.content.Context;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by jai on 21/10/15.
 */
public class SyncDB {

    public static void refreshEvent(Context context) {

        final Database db = new Database(context);


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");
        query.setLimit(1000);

        query.findInBackground(new FindCallback<ParseObject>() {

            @SuppressWarnings("unchecked")
            @Override
            public void done(List<ParseObject> eventList, ParseException e) {
                int c=0;
                if (e == null) {
                    for (ParseObject parseEvent : eventList) {
                        int check=0;

                        boolean isAllDay=false;
                       if (parseEvent.getBoolean("isProfShow"))
                       {
                           check=1;
                       }
                            EventModel event = new EventModel(parseEvent.getObjectId(), parseEvent.getString("eventName").trim(),
                                    parseEvent.getString("location"), parseEvent.getDate("start"), parseEvent.getDate("end"), parseEvent.getString("desc"), isAllDay,check);
//                        Log.d("Nitish", "neo");
                        db.addEvent(event);

                        c++;
                        Log.d("Event name" +c,parseEvent.getString("eventName"));
                    }

                }
                else
                    Log.d("Jay", "Jay");
            }
        });
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
