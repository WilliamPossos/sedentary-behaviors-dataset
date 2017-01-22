package com.example.william.data_set.cleandata;

/**
 * Created by william on 20/11/16.
 */
public class CleanDataInteratorImpl implements CleanDataInteractor {
    CleanDataRepository repository;

    public CleanDataInteratorImpl() {
        this.repository = new CleanDataRepositoryImpl();
    }

    @Override
    public void doSampleAdjust(int activityId) {
        repository.deleteSamples(activityId);

    }

    @Override
    public void doSamplesUpdate() {

    }

    @Override
    public void doPreview(int activityId) {
        repository.previewData(activityId);
    }

    @Override
    public void doSpecificAdjust(int activityId, int sampleId) {
        repository.deleteSpecificSamples(activityId, sampleId);
    }
}
