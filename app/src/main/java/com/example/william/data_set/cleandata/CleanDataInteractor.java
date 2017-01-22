package com.example.william.data_set.cleandata;

/**
 * Created by william on 20/11/16.
 */
public interface CleanDataInteractor {
    void doSampleAdjust(int activityId);
    void doSamplesUpdate();
    void doPreview(int activityId);

    void doSpecificAdjust(int activityId, int sampleId);
}
