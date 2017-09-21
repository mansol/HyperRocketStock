package com.example.oem.hyperrocketstock;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.whocares.hyperrocketstock.R;

public class MyFragment extends Fragment {
    LinearLayout ll;
    View rootView;

    public static MyFragment newInstance(String text){
        MyFragment f = new MyFragment();
        Bundle b = new Bundle();
        b.putString("text", text);
        f.setArguments(b);
        return f;
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.layout_detailfragment, container, false);

        return rootView;
    }


