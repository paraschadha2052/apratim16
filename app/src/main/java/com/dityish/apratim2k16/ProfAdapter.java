package com.dityish.apratim2k16;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Nitish on 10/19/2015.
 */
public class ProfAdapter extends ArrayAdapter<String> {

    private LayoutInflater mInflater;
    private final Context context;
    Bitmap[] ProfPic;

    public ProfAdapter(Context context, String[] item, Bitmap[] ProfPic)
    {
        super(context,R.layout.custom_prof, item);
        this.context=context;
        this.ProfPic=ProfPic;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String str= getItem(position);
        final Holder holder;

        if(convertView==null)
        {
            mInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=mInflater.inflate(R.layout.custom_prof,parent,false);
            holder = new Holder();
            holder.profName=(TextView) convertView.findViewById(R.id.profName);
            holder.ProfPicture=(ImageView) convertView.findViewById(R.id.ProfPicture);
            convertView.setTag(holder);
        }
        else
        {
            holder=(Holder) convertView.getTag();
        }

        holder.profName.setText(str);
        holder.ProfPicture.setImageBitmap(ProfPic[position]);

        return convertView;
    }
    public class Holder {
        public TextView profName;
        public ImageView ProfPicture;
    }
}
