package com.example.william.data_set.lib;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.example.william.data_set.AndroidCollectAplication;
import com.microsoft.band.BandClient;
import com.microsoft.band.BandClientManager;
import com.microsoft.band.BandException;
import com.microsoft.band.BandIOException;
import com.microsoft.band.BandInfo;
import com.microsoft.band.ConnectionState;
import com.microsoft.band.UserConsent;
import com.microsoft.band.sensors.BandAccelerometerEventListener;
import com.microsoft.band.sensors.BandAltimeterEventListener;
import com.microsoft.band.sensors.BandAmbientLightEventListener;
import com.microsoft.band.sensors.BandBarometerEventListener;
import com.microsoft.band.sensors.BandGsrEventListener;
import com.microsoft.band.sensors.BandGyroscopeEventListener;
import com.microsoft.band.sensors.BandHeartRateEventListener;
import com.microsoft.band.sensors.BandPedometerEventListener;
import com.microsoft.band.sensors.BandRRIntervalEventListener;
import com.microsoft.band.sensors.BandSkinTemperatureEventListener;
import com.microsoft.band.sensors.BandUVEventListener;
import com.microsoft.band.sensors.GsrSampleRate;
import com.microsoft.band.sensors.HeartRateConsentListener;
import com.microsoft.band.sensors.SampleRate;

import java.lang.ref.WeakReference;

/**
 * Created by william on 11/09/16.
 */
public class MicrosoftBandTaskImpl{
    BandClient client = null;
    private BandRRIntervalEventListener mRRIntervalEventListener;
    private BandAccelerometerEventListener mAccelerometerEventListener;
    private BandAltimeterEventListener mAltimeterEventListener;
    private BandAmbientLightEventListener mAmbientLightEventListener;
    private BandBarometerEventListener mBarometerEventListener;
    private BandGsrEventListener mGsrEventListener;
    private BandGyroscopeEventListener mGyroscopeEventListener;
    private BandHeartRateEventListener mHeartRateEventListener;
    private BandPedometerEventListener mPedometerEventListener;
    private BandSkinTemperatureEventListener mSkinTemperatureEventListener;
    private BandUVEventListener mUVEventListener;

    private static class SingletonHolder {
        private static final MicrosoftBandTaskImpl INSTANCE = new MicrosoftBandTaskImpl();
    }

    public static MicrosoftBandTaskImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public MicrosoftBandTaskImpl() {
    }

    public BandRRIntervalEventListener getmRRIntervalEventListener() {
        return mRRIntervalEventListener;
    }

    public void setmRRIntervalEventListener(BandRRIntervalEventListener mRRIntervalEventListener) {
        this.mRRIntervalEventListener = mRRIntervalEventListener;
    }

    public BandAccelerometerEventListener getmAccelerometerEventListener() {
        return mAccelerometerEventListener;
    }

    public void setmAccelerometerEventListener(BandAccelerometerEventListener mAccelerometerEventListener) {
        this.mAccelerometerEventListener = mAccelerometerEventListener;
    }

    public BandAltimeterEventListener getmAltimeterEventListener() {
        return mAltimeterEventListener;
    }

    public void setmAltimeterEventListener(BandAltimeterEventListener mAltimeterEventListener) {
        this.mAltimeterEventListener = mAltimeterEventListener;
    }

    public BandAmbientLightEventListener getmAmbientLightEventListener() {
        return mAmbientLightEventListener;
    }

    public void setmAmbientLightEventListener(BandAmbientLightEventListener mAmbientLightEventListener) {
        this.mAmbientLightEventListener = mAmbientLightEventListener;
    }

    public BandBarometerEventListener getmBarometerEventListener() {
        return mBarometerEventListener;
    }

    public void setmBarometerEventListener(BandBarometerEventListener mBarometerEventListener) {
        this.mBarometerEventListener = mBarometerEventListener;
    }

    public BandGsrEventListener getmGsrEventListener() {
        return mGsrEventListener;
    }

    public void setmGsrEventListener(BandGsrEventListener mGsrEventListener) {
        this.mGsrEventListener = mGsrEventListener;
    }

    public BandGyroscopeEventListener getmGyroscopeEventListener() {
        return mGyroscopeEventListener;
    }

    public void setmGyroscopeEventListener(BandGyroscopeEventListener mGyroscopeEventListener) {
        this.mGyroscopeEventListener = mGyroscopeEventListener;
    }

    public BandHeartRateEventListener getmHeartRateEventListener() {
        return mHeartRateEventListener;
    }

    public void setmHeartRateEventListener(BandHeartRateEventListener mHeartRateEventListener) {
        this.mHeartRateEventListener = mHeartRateEventListener;
    }

    public BandPedometerEventListener getmPedometerEventListener() {
        return mPedometerEventListener;
    }

    public void setmPedometerEventListener(BandPedometerEventListener mPedometerEventListener) {
        this.mPedometerEventListener = mPedometerEventListener;
    }

    public BandSkinTemperatureEventListener getmSkinTemperatureEventListener() {
        return mSkinTemperatureEventListener;
    }

    public void setmSkinTemperatureEventListener(BandSkinTemperatureEventListener mSkinTemperatureEventListener) {
        this.mSkinTemperatureEventListener = mSkinTemperatureEventListener;
    }

    public BandUVEventListener getmUVEventListener() {
        return mUVEventListener;
    }

    public void setmUVEventListener(BandUVEventListener mUVEventListener) {
        this.mUVEventListener = mUVEventListener;
    }

