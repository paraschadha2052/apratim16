package com.dityish.apratim2k16;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class splash extends AppCompatActivity {

    String test="blah";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final ImageView image=(ImageView) findViewById(R.id.image);

        Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.loading);

        anim1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Thread sync=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SyncDB.refreshEvent(getApplicationContext());
                    }

                });
                sync.setPriority(Thread.MAX_PRIORITY);
                sync.start();


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        image.startAnimation(anim1);

        final Intent i = new Intent(this, MainHome.class);

        //Setting up a delay
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                startActivity(i);
            }
        }, 8000);

    }


}

