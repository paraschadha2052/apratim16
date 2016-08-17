package com.dityish.apratim2k16;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Nitish on 9/3/2015.
 */
public class ContactAdapter extends ArrayAdapter<String> implements SHARED_CONSTANTS {
    private LayoutInflater mInflater;
    private final Context context;
    int t=0;
    String[] name;
    String[] number;
    String[] email;
    public ContactAdapter(Context context, String[] item, String[] name, String[] number, String[] email)
    {
        super(context,R.layout.custom_contact, item);
        this.context=context;
        this.name=name;
        this.number=number;
        this.email=email;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Holder holder;
        String str=getItem(position);
        final Animation anim= AnimationUtils.loadAnimation(getContext(), R.anim.home_fade);
        if(convertView==null)
        {
            mInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=mInflater.inflate(R.layout.custom_contact,parent,false);
            holder = new Holder();
            holder.head=(TextView) convertView.findViewById(R.id.head);
            holder.details=(TextView) convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        }
        else
        {
            holder=(Holder) convertView.getTag();
        }

        holder.head.setText(str);
        holder.details.setText(name[position]+"\n"+number[position]+"\n"+email[position]);

        if(t<position) {
            convertView.startAnimation(anim);
        }

        t=position;
        return convertView;
    }

    public class Holder
    {
        public TextView head;
        public TextView details;
    }
}
