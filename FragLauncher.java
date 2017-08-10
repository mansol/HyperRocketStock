package com.example.oem.hyperrocketstock;
/**
 * Created by oem on 4/26/17.
 */
        import android.app.ActionBar;
        import android.app.Activity;
        import android.graphics.Color;
        import android.support.v4.app.FragmentTransaction;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentActivity;
        import android.support.v4.app.FragmentManager;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.EditText;
        import android.widget.FrameLayout;
        import android.widget.LinearLayout;
        
public class FragLauncher extends FragmentActivity {
    private static final int CONTENT_VIEW_ID = 10101010;
    public static final int CONTENT_VIEW_ID1 = 10101011;
    public FragmentManager fm = getSupportFragmentManager();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout view = new FrameLayout(this);
        view.setId(CONTENT_VIEW_ID1);
        setContentView(view);
        if (savedInstanceState == null) {
            Fragment newFragment = new ProfitCalcFrag();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(CONTENT_VIEW_ID1, newFragment);
            ft.commit();       
        }
    }
    
    public static class ProfitCalcFrag2 extends Fragment {
        public static int count = 0;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            View v = new View(getActivity());
            v.setBackgroundColor(Color.RED);
            return v;
        }
    }
}


