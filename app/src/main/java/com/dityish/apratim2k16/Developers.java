package com.dityish.apratim2k16;


import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;


public class Developers extends android.support.v4.app.Fragment {

    String[] DEVELOPER_NAMES={"Paras Chadha","Rishabh Malhotra","Akanksha Kumari","Aditya Bansal"};
    int [] DEVELOPER_PICS={R.drawable.profile,R.drawable.profile,R.drawable.profile,R.drawable.profile};
    String[] DEVELOPER_CONTRI={"UI/UX & Back-end Developer","Graphic Designer","Back-end Developer","UX Developer"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_developers, container, false);

        ListView developerList=(ListView) view.findViewById(R.id.developerList);
        Bitmap[] drw=new Bitmap[9];
        for(int i=0;i<DEVELOPER_NAMES.length;i++)
        {
            Bitmap pics= BitmapFactory.decodeResource(getResources(), DEVELOPER_PICS[i]);
            pics=getRoundedShape(pics);
            drw[i]=pics;
        }
        ListAdapter custom = new DeveloperAdapter(getActivity(), DEVELOPER_NAMES, drw,DEVELOPER_CONTRI);
        developerList.setAdapter(custom);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        jerryAnimation anim = new jerryAnimation(view,height,width);
        anim.con_anime().start();
        return view;
    }

    public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
        int targetWidth = scaleBitmapImage.getWidth();
        int targetHeight = scaleBitmapImage.getHeight();
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                targetHeight, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidth - 1) / 2,
                ((float) targetHeight - 1) / 2,
                (Math.min(((float) targetWidth),
                        ((float) targetHeight)) / 2),
                Path.Direction.CCW);

        canvas.clipPath(path);
        Bitmap sourceBitmap = scaleBitmapImage;
        canvas.drawBitmap(sourceBitmap,
                new Rect(0, 0, sourceBitmap.getWidth(),
                        sourceBitmap.getHeight()),
                new Rect(0, 0, targetWidth, targetHeight), null);
        return targetBitmap;
    }
}
