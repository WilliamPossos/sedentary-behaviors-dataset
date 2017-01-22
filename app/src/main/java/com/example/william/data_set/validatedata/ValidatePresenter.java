package com.example.william.data_set.validatedata;

import com.example.william.data_set.validatedata.event.ValidateEvent;

/**
 * Created by william on 17/10/16.
 */

public interface ValidatePresenter {
    void onCreate();
    void onDestroy();
    void onPause();
    void OnResume();
    void onEventMainThread(ValidateEvent event);

    void getuser(String userName);
    void validateData(Boolean success);
    void selectActivity(String postureName, String compoundActivity);
}