    public void disconnect() {
        if (client != null) {
            try {
                client.disconnect().await();
            } catch (InterruptedException e) {
                // Do nothing as this is happening during destroy
            } catch (BandException e) {
                // Do nothing as this is happening during destroy
            }
        }

    }

    public void registerSensors() {
        new SensorsSubscriptionTask().execute();
    }

    public void consetDataCollection(WeakReference<Activity> reference){
        new HeartRateConsentTask().execute(reference);

    }

    public void unregisterSensors() {
        if (client != null) {
            try {
                client.getSensorManager().unregisterAllListeners();
            } catch (BandIOException e) {
                postMessage(e.getMessage());
            }
        }

    }

    public BandAccelerometerEventListener getAcelerometer() {
        return mAccelerometerEventListener;
    }

    private void postMessage(String s) {
        Log.e("-----", s);
    }

    private class SensorsSubscriptionTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                if (getConnectedBandClient()) {

                    int hardwareVersion = Integer.parseInt(client.getHardwareVersion().await());
                    if (hardwareVersion >= 20) {
                        if (client.getSensorManager().getCurrentHeartRateConsent() == UserConsent.GRANTED) {
                            client.getSensorManager().registerRRIntervalEventListener(mRRIntervalEventListener);
                            client.getSensorManager().registerAccelerometerEventListener(mAccelerometerEventListener, SampleRate.MS32);
                            client.getSensorManager().registerAltimeterEventListener(mAltimeterEventListener);
                            client.getSensorManager().registerAmbientLightEventListener(mAmbientLightEventListener);
                            client.getSensorManager().registerBarometerEventListener(mBarometerEventListener);
                            client.getSensorManager().registerGsrEventListener(mGsrEventListener, GsrSampleRate.MS200);
                            client.getSensorManager().registerGyroscopeEventListener(mGyroscopeEventListener, SampleRate.MS32);
                            client.getSensorManager().registerHeartRateEventListener(mHeartRateEventListener);
                            client.getSensorManager().registerPedometerEventListener(mPedometerEventListener);
                            client.getSensorManager().registerSkinTemperatureEventListener(mSkinTemperatureEventListener);
                            client.getSensorManager().registerUVEventListener(mUVEventListener);


                        } else {
                            postMessage("You have not given this application consent to access heart rate data yet."
                                    + " Please press the Heart Rate Consent button.\n");
                        }
                    } else {
                        postMessage("The RR Interval sensor is not supported with your Band version. Microsoft Band 2 is required.\n");
                    }
                } else {
                    postMessage("Band isn't connected. Please make sure bluetooth is on and the band is in range.\n");
                }
            } catch (BandException e) {
                String exceptionMessage = "";
                switch (e.getErrorType()) {
                    case UNSUPPORTED_SDK_VERSION_ERROR:
                        exceptionMessage = "Microsoft Health BandService doesn't support your SDK Version. Please update to latest SDK.\n";
                        break;
                    case SERVICE_ERROR:
                        exceptionMessage = "Microsoft Health BandService is not available. Please make sure Microsoft Health is installed and that you have the correct permissions.\n";
                        break;
                    default:
                        exceptionMessage = "Unknown error occured: " + e.getMessage() + "\n";
                        break;
                }
                postMessage(exceptionMessage);

            } catch (Exception e) {
                postMessage(e.getMessage());
            }
            return null;
        }
    }


    private class HeartRateConsentTask extends AsyncTask<WeakReference<Activity>, Void, Void> {
        @Override
        protected Void doInBackground(WeakReference<Activity>... params) {
            try {
                if (getConnectedBandClient()) {

                    if (client.getSensorManager().getCurrentHeartRateConsent() != UserConsent.GRANTED) {

                    if (params[0].get() != null) {
                        client.getSensorManager().requestHeartRateConsent(params[0].get(), new HeartRateConsentListener() {
                            @Override
                            public void userAccepted(boolean consentGiven) {

                            }
                        });
                    }
                }
                } else {
                    postMessage("Band isn't connected. Please make sure bluetooth is on and the band is in range.\n");
                }
            } catch (BandException e) {
                String exceptionMessage = "";
                switch (e.getErrorType()) {
                    case UNSUPPORTED_SDK_VERSION_ERROR:
                        exceptionMessage = "Microsoft Health BandService doesn't support your SDK Version. Please update to latest SDK.\n";
                        break;
                    case SERVICE_ERROR:
                        exceptionMessage = "Microsoft Health BandService is not available. Please make sure Microsoft Health is installed and that you have the correct permissions.\n";
                        break;
                    default:
                        exceptionMessage = "Unknown error occured: " + e.getMessage() + "\n";
                        break;
                }
                postMessage(exceptionMessage);

            } catch (Exception e) {
                postMessage(e.getMessage());
            }
            return null;
        }
    }

    private boolean getConnectedBandClient() throws InterruptedException, BandException {
        if (client == null) {
            BandInfo[] devices = BandClientManager.getInstance().getPairedBands();
            if (devices.length == 0) {
                postMessage("Band isn't paired with your phone.\n");
                return false;
            }
            client = BandClientManager.getInstance().create(AndroidCollectAplication.getAppContext(), devices[0]);
        } else if (ConnectionState.CONNECTED == client.getConnectionState()) {
            return true;
        }

        postMessage("Band is connecting...\n");
        return ConnectionState.CONNECTED == client.connect().await();
    }

}