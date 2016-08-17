package com.dityish.apratim2k16;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ContactUs extends Fragment {


    String[] CONTACT_HEAD={"Publications & Correspondence",
            "Website and Online registration",

            "Sponsorship & Marketing",

            "Events & Scheduling",

            "Publicity & Online Partnership" ,


            "Hospitality & Accommodation" ,

            "Department of Stage Controls"};
    String[] CONTACT_NAME={"Maheep Tripathi",
            "Siddhant Tuli",
            "Ojas Malpani",
            "Krishna Akhil",
            "Krishna Chaitanya",
            "Saketh Boddu",
            "Akshansh Deva"};

    String[] CONTACT_NUMBER= {"+91-7240105156",
            "+91-9810885196",
            "+91-9772231910",
            "+91-8441000746",
            "+91-9660570469",
            "+91-9772048822",
            "+91-8741064850"};

    String[] CONTACT_EMAIL={"pcr@bits-oasis.org",
            "webmaster@bits-oasis.org",
            "sponsorship@bits-oasis.org",
            "controls@bits-oasis.org",
            "adp@bits-oasis.org",
            "recnacc@bits-oasis.org",
            "stagecontrols@bits-oasis.org"};



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
                        mail.putExtra(Intent.EXTRA_TEXT, "\n\n\n\n\n\n\n\nSent from the official Oasis 2015 App");

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
        return view;
    }
}
