package com.dityish.apratim2k16;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ContactUs extends Fragment {


    String[] CONTACT_HEAD={"lorem ipsum",
            "lorem ipsum",

            "lorem ipsum",

            "lorem ipsum",

            "lorem ipsump" ,


            "lorem ipsum" ,

            "lorem ipsum"};
    String[] CONTACT_NAME={"lorem ipsum",
            "lorem ipsum",
            "lorem ipsum",
            "lorem ipsum",
            "lorem ipsum",
            "lorem ipsum",
            "lorem ipsum"};

    String[] CONTACT_NUMBER= {"+91-420",
            "+91-420",
            "+91-420",
            "+91-420",
            "+91-420",
            "+91-420",
            "+91-420"};

    String[] CONTACT_EMAIL={"lorem_ipsum@lorem_ipsum.com",
            "lorem_ipsum@lorem_ipsum.com",
            "lorem_ipsum@lorem_ipsum.com",
            "lorem_ipsum@lorem_ipsum.com",
            "lorem_ipsum@lorem_ipsum.com",
            "lorem_ipsum@lorem_ipsum.com",
            "lorem_ipsum@lorem_ipsum.com"};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.activity_contact_us, container, false);

        ListView contactList=(ListView) view.findViewById(R.id.contactList);
        ListAdapter custom= new ContactAdapter(getActivity() ,CONTACT_HEAD,CONTACT_NAME,CONTACT_NUMBER,CONTACT_EMAIL);
        contactList.setAdapter(custom);

        Toast.makeText(getActivity(),"Click on a person to call or send an email to him", Toast.LENGTH_LONG).show();

        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.setTitle("Contact");


        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                dialog.setMessage("How would you like to contact " + CONTACT_NAME[position]);

                dialog.setButton2("Call", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent call = new Intent(Intent.ACTION_DIAL);
                        call.setData(Uri.parse("tel:" + CONTACT_NUMBER[position]));
                        startActivity(call);
                    }
                });

                dialog.setButton("Email", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent mail = new Intent(Intent.ACTION_VIEW);
                        mail.setType("plain/text");
                        mail.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                        mail.putExtra(Intent.EXTRA_EMAIL, new String[]{CONTACT_EMAIL[position]});
                        mail.putExtra(Intent.EXTRA_SUBJECT, "");
                        mail.putExtra(Intent.EXTRA_TEXT, "\n\n\n\n\n\n\n\nSent from the official Apratim 2016 App");

                        try {
                            startActivity(Intent.createChooser(mail, "Send mail...(Preferably GMail) ;)"));
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.show();
            }
        });

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        jerryAnimation anim = new jerryAnimation(view,height,width);
        anim.con_anime().start();

        return view;
    }
}
