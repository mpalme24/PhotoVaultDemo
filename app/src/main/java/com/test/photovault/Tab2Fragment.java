package com.test.photovault;

import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Tab2Fragment extends Fragment {
    private static final String TAG = "Tab2Fragment";

    public static TextView fetcheddata;
    public static ImageView moonIcon;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.tab2_fragment, container, false);

        fetcheddata = (TextView)RootView.findViewById(R.id.fetcheddata);
        moonIcon = (ImageView)RootView.findViewById(R.id.moonIcon);

        //Main Logic of Moon Phase
        fetchData process = new fetchData();
        process.execute();

        return RootView;
    }


}
