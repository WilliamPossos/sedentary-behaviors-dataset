package com.example.william.data_set.validatedata.ui;

import com.example.william.data_set.User;

/**
 * Created by william on 17/10/16.
 */

public interface ValidateView {
    void showValidationDialog(String title, String message, final int flag);

    void loadUser(User user);

    void NavigateToList(int fill);

    void showSummary(String message);
}
