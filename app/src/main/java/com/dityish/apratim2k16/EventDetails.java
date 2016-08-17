package com.dityish.apratim2k16;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class EventDetails extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbarLayout;
    String name;
    String location;
    String desc;
    long reminderStart;
    long reminderEnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        TextView eventLocation=(TextView)findViewById(R.id.eventLocation);
        TextView eventDetails=(TextView)findViewById(R.id.eventDetails);
        TextView eventTime=(TextView)findViewById(R.id.eventTime);

        Bundle details=getIntent().getExtras();
         name=details.getString("name");
         location=details.getString("location");
        String time=details.getString("time");
        String date=details.getString("date");
         desc=details.getString("desc");
        reminderStart=details.getLong("reminderStart");
        reminderEnd=details.getLong("reminderEnd");

        eventDetails.setText(desc+"\n\n\n ");
        eventLocation.setText(location);
        eventTime.setText(date + "  " + time);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        collapsingToolbarLayout.setContentScrimColor(R.color.dark_theme);
        collapsingToolbarLayout.setStatusBarScrimColor(R.color.dark_theme);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.white));
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
        collapsingToolbarLayout.setTitle(name);

    }

    public void setReminder(View view)
    {
        long startMillis=reminderStart;
        long endMillis=reminderEnd;

        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra(CalendarContract.Events.TITLE, name);
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                startMillis);
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION,location);

        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                endMillis);

        intent.putExtra(CalendarContract.Events.ALL_DAY, false);// periodicity
        intent.putExtra(CalendarContract.Events.DESCRIPTION,desc);
        startActivity(intent);
    }
    }

