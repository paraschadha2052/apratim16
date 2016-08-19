package com.dityish.apratim2k16;


import android.app.Fragment;
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
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class Results extends android.support.v4.app.Fragment {

    ArrayList<ResultModel> resultList = new ArrayList<ResultModel>();
    ListView resList;
    Database db;
    String[] name;
    String[] result;
    String[] date;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_results, container, false);
        // Inflate the layout for this fragment

        final TextView noEvents=(TextView) view.findViewById(R.id.noEvents);
        noEvents.setVisibility(View.INVISIBLE);
        db=new Database(getActivity());
        resList = (ListView) view.findViewById(R.id.resList);
        final String msg="Success";

        resList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showResultDescription(position);
            }
        });

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                resultList= db.getResultList();
                return msg;
            }

            @Override
            protected void onPostExecute(String s) {
                Log.d("Neo", "Neo");
                DateFormat df2 = new SimpleDateFormat("dd-MMM hh:mm a");
                name=new String[resultList.size()];
                result=new String[resultList.size()];
                date=new String[resultList.size()];
                for (int i=0;i<resultList.size();i++)
                {
                    name[i]=resultList.get(i).getName();
                    date[i]=df2.format(resultList.get(i).getUpdatedAt());
                    result[i]=resultList.get(i).getResult();
                }


                if (resultList.size() == 0) {
                    noEvents.setVisibility(View.VISIBLE);

                } else {
                    ListAdapter custom = new CustomList(getActivity(), name, null,null);
                    resList.setAdapter(custom);
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

    public void showResultDescription(int position)
    {
        Intent i=new Intent(getActivity(),ResultDetails.class);
        i.putExtra("name",name[position]);
        i.putExtra("result",result[position]);
        i.putExtra("updatedAt",date[position]);
        startActivity(i);
    }
}
