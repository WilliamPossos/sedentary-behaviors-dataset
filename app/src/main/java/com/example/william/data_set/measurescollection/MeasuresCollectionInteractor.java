package com.example.william.data_set.measurescollection;

import android.app.Activity;

import java.lang.ref.WeakReference;

/**
 * Created by william on 9/09/16.
 */
public interface MeasuresCollectionInteractor {
    void seekForConsent(WeakReference<Activity> reference);
    void doObjetiveDataCollection(String posture, String compoundAct,String userName);
    void doRestar();
    void doInterruption();
    void doDisconnect();
}
