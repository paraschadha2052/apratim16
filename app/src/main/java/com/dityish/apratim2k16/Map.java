package com.dityish.apratim2k16;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Map extends android.support.v4.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_map, container, false);

        String HTML="<html><body><img src=\"images/oasis_map.jpg\" /></body></html>";

        WebView web = (WebView) view.findViewById(R.id.web);
        web.getSettings().setAllowFileAccess(true);
        web.getSettings().setSupportZoom(true);
        WebSettings ws = web.getSettings();
        ws.setSaveFormData(true);
        ws.setJavaScriptEnabled(true);
        ws.setSavePassword(true);
        ws.setBuiltInZoomControls(true);
        ws.setSupportZoom(true);
        ws.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        ws.setLoadWithOverviewMode(true);
        ws.setUseWideViewPort(true);
        ws.setAppCacheMaxSize(2048 * 2048);
        ws.setAppCachePath(getActivity().getCacheDir().getAbsolutePath());
        ws.setAppCacheEnabled(true);

        web.setWebViewClient(new mwebViewClient());

        web.loadDataWithBaseURL("file:///android_asset/", HTML, "text/html", "utf-8", null);

        Toast.makeText(getActivity(), "Fetching the Map .....", Toast.LENGTH_SHORT).show();

        return view;
    }
    private class mwebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

    }

}
