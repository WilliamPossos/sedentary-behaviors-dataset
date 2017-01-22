package com.example.william.data_set.includeform.events;

/**
 * Created by WilliamStibent on 02/09/2016.
 */
public class RegisterEvent {
    public final static int registerError = 0;
    public final static int registerSuccess = 1;
    public final static int onFailedToRecoverSession = 2;

    private int eventType;
    private String errorMesage;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getErrorMesage() {
        return errorMesage;
    }

    public void setErrorMesage(String errorMesage) {
        this.errorMesage = errorMesage;
    }
}
