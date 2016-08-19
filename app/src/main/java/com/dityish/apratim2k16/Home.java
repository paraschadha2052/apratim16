package com.dityish.apratim2k16;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Home extends android.support.v4.app.Fragment implements SHARED_CONSTANTS {

    AsyncTask slide;
    ImageView homeLayout1;
    ImageView homeLayout2;

    int index;
    int c=0;
    Handler handler;
    Runnable changeImage;
    Animation anim2;
    Animation anim1;
    ArrayList<EventModel> eventsNowList = new ArrayList<EventModel>();
    Database db;
    TextView noEvents;
    ListView homelist;
    String[] name;
    String[] location;
    String[] inTime;
    String[] date;
    String[] desc;
    String[] time;
    Date[] reminderStart;
    Date[] reminderEnd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, container, false);

        homeLayout1 = (ImageView) view.findViewById(R.id.homeLayout1);
        homeLayout2 = (ImageView) view.findViewById(R.id.homeLayout2);
        noEvents = (TextView) view.findViewById(R.id.noEvents);
        homelist= (ListView) view.findViewById(R.id.homelist);
        db=new Database(getActivity());
        Window window = getActivity().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.home4));
        homelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                eventDetail(position);
            }
        });

        final String msg="Success";
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                eventsNowList= db.getEventsNowList();
                return msg;
            }

            @Override
            protected void onPostExecute(String s) {
                Log.d("CheckPoint", "Checkpoint at onpostexecute in home reached");
                DateFormat df2 = new SimpleDateFormat("hh:mm a");
                DateFormat df = new SimpleDateFormat("dd  MMM");
                 name=new String[eventsNowList.size()];
                location=new String[eventsNowList.size()];
                 inTime=new String[eventsNowList.size()];
                date=new String[eventsNowList.size()];
                time=new String[eventsNowList.size()];
                desc=new String[eventsNowList.size()];
                reminderStart=new Date[eventsNowList.size()];
                reminderEnd=new Date[eventsNowList.size()];
                for (int i=0;i<eventsNowList.size();i++)
                {
                    name[i]=eventsNowList.get(i).getName();
                    location[i]=eventsNowList.get(i).getLocation();
                    inTime[i]=eventsNowList.get(i).getTimeLeft();
                    desc[i]=eventsNowList.get(i).getDesc();
                    date[i]=df.format(eventsNowList.get(i).getStart());
                    time[i]=df2.format(eventsNowList.get(i).getStart());
                    reminderStart[i]=eventsNowList.get(i).getStart();
                    reminderEnd[i]=eventsNowList.get(i).getEnd();
                    Log.d("Time Left",inTime[i]);
                }


                if (eventsNowList.size() == 0) {
                    noEvents.setVisibility(View.VISIBLE);
                    if(!isNetworkAvailable())
                    {
                        Toast.makeText(getActivity().getBaseContext(),"No Network Connection to refresh schedule", Toast.LENGTH_SHORT).show();
                    }

                } else {


                    ListAdapter custom = new CustomList(getActivity(),name , location, inTime);
                    homelist.setAdapter(custom);
                }

            }
        }.execute(null, null, null);



        noEvents.setVisibility(View.INVISIBLE);



                backgroundSwitcher();

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        jerryAnimation anim = new jerryAnimation(view,height,width);
        anim.con_anime().start();

        return view;
    }

    public void backgroundSwitcher()
    {

        handler= new Handler();
        anim1= AnimationUtils.loadAnimation(getActivity(), R.anim.home_fade_out);
        anim2= AnimationUtils.loadAnimation(getActivity(), R.anim.home_fade);

        changeImage = new Runnable() {

            @Override
            public void run() {
                index=c%5;

                anim2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                        homeLayout2.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        c++;
                        handler.postDelayed(changeImage, 4000);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });


                anim1.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        c++;
                        index = c % 5;
                        homeLayout2.setVisibility(View.INVISIBLE);
                        homeLayout2.setBackgroundResource(HOME_BACK_PICS[index]);

                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                homeLayout2.startAnimation(anim2);
                                Window window = getActivity().getWindow();
                                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                                window.setStatusBarColor(ContextCompat.getColor(getActivity(), HOME_BACK_COLORS[index]));
                            }
                        }, 2500);
                    }
                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                homeLayout1.setBackgroundResource(HOME_BACK_PICS[index]);
                homeLayout2.startAnimation(anim1);
                Window window = getActivity().getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(ContextCompat.getColor(getActivity(), HOME_BACK_COLORS[index]));

            }
        };

        slide= new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg="success";

                handler.postDelayed(changeImage, 2500);

                return msg;
            }

        }.execute(null, null, null);

    }

    public void eventDetail(int position)
    {
        Intent i=new Intent(getActivity(),EventDetails.class);
        i.putExtra("name",name[position]);
        i.putExtra("location",location[position]);
        i.putExtra("time",time[position]);
        i.putExtra("desc",desc[position]);
        i.putExtra("date",date[position]);
        i.putExtra("reminderStart",reminderStart[position].getTime());
        i.putExtra("reminderEnd",reminderEnd[position].getTime());
        startActivity(i);

    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
