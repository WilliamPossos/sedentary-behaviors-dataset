package com.example.william.data_set;

import android.app.Application;
import android.content.Context;

import com.estimote.sdk.EstimoteSDK;
import com.example.william.data_set.domain.GreenDaoHelper;


/**
 * Created by william on 5/09/16.
 */
public class AndroidCollectAplication extends Application{
    private DaoSession daoSession;
    private static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        setupBeacons();
        setupGreenDAO();
    }

    private void setupBeacons() {
        EstimoteSDK.initialize(
                getApplicationContext(),
                "williamstibent-hotmail-com-lwh",
                "74246db45e36cd61a2c6eaff3cfb1324");
    }

    private void setupGreenDAO() {
        GreenDaoHelper.getInstance();
    }
    public DaoSession getDaoSession() {
        return daoSession;
    }
    public static Context getAppContext() {
        return mContext;
    }
}
