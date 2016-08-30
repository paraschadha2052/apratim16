package com.dityish.apratim2k16;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
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

public class ProfShow extends android.support.v4.app.Fragment implements SHARED_CONSTANTS {

    Bitmap[] drw;

    Database db;
    String[] name;
    String[] location;
    String[] date;
    String[] desc;
    String[] time;
    Date[] reminderStart;
    Date[] reminderEnd;
    ListView profList;
    ArrayList<EventModel> profShows = new ArrayList<EventModel>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_prof_show, container, false);
        profList=(ListView) view.findViewById(R.id.ProfList);
        profList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                profDetails(position);
            }
        });
/*
        drw=new Bitmap[PROF_PROFILE_PIC.length];

        db=new Database(getActivity());


        final String msg="Success";
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                profShows= db.getProfShows();
                for(int i=0;i<PROF_PROFILE_PIC.length;i++)
                {
                    Bitmap pics= BitmapFactory.decodeResource(getResources(), PROF_PROFILE_PIC[i]);
                    pics=getRoundedShape(pics);
                    drw[i]=pics;
                }

                return msg;
            }

            @Override
            protected void onPostExecute(String s) {
                DateFormat df2 = new SimpleDateFormat("hh:mm a");
                DateFormat df = new SimpleDateFormat("dd MMM");
                name=new String[profShows.size()];
                location=new String[profShows.size()];
                time=new String[profShows.size()];
                date=new String[profShows.size()];
                time=new String[profShows.size()];
                desc=new String[profShows.size()];
                reminderStart=new Date[profShows.size()];
                reminderEnd=new Date[profShows.size()];
                for (int i=0;i<profShows.size();i++)
                {
                    name[i]=profShows.get(i).getName();
                    location[i]=profShows.get(i).getLocation();
                    desc[i]=profShows.get(i).getDesc();
                    date[i]=df.format(profShows.get(i).getStart());
                    time[i]=df2.format(profShows.get(i).getStart());
                    reminderStart[i]=profShows.get(i).getStart();
                    reminderEnd[i]=profShows.get(i).getEnd();
                    Log.d("Time Left",time[i]);
                }

                if (profShows.size() == 0) {
                   Log.d("dityish says","ProfShows not received");

                } else {
                    ListAdapter custom = new ProfAdapter(getActivity(),name,drw);
                    profList.setAdapter(custom);
                }
            }
        }.execute(null, null, null);
*/
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        jerryAnimation anim = new jerryAnimation(view,height,width);
        anim.con_anime().start();

        return view;
    }

    public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
        int targetWidth = scaleBitmapImage.getWidth();
        int targetHeight = scaleBitmapImage.getHeight();
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                targetHeight, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidth - 1) / 2,
                ((float) targetHeight - 1) / 2,
                (Math.min(((float) targetWidth),
                        ((float) targetHeight)) / 2),
                Path.Direction.CCW);

        canvas.clipPath(path);
        Bitmap sourceBitmap = scaleBitmapImage;
        canvas.drawBitmap(sourceBitmap,
                new Rect(0, 0, sourceBitmap.getWidth(),
                        sourceBitmap.getHeight()),
                new Rect(0, 0, targetWidth, targetHeight), null);
        return targetBitmap;
    }

    public void profDetails(int profNum)
    {
        Intent i=new Intent(getActivity(),ProfDetails.class);
        i.putExtra("profNum",profNum);
        i.putExtra("name",name[profNum]);
        i.putExtra("location",location[profNum]);
        i.putExtra("time",time[profNum]);
        i.putExtra("desc",desc[profNum]);
        i.putExtra("date",date[profNum]);
        i.putExtra("reminderStart",reminderStart[profNum].getTime());
        i.putExtra("reminderEnd",reminderEnd[profNum].getTime());
        startActivity(i);
    }
}
