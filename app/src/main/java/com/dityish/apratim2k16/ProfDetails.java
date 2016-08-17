package com.dityish.apratim2k16;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfDetails extends Activity implements SHARED_CONSTANTS{

    CollapsingToolbarLayout collapsingToolbarLayout;
    Window window;
    int profNum;
    String name;
    long reminderStart;
    long reminderEnd;
    String desc;
    String location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_details);
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        TextView profDetails=(TextView) findViewById(R.id.profDetails);
        TextView profDate=(TextView)findViewById(R.id.profDate);
        TextView profLocation=(TextView) findViewById(R.id.profLocation);
        Bundle prof=getIntent().getExtras();
        if(prof==null)
        {
            return;
        }

         profNum=prof.getInt("profNum");
         name=prof.getString("name");
        desc=prof.getString("desc");
        location=prof.getString("location");
        reminderStart=prof.getLong("reminderStart");
        reminderEnd=prof.getLong("reminderEnd");


        TextView profName=(TextView) findViewById(R.id.profName);
        ImageView image=(ImageView) findViewById(R.id.image);
        image.setImageResource(PROF_PICS[profNum]);


        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        collapsingToolbarLayout.setTitle(name);
        profName.setText(name);
        profDetails.setText(prof.getString("desc"));
        profDate.setText(prof.getString("date")+" "+prof.getString("time"));
        profLocation.setText(location);


//        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
//
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        window =getWindow();


//        collapsingToolbarLayout.setContentScrimColor(R.color.toolbar);
//      collapsingToolbarLayout.setStatusBarScrimColor(R.color.toolbar);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), PROF_PICS[profNum]);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int vibrant = palette.getVibrantColor(0x000000);
                int vibrantLight = palette.getLightVibrantColor(0x000000);
                int vibrantDark = palette.getDarkVibrantColor(0x000000);
                collapsingToolbarLayout.setContentScrimColor(vibrant);
                collapsingToolbarLayout.setStatusBarScrimColor(vibrant);
                int currentapiVersion = android.os.Build.VERSION.SDK_INT;
                if (currentapiVersion >= android.os.Build.VERSION_CODES.LOLLIPOP){
                    // Do something for lollipop and above versions
                    window.setStatusBarColor(vibrant);
                }
            }
        });
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
