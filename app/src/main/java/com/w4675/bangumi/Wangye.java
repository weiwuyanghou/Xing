package com.w4675.bangumi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

public class Wangye extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tiaozhuan,container,false);
        WebView webView=view.findViewById(R.id.webview);
        final String getname = getArguments().getString("dizhi");
      //  Toast.makeText(getContext(),getname, Toast.LENGTH_SHORT).show();
        String dizhi="https://m.cndzys.com";
        webView.loadUrl(dizhi+getname);
        return view;
    }
}
