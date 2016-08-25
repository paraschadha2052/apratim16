package com.dityish.apratim2k16;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


public class MainHome extends AppCompatActivity {

    DrawerLayout drawer;
    Toolbar toolbar;
    boolean exit=false;
    NavigationView nvDrawer;
    int currentapiVersion;
    android.support.v4.app.Fragment fragment=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

         currentapiVersion = android.os.Build.VERSION.SDK_INT;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(currentapiVersion>=21)
        {
            toolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        }
        else {
            toolbar.setBackgroundColor(getResources().getColor(R.color.dark_theme));
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);

        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar ab = getSupportActionBar();

        fragment = new Home_default();

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        drawer.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                        exit = false;
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        nvDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                if(currentapiVersion>=21)
                {
                    toolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                }
                else {
                    toolbar.setBackgroundColor(getResources().getColor(R.color.dark_theme));
                }
                exit=false;
                Window window = getWindow();
                switch (menuItem.getItemId()) {
                    case R.id.nav_first_fragment:
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.home4));
                        fragment = new AboutUs();
                        break;
                    case R.id.nav_second_fragment:
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.home4));
                        fragment = new Schedule();
                        break;
                    case R.id.nav_third_fragment:
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.home4));
                        fragment = new ProfShow();
                        break;
                    case R.id.nav_fifth_fragment:
                        fragment = new Home();
                        break;
                    case R.id.nav_seventh_fragment:
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.home4));
                        fragment = new Map();
                        toolbar.setBackgroundColor(getResources().getColor(R.color.dark_theme));
                        break;
                    case R.id.nav_tenth_fragment:
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.home4));
                        fragment = new ContactUs();
                        break;
                    case R.id.nav_eleventh_fragment:
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.home4));
                        fragment = new Developers();
                        toolbar.setTitle("Developers");
                        break;
                    case R.id.nav_twelfth_fragment:
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.home4));
                        toolbar.setBackgroundColor(getResources().getColor(R.color.dark_theme));
                        fragment = new Sponsors();
                        break;
                    default:
                        break;
                }
                drawer.closeDrawers();
                menuItem.setChecked(true);
                toolbar.setTitle(menuItem.getTitle());

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        if (fragment != null) {
                            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                            android.support.v4.app.FragmentTransaction ft = fragmentManager.beginTransaction();
                            ft.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_right_out, R.anim.slide_right_in, R.anim.slide_left_out);
                            ft.replace(R.id.flContent, fragment).commit();
                        }
                    }
                }, 350);

                return true;
            }
        });

        if (currentapiVersion >=14) {
            ab.setHomeButtonEnabled(true);
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        }

    }
            @Override
            public boolean onOptionsItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case android.R.id.home:
                        drawer.openDrawer(GravityCompat.START);
                        return true;
                    case R.id.refresh:
                        Thread sync=new Thread(new Runnable() {
                            @Override
                            public void run() {
                                SyncDB.refreshEvent(getApplicationContext());
                                SyncDB.refreshResult(getApplicationContext());
                            }
                        });
                        sync.start();
                        return true;
                }
                return true;
            }

            @Override
            public void onBackPressed() {

                if (!drawer.isDrawerOpen(nvDrawer)) {
                        drawer.openDrawer(nvDrawer);
                    Toast.makeText(MainHome.this, "Press Again to Exit", Toast.LENGTH_SHORT).show();
                        exit = true;
                    return;
                    }
                else if(!exit && drawer.isDrawerOpen(nvDrawer)) {
                    drawer.closeDrawers();
                }

                if(exit)
                    System.exit(0);

            }
}
