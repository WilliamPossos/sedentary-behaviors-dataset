package com.example.william.data_set.selectactivity.ui;

import com.example.william.data_set.Activity;
import com.example.william.data_set.User;

import java.util.List;

/**
 * Created by william on 24/09/16.
 */
public interface ListView {
    void selectActivity();
    void setActivities(String Posture, String[] activities,String[] pictures);
    void setuser(User user);
}
