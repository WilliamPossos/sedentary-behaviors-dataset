package com.example.william.data_set.measurescollection;

import android.app.Activity;


import java.lang.ref.WeakReference;

/**
 * Created by william on 9/09/16.
 */
public interface MeasuresCollRepository {
    void saveObjectiveData();
    void dataConsent(WeakReference<Activity> reference);
    void disconnect();
    void registerSensors();
    void registerMobileSensors();
    void unregisterSensors();

    void deleteRecords();

    void defineActivity(String posture, String compoundAct,String userName);
}
