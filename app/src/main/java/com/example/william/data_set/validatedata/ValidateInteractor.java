package com.example.william.data_set.validatedata;

/**
 * Created by william on 17/10/16.
 */
public interface ValidateInteractor {
    void showUserData(String userName);
    void selectActivity(String postureName, String compoundActivity);
    void doValidate(Boolean success);
    void showSummary();
}
