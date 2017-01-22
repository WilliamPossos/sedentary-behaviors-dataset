package com.example.william.data_set.cleandata;

/**
 * Created by william on 20/11/16.
 */
public interface CleanDataRepository {
    void deleteSamples(int activityId);
    void deleteSpecificSamples(int activityId, int first);
    void updateRecords(int activityId);
    void previewData(int activityId);
}
