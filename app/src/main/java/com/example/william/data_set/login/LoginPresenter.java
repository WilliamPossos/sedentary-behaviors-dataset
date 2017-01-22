package com.example.william.data_set.login;

import com.example.william.data_set.login.event.LoginEvent;

/**
 * Created by william on 19/10/16.
 */

public interface LoginPresenter {
    void onCreate();
    void onPause();
    void onDestroy();

    void login(String user);
    void onEventMainThread(LoginEvent event);
}
