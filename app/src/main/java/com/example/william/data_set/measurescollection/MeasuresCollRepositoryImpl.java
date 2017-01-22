package com.example.william.data_set.measurescollection;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.Switch;

import com.example.william.data_set.Action;
import com.example.william.data_set.ActionDao;
import com.example.william.data_set.ActivityDao;
import com.example.william.data_set.AndroidCollectAplication;
import com.example.william.data_set.Location;
import com.example.william.data_set.LocationDao;
import com.example.william.data_set.Posture;
import com.example.william.data_set.PostureDao;
import com.example.william.data_set.User;
import com.example.william.data_set.UserDao;
import com.example.william.data_set.UserData;
import com.example.william.data_set.UserDataDao;
import com.example.william.data_set.domain.GreenDaoHelper;
import com.example.william.data_set.lib.BeaconID;
import com.example.william.data_set.lib.EventBus;
import com.example.william.data_set.lib.GreenRobotEventBus;
import com.example.william.data_set.lib.MicrosoftBandTaskImpl;
import com.example.william.data_set.lib.NearestBeaconManager;
import com.example.william.data_set.measurescollection.events.MeasuresEvent;
import com.microsoft.band.sensors.BandAccelerometerEvent;
import com.microsoft.band.sensors.BandAccelerometerEventListener;
import com.microsoft.band.sensors.BandAltimeterEvent;
import com.microsoft.band.sensors.BandAltimeterEventListener;
import com.microsoft.band.sensors.BandAmbientLightEvent;
import com.microsoft.band.sensors.BandAmbientLightEventListener;
import com.microsoft.band.sensors.BandBarometerEvent;
import com.microsoft.band.sensors.BandBarometerEventListener;
import com.microsoft.band.sensors.BandGsrEvent;
import com.microsoft.band.sensors.BandGsrEventListener;
import com.microsoft.band.sensors.BandGyroscopeEvent;
import com.microsoft.band.sensors.BandGyroscopeEventListener;
import com.microsoft.band.sensors.BandHeartRateEvent;
import com.microsoft.band.sensors.BandHeartRateEventListener;
import com.microsoft.band.sensors.BandPedometerEvent;
import com.microsoft.band.sensors.BandPedometerEventListener;
import com.microsoft.band.sensors.BandRRIntervalEvent;
import com.microsoft.band.sensors.BandRRIntervalEventListener;
import com.microsoft.band.sensors.BandSkinTemperatureEvent;
import com.microsoft.band.sensors.BandSkinTemperatureEventListener;
import com.microsoft.band.sensors.BandUVEvent;
import com.microsoft.band.sensors.BandUVEventListener;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by william on 9/09/16.
 */
public class MeasuresCollRepositoryImpl implements MeasuresCollRepository{
    private static final String TAG = "Repository collection";
    private GreenDaoHelper greenDaoHelper;
    private MicrosoftBandTaskImpl microsoftBandTask;
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
    private SensorEventListener mSensorEventListener;
    private NearestBeaconManager nearestBeaconManager;
    private Sensor mSensorAccelerometer;
    private Sensor mSensorGyroscope;
    private Sensor mSensorMagnetico;
    private Sensor mSensorGravity;
    private Sensor mSensorRotation;
    private Sensor mSensorLinearAcceleration;
    private Sensor mSensorPressure;
    private SensorManager mSensorManager;
    private UserData userData = new UserData();


    User currentUser;
    long isTime=0;
    int delayPhoneVersion=0;
    com.example.william.data_set.Activity currentAct;


