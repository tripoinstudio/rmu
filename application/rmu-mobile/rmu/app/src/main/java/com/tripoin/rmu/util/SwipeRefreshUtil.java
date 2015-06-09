package com.tripoin.rmu.util;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.Toast;

import com.tripoin.rmu.R;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.fragment.impl.FragmentOrderDetail;

/**
 * Created by Ginanjar Aji Sanjaya on 6/6/2015.
 */
public class SwipeRefreshUtil {

    public void onRefreshAction(final SwipeRefreshLayout swipeView, final AsyncTask asyncTask){
        swipeView.setColorScheme(android.R.color.holo_blue_dark,
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_green_light);
        swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeView.setRefreshing(true);
                Log.d("Swipe", "Refreshing List");
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeView.setRefreshing(false);
                        asyncTask.execute();
                        Log.d("Swipe", "Refreshing List Done");
                    }
                }, 3000);
            }
        });
    }

}
