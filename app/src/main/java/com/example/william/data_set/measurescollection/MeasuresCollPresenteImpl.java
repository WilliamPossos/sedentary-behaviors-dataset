package com.example.william.data_set.measurescollection;

import android.app.Activity;
import android.util.Log;

import com.example.william.data_set.R;
import com.example.william.data_set.lib.EventBus;
import com.example.william.data_set.lib.GreenRobotEventBus;
import com.example.william.data_set.measurescollection.events.MeasuresEvent;
import com.example.william.data_set.measurescollection.ui.MeasuresCollView;

import org.greenrobot.eventbus.Subscribe;

import java.lang.ref.WeakReference;

/**
 * Created by william on 8/09/16.
 */
public class MeasuresCollPresenteImpl implements MeasuresCollPresenter {
    EventBus eventBus;
    MeasuresCollView measuresCollView;
    MeasuresCollectionInteractor measuresCollectionInteractor;

    public MeasuresCollPresenteImpl(MeasuresCollView measuresCollView) {
        this.measuresCollView = measuresCollView;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.measuresCollectionInteractor = new MeasuresCollectionInteractorImpl();
    }

    @Override
    public void onPause() {
        measuresCollectionInteractor.doInterruption();

    }

    @Override
    public void onResume() {


    }

    @Override
    public void onCreate(WeakReference<Activity> reference) {
        measuresCollectionInteractor.seekForConsent(reference);
        eventBus.register(this);

    }

    @Override
    public void onDestroy() {
        measuresCollView = null;
        eventBus.unregister(this);
        measuresCollectionInteractor.doDisconnect();

    }

    @Override
    public void initDataStorage(String posture, String compoundAct,String userName) {
        if (measuresCollView != null) {
            measuresCollView.hideStartButton();
            measuresCollView.showRestartButton();
        }
        measuresCollectionInteractor.doObjetiveDataCollection(posture,compoundAct,userName);

    }

    @Override
    public void restartDataStorage() {
        if (measuresCollView != null) {
            measuresCollView.showRestartDialog(R.string.restart_title, R.string.restart_message);
            measuresCollView.hideRestartButton();
            measuresCollView.showStartButton();
        }
        measuresCollectionInteractor.doRestar();

    }

    @Override
    public void restartCapture() {


    }

    @Override
    public void validateData() {

    }

    @Override
    public void initTimer() {

    }

    @Override
    public void finishCollection() {
        measuresCollectionInteractor.doInterruption();

    }

    @Override
    @Subscribe
    public void onEventMainThread(MeasuresEvent event) {
        switch (event.getEventType())
        {
            case MeasuresEvent.updateProgress:
                //measuresCollView.updateProgressBar();

        }

    }
}
