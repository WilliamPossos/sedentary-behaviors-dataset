package com.example.william.data_set.selectactivity;

import com.example.william.data_set.selectactivity.event.LoadActivityEvent;

/**
 * Created by william on 24/09/16.
 */
public interface SelectionPresenter {
    void onCreate(String mail);
    void onPause();
    void onDestroy();
    void selectActivity(String activityName);
    void loadActivities();
    void showSpecificPositionDialog();
    void onEventMainThread(LoadActivityEvent event);
}
