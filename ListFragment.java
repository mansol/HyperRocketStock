package com.example.oem.hyperrocketstock;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.whocares.hyperrocketstock.R;

import java.util.zip.Inflater;

public class ListFragment extends Fragment {

    private static final String TAG = ListFragment.class.getSimpleName();
    private ListView mListView;
    private ListSelectedListener mParentListSelectedListener;
    public Boolean mInitialCreate;
    
    StockAdapter<Stock> adapter = null;
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        Log.v(TAG, "onAttach");
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null && parentFragment instanceof ListSelectedListener){
            mParentListSelectedListener = (ListSelectedListener) parentFragment;
        }
        else if (activity != null && activity instanceof ListSelectedListener){
            mParentListSelectedListener = (ListSelectedListener) activity;
        }
        else if (mParentListSelectedListener == null){
            Log.w(TAG, "onAttach: neither the parent fragment or parent activity implement ListSelectedListener," +
                    " image selections will not be communicated to other components ");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate: savedInstanceState " +
                (savedInstanceState == null ? "==" : "!=") + "null");
        if (savedInstanceState != null){
            mInitialCreate = false;
        } else {
            mInitialCreate = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.list_frag, container, false);

        mListView = (ListView)rootView.findViewById(android.R.id.list);
        adapter = new StockAdapter<Stock>(getActivity(), R.layout.stock, Main.pubStocks);
        mListView.setAdapter(adapter);
        return rootView;
    }

    public void setListSelected(Stock stockItem, int position){
        Log.d(TAG, "setImageSelected: stock = " + stockItem.getName() + " position = " + position);
        if (isResumed()){
            mListView.setItemChecked(position, true);
        }
    }
}
