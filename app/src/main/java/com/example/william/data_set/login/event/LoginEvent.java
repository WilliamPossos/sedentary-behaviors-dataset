package com.example.william.data_set.login.event;

/**
 * Created by william on 19/10/16.
 */
public class LoginEvent {

    public static final int error=0;
    public static final int success=1;

    private int eventType;
    private String message;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
