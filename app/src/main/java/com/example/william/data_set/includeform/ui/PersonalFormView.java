package com.example.william.data_set.includeform.ui;

/**
 * Created by william on 5/09/16.
 */
public interface PersonalFormView {
    void navigateToActivities();
    void handleRegister();


    void newUserSuccess();
    void newUserError(String error);
}
