package com.example.william.data_set.cleandata;

/**
 * Created by william on 20/11/16.
 */

public interface CleanDataPresenter {
    void onCreate();
    void onResume();
    void onDestroy();
    void onPause();
    void previewSamples(int activityId);
    void adjustSamples(int activityId);
    void updateSamples();

    void adjustSpecificSamples(int activityI, int sampleId);
}
