package com.example.william.data_set.measurescollection;

import android.app.Activity;

import java.lang.ref.WeakReference;

/**
 * Created by william on 9/09/16.
 */
public class MeasuresCollectionInteractorImpl implements MeasuresCollectionInteractor {
    private MeasuresCollRepository measuresCollRepository;

    public MeasuresCollectionInteractorImpl() {
        this.measuresCollRepository = new MeasuresCollRepositoryImpl();
    }


    @Override
    public void seekForConsent(WeakReference<Activity> reference) {
        measuresCollRepository.dataConsent(reference);
    }

    @Override
    public void doObjetiveDataCollection(String posture, String compoundAct,String userName) {
        measuresCollRepository.registerSensors();
        measuresCollRepository.registerMobileSensors();
        measuresCollRepository.saveObjectiveData();
        measuresCollRepository.defineActivity(posture,compoundAct,userName);

    }

    @Override
    public void doRestar() {
        measuresCollRepository.unregisterSensors();
        measuresCollRepository.deleteRecords();
    }

    @Override
    public void doInterruption() {
        measuresCollRepository.unregisterSensors();
    }

    @Override
    public void doDisconnect() {
        measuresCollRepository.disconnect();
    }

}
