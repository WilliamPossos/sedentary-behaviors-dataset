package com.example.william.data_set.cleandata;

import com.example.william.data_set.cleandata.ui.CleanDataView;
import com.example.william.data_set.lib.EventBus;

/**
 * Created by william on 20/11/16.
 */

public class CleanDataPresenterImpl implements CleanDataPresenter {
    EventBus eventBus;
    CleanDataView view;
    CleanDataInteractor interactor;

    public CleanDataPresenterImpl(CleanDataView view) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = new CleanDataInteratorImpl();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void previewSamples(int activityId) {
        interactor.doPreview(activityId);
    }

    @Override
    public void adjustSamples(int activityId) {
        interactor.doSampleAdjust(activityId);
    }

    @Override
    public void updateSamples() {

    }

    @Override
    public void adjustSpecificSamples(int activityId, int sampleId) {
        interactor.doSpecificAdjust(activityId, sampleId);
    }
}
