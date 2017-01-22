package com.example.william.data_set.login.ui;

/**
 * Created by william on 19/10/16.
 */

public interface LoginView {
    void showSnackBar(String message);
    void showToast();
    void navigateToFormActivity();
    void navigateToListActivity();
}