    public MeasuresCollRepositoryImpl() {
        this.greenDaoHelper = GreenDaoHelper.getInstance();
        this.microsoftBandTask = MicrosoftBandTaskImpl.getInstance();

    }
    @Override
    public void saveObjectiveData() {
        isTime=0;

        BandRRIntervalEventListener mRRIntervalEventListener = new BandRRIntervalEventListener() {
            @Override
            public void onBandRRIntervalChanged(final BandRRIntervalEvent event) {
                if (event != null) {
                    userData.setBandRR(event.getInterval());
                }
            }
        };

        BandAccelerometerEventListener mAccelerometerEventListener =new BandAccelerometerEventListener() {
            @Override
            public void onBandAccelerometerChanged(final BandAccelerometerEvent event) {
                if (event != null) {
                    userData.setBandAccX((double) event.getAccelerationX());
                    userData.setBandAccY((double) event.getAccelerationY());
                    userData.setBandAccZ((double) event.getAccelerationZ());



                }
            }
        };

        BandAltimeterEventListener mAltimeterEventListener = new BandAltimeterEventListener() {
            @Override
            public void onBandAltimeterChanged(final BandAltimeterEvent event) {
                if (event != null) {
                    userData.setBandAltimeterRate(event.getRate());
                }
            }
        };
        BandAmbientLightEventListener mAmbientLightEventListener = new BandAmbientLightEventListener() {
            @Override
            public void onBandAmbientLightChanged(final BandAmbientLightEvent event) {
                if (event != null) {
                   userData.setBandAmbientLight(event.getBrightness());
                }
            }
        };
        BandBarometerEventListener mBarometerEventListener = new BandBarometerEventListener() {
            @Override
            public void onBandBarometerChanged(final BandBarometerEvent event) {
                if (event != null) {
                    userData.setBandBarometerAir(event.getAirPressure());
                    userData.setBandBarometerTemp(event.getTemperature());
                }
            }
        };
        BandGsrEventListener mGsrEventListener = new BandGsrEventListener() {
            @Override
            public void onBandGsrChanged(final BandGsrEvent event) {
                if (event != null) {
                    userData.setBandGsr(event.getResistance());
                }
            }
        };
        BandGyroscopeEventListener mGyroscopeEventListener = new BandGyroscopeEventListener() {
            @Override
            public void onBandGyroscopeChanged(BandGyroscopeEvent event) {
                if (event != null) {
                    userData.setBandGyrX((double) event.getAngularVelocityX());
                    userData.setBandGyrY((double) event.getAngularVelocityY());
                    userData.setBandGyrZ((double) event.getAngularVelocityZ());
                }

            }
        };
        BandHeartRateEventListener mHeartRateEventListener = new BandHeartRateEventListener() {
            @Override
            public void onBandHeartRateChanged(final BandHeartRateEvent event) {
                if (event != null) {
                    userData.setBandHeartRate(event.getHeartRate());
                    userData.setBandQoHR(String.valueOf(event.getQuality()));

                }
            }
        };
        BandPedometerEventListener mPedometerEventListener = new BandPedometerEventListener() {
            @Override
            public void onBandPedometerChanged(BandPedometerEvent event) {
                if (event != null) {
                    userData.setBandPedometer(event.getTotalSteps());

                }
            }
        };
        BandSkinTemperatureEventListener mSkinTemperatureEventListener = new BandSkinTemperatureEventListener() {
            @Override
            public void onBandSkinTemperatureChanged(BandSkinTemperatureEvent event) {
                if (event != null) {
                    userData.setBandSkinTemperature((double) event.getTemperature());
                }
            }
        };
        BandUVEventListener mUVEventListener = new BandUVEventListener() {
            @Override
            public void onBandUVChanged(BandUVEvent event) {
                if (event != null) {
                    userData.setBandUVindex(String.valueOf(event.getUVIndexLevel()));
                }
            }
        };

        nearestBeaconManager.setListener(new NearestBeaconManager.Listener() {
            @Override
            public void onNearestBeaconChanged(Map<String,Integer> beacons) {
                if (beacons != null) {
                    for (Map.Entry<String,Integer> beacon:beacons.entrySet()
                            ) {
                        if(beacon.getKey().toString().equals("[D3:60:2D:3A:08:7C]")){
                            userData.setBbBeacon(beacon.getValue());
                        }
                        if(beacon.getKey().toString().equals("[C5:CA:77:69:EC:53]")){
                            userData.setIceBeacon(beacon.getValue());
                        }
                        if(beacon.getKey().toString().equals("[ED:76:B0:82:7B:DC]")){
                            userData.setMintBeacon(beacon.getValue());
                        }
                        if(beacon.getKey().toString().equals("[E1:B1:F2:9F:2C:7E]")){
                            userData.setMintBeacon2(beacon.getValue());
                        }
                        if(beacon.getKey().toString().equals("[EE:04:E8:E5:B0:48]")){
                            userData.setBbBeacon2(beacon.getValue());
                        }
                        if(beacon.getKey().toString().equals("[D9:A2:26:D8:78:4B]")){
                            userData.setIceBeacon2(beacon.getValue());
                        }

                    }
                }
            }
        });
        microsoftBandTask.setmAccelerometerEventListener(mAccelerometerEventListener);
        microsoftBandTask.setmHeartRateEventListener(mHeartRateEventListener);
        microsoftBandTask.setmRRIntervalEventListener(mRRIntervalEventListener);
        microsoftBandTask.setmAltimeterEventListener(mAltimeterEventListener);
        microsoftBandTask.setmAmbientLightEventListener(mAmbientLightEventListener);
        microsoftBandTask.setmBarometerEventListener(mBarometerEventListener);
        microsoftBandTask.setmPedometerEventListener(mPedometerEventListener);
        microsoftBandTask.setmGsrEventListener(mGsrEventListener);
        microsoftBandTask.setmGyroscopeEventListener(mGyroscopeEventListener);
        microsoftBandTask.setmSkinTemperatureEventListener(mSkinTemperatureEventListener);
        microsoftBandTask.setmUVEventListener(mUVEventListener);
        microsoftBandTask.registerSensors();

    }

