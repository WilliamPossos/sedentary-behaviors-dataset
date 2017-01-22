package com.example.william.data_set.measurescollection.events;

/**
 * Created by william on 9/09/16.
 */
public class MeasuresEvent {
    public final static int updateProgress = 0;
    public final static int tset = 1;

    private int eventType;
    private float Xvalue;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public float getXvalue() {
        return Xvalue;
    }

    public void setXvalue(float xvalue) {
        Xvalue = xvalue;
    }
}
