package com.example.william.data_set.measurescollection;

import android.app.Activity;

import com.example.william.data_set.measurescollection.events.MeasuresEvent;

import java.lang.ref.WeakReference;

/**
 * Created by william on 8/09/16.
 */
public interface MeasuresCollPresenter {
    void onPause();
    void onResume();
    void onCreate(WeakReference<Activity> reference);
    void onDestroy();

    void initDataStorage(String posture, String compoundAct,String userName);
    void restartDataStorage();

    void restartCapture();
    void validateData();

    void initTimer();
    void finishCollection();

    void onEventMainThread(MeasuresEvent event);
}
