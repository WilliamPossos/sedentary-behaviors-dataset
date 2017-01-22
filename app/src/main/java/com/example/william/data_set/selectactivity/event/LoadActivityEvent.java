package com.example.william.data_set.selectactivity.event;

import com.example.william.data_set.Activity;
import com.example.william.data_set.User;

import java.util.List;

/**
 * Created by william on 9/10/16.
 */
public class LoadActivityEvent {
    public final static int userLoad = 0;
    public final static int activityLoad = 1;

    private int eventType;

    private String[] activities;
    private String[] pictures;
    private User user;
    private String posture;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String[] getActivities() {
        return activities;
    }

    public void setActivities(String[] activities) {
        this.activities = activities;
    }

    public String[] getPictures() {
        return pictures;
    }

    public void setPictures(String[] pictures) {
        this.pictures = pictures;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPosture() {
        return posture;
    }

    public void setPosture(String posture) {
        this.posture = posture;
    }
}