    private void saveData() {
        long currentTime=System.currentTimeMillis();
        if (isTime==0){
            isTime=currentTime;
        }

        userData.setId(null);
        userData.setTimestamp(currentTime);
        if (userData != null&&currentTime-isTime>40000) {
            //if (userData != null) {
            greenDaoHelper.getUserDataEntityDao().insert(userData);
        }


    }

    @Override
    public void dataConsent(WeakReference<Activity> reference) {
        microsoftBandTask.consetDataCollection(reference);

    }

    @Override
    public void disconnect() {
        microsoftBandTask.disconnect();
    }

    @Override
    public void registerSensors() {

        nearestBeaconManager = new NearestBeaconManager(AndroidCollectAplication.getAppContext(), Arrays.asList(
                new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 54060, 15247),
                new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 30764, 17379),
                new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 51127, 63751),
                new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 1, 1),
                new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 1, 2),
                new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 1, 3))
        );
        nearestBeaconManager.startNearestBeaconUpdates();

        /*mBandAccelerometerEventListener = microsoftBandTask.getmAccelerometerEventListener();
        mHeartRateEventListener = microsoftBandTask.getmBandHeartRateEventListener();
        microsoftBandTask.registerSensors();*/


    }

    @Override
    public void unregisterSensors() {
        microsoftBandTask.unregisterSensors();
        if (nearestBeaconManager != null) {
            nearestBeaconManager.stopNearestBeaconUpdates();
            nearestBeaconManager.destroy();
        }
        if (mSensorEventListener != null) {
            mSensorManager.unregisterListener(mSensorEventListener);
        }


    }

    @Override
    public void deleteRecords() {
        greenDaoHelper.getUserDataEntityDao().queryBuilder().where(
                UserDataDao.Properties.ActivityId.eq(currentAct.getId())).buildDelete().
                executeDeleteWithoutDetachingEntities();

    }

    @Override
    public void defineActivity(String postureName, String compoundAct,String userName) {
        currentUser = greenDaoHelper.getUserEntityDao().queryBuilder().
                where(UserDao.Properties.Username.eq(userName)).unique();

        String[] parts = compoundAct.split("\n\t\t");
        String actionName = parts[0];
        String locationName = parts[1];
        Action action = greenDaoHelper.getActionEntityDao().queryBuilder().where(ActionDao.Properties.Name.eq(actionName)).unique();
        Location location = greenDaoHelper.getLocationEntityDao().queryBuilder().where(LocationDao.Properties.LocationName.eq(locationName)).unique();
        Posture posture = greenDaoHelper.getPostureEntityDao().queryBuilder().where(PostureDao.Properties.Posture.eq(postureName)).unique();
        currentAct = greenDaoHelper.getActivityDao().queryBuilder().where(
                ActivityDao.Properties.UserId.eq(currentUser.getId()),
                ActivityDao.Properties.LocationId.eq(location.getId()),
                ActivityDao.Properties.PostureId.eq(posture.getId()),
                ActivityDao.Properties.ActionId.eq(action.getId())).unique();
        userData.setActivityId(currentAct.getId());

    }

    @Override
    public void registerMobileSensors(){
        mSensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                switch (sensorEvent.sensor.getType()){
                case Sensor.TYPE_ACCELEROMETER:
                    userData.setMobileAccX(sensorEvent.values[0]);
                    userData.setMobileAccY(sensorEvent.values[1]);
                    userData.setMobileAccZ(sensorEvent.values[2]);
                    //one Phones not change the frequency
                    //saveData();

                    if(delayPhoneVersion==0){

                        saveData();
                        delayPhoneVersion=1;

                    }
                    else{
                        delayPhoneVersion=0;
                    }

                    break;

                case Sensor.TYPE_GYROSCOPE:
                    userData.setMobileGyrX(sensorEvent.values[0]);
                    userData.setMobileGyrY(sensorEvent.values[1]);
                    userData.setMobileGyrZ(sensorEvent.values[2]);
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD :
                    userData.setMobileMagX(sensorEvent.values[0]);
                    userData.setMobileMagY(sensorEvent.values[1]);
                    userData.setMobileMagZ(sensorEvent.values[2]);

                    break;

                case Sensor.TYPE_GRAVITY:
                    userData.setMobileGraX(sensorEvent.values[0]);
                    userData.setMobileGraY(sensorEvent.values[1]);
                    userData.setMobileGraZ(sensorEvent.values[2]);
                    break;

                case Sensor.TYPE_LINEAR_ACCELERATION:
                    userData.setMobileLinX(sensorEvent.values[0]);
                    userData.setMobileLinY(sensorEvent.values[1]);
                    userData.setMobileLinZ(sensorEvent.values[2]);
                    break;

                case Sensor.TYPE_ROTATION_VECTOR:
                    userData.setMobileRotX(sensorEvent.values[0]);
                    userData.setMobileRotY(sensorEvent.values[1]);
                    userData.setMobileRotZ(sensorEvent.values[2]);
                    break;
                case Sensor.TYPE_PRESSURE:
                    userData.setMBarometer(sensorEvent.values[0]);
                    break;

            }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        mSensorManager = (SensorManager) AndroidCollectAplication.getAppContext().
                getSystemService(Context.SENSOR_SERVICE);
        mSensorAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mSensorMagnetico=mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mSensorGravity=mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        mSensorRotation=mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        mSensorPressure=mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        mSensorLinearAcceleration=mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        //Frecuencias diferentes, para el LG hay que colocar SENSOR_DELAY_GAME (50 hz) y para el
        //Huawei SENSOR_DELAY_NORMAL.
        mSensorManager.registerListener(mSensorEventListener,mSensorAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(mSensorEventListener,mSensorGyroscope,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(mSensorEventListener,mSensorMagnetico,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(mSensorEventListener,mSensorLinearAcceleration,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(mSensorEventListener,mSensorRotation,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(mSensorEventListener,mSensorGravity,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(mSensorEventListener,mSensorPressure,SensorManager.SENSOR_DELAY_NORMAL);

    }
    private void postEvent(int type) {
        postEvent(type, null);
    }

    private void postEvent(int type, String errorMessage) {
        MeasuresEvent measuresEvent = new MeasuresEvent();
        measuresEvent.setEventType(type);

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(measuresEvent);
    }
}
