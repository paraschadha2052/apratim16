package com.dityish.apratim2k16;


import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Blog extends android.support.v4.app.Fragment {
    ProgressBar loading;
    boolean check=true;
    WebView web;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_blog, container, false);

      loading=(ProgressBar) view.findViewById(R.id.loading);
        if(!isNetworkAvailable())
        {
            Toast.makeText(getActivity(), "No Internet Connectivity", Toast.LENGTH_LONG).show();
            return view;
        }
        loading.setMax(100);
        web=(WebView) view.findViewById(R.id.web);
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setAllowFileAccess(true);
        WebSettings ws = web.getSettings();
        ws.setSaveFormData(true);
        ws.setSavePassword(true);
        web.setWebViewClient(new mwebViewClient());

        web.loadUrl("http://bits-oasis.org/blogs/english/");

        return view;
    }

    private class mwebViewClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            loading.setProgress(40);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

        }

        @Override
        public void onPageFinished(WebView view, String url) {
                loading.setVisibility(View.GONE);
        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
