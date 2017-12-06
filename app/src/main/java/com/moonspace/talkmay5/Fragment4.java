package com.moonspace.talkmay5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.ToggleButton;

/**
 * Created by eugenemoon on 2016. 8. 16..
 */
public class Fragment4 extends android.support.v4.app.Fragment{
    private static final String ARG_SECTION_NUMBER = "section_number";
    WebView radioView;

    public Fragment4() {
    }
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Fragment4 newInstance(int sectionNumber) {
        Fragment4 fragment = new Fragment4();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment4, container, false);
        radioView = (WebView)rootView.findViewById(R.id.webview1);
        WebSettings webSettings = radioView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        radioView.setWebViewClient(new MyWebViewClient());
        openURL();
        //WebSettings webSettings = radioView.getSettings();
        //webSettings.setJavaScriptEnabled(true);
        return rootView;
    }
    private void openURL(){
        radioView.loadUrl("https://soundcloud.com/uttalktalk");
        radioView.requestFocus();
    }
}