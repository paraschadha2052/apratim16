package com.dityish.apratim2k16;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by HP on 10/21/2015.
 */
public class Tab3 extends android.support.v4.app.Fragment  {

    ArrayList<EventModel> eventList = new ArrayList<EventModel>();
    ListView tabList;
    Database db;
    String[] name;
    String[] location;
    String[] time;
    String[] desc;
    Date[] reminderStart;
    Date[] reminderEnd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_tab1, container, false);

        db=new Database(getActivity());
        tabList = (ListView) view.findViewById(R.id.tabList);
        final String msg="Success";

        tabList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDescription(position);
            }
        });

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                eventList= db.getEventsList(1446143400000l,1446229799990l);
                return msg;
            }

            @Override
            protected void onPostExecute(String s) {
//                Log.d("Neo", "Neo");
                DateFormat df2 = new SimpleDateFormat("hh:mm a");
                name=new String[eventList.size()];
                location=new String[eventList.size()];
                time=new String[eventList.size()];
                desc=new String[eventList.size()];
                reminderStart=new Date[eventList.size()];
                reminderEnd=new Date[eventList.size()];
                for (int i=0;i<eventList.size();i++)
                {
                    name[i]=eventList.get(i).getName();
                    location[i]=eventList.get(i).getLocation();
                    reminderStart[i]=eventList.get(i).getStart();
                    reminderEnd[i]=eventList.get(i).getEnd();
                    time[i]=df2.format(reminderStart[i]);
                    desc[i]=eventList.get(i).getDesc();
                }

                if (eventList.size() == 0) {
                    Log.d("Neo", "JaiFailed");
//                    if(!isNetworkAvailable())
//                    {
//                        Toast.makeText(getActivity().getBaseContext(), "No Network Connection to refresh schedule", Toast.LENGTH_SHORT).show();
//                    }

                } else {


                    ListAdapter custom = new CustomList(getActivity(), name, location,time);
                    tabList.setAdapter(custom);
                }
            }
        }.execute(null, null, null);


        // Inflate the layout for this fragment
        return view;
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public void showDescription(int position)
    {
        Intent i=new Intent(getActivity(),EventDetails.class);
        i.putExtra("name",name[position]);
        i.putExtra("location",location[position]);
        i.putExtra("time",time[position]);
        i.putExtra("desc",desc[position]);
        i.putExtra("date","30th Oct  ");
        i.putExtra("reminderStart",reminderStart[position].getTime());
        i.putExtra("reminderEnd",reminderEnd[position].getTime());
        startActivity(i);
    }
}
