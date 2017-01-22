package com.example.william.data_set.lib;

import com.microsoft.band.sensors.BandAccelerometerEventListener;

/**
 * Created by william on 15/09/16.
 */
public interface MicrosoftBandTask {
    void disconnect();
    void registerSensors();
    void unregisterSensors();
    BandAccelerometerEventListener getAcelerometer();
    void postEventToRepository();
}
