package com.example.william.data_set.validatedata.event;

import com.example.william.data_set.User;

/**
 * Created by william on 17/10/16.
 */
public class ValidateEvent {
    public final static int userSuccess = 0;
    public final static int deleteSuccess = 1;
    public final static int error = 2;
    public final static int unfilled = 3;
    public final static int filled = 4;
    public final static int nullFinder = 5;

    private User user;
    private String message;
    private int eventType;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
}
