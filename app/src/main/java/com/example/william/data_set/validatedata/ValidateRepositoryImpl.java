package com.example.william.data_set.validatedata;

import android.os.Environment;
import android.util.Log;

import com.example.william.data_set.Action;
import com.example.william.data_set.ActionDao;
import com.example.william.data_set.Activity;
import com.example.william.data_set.ActivityDao;
import com.example.william.data_set.Location;
import com.example.william.data_set.LocationDao;
import com.example.william.data_set.Posture;
import com.example.william.data_set.PostureDao;
import com.example.william.data_set.User;
import com.example.william.data_set.UserDao;
import com.example.william.data_set.UserData;
import com.example.william.data_set.UserDataDao;
import com.example.william.data_set.domain.GreenDaoHelper;
import com.example.william.data_set.lib.EventBus;
import com.example.william.data_set.lib.GreenRobotEventBus;
import com.example.william.data_set.validatedata.event.ValidateEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by william on 17/10/16.
 */
public class ValidateRepositoryImpl implements ValidateRepository {
    private static final String SEPARATOR = ";";
    private GreenDaoHelper greenDaoHelper;
    private User currentUser;
    private Activity activity;
    FileWriter fileWriter;


    public ValidateRepositoryImpl() {
        this.greenDaoHelper = GreenDaoHelper.getInstance();
    }

    @Override
    public void getUser(String userName) {
        currentUser = greenDaoHelper.getUserEntityDao().queryBuilder().
                where(UserDao.Properties.Username.eq(userName)).unique();
        postEvent(ValidateEvent.userSuccess,currentUser);

    }


    @Override
    public void validateActivity(String postureName, String cactivity) {

        String[] parts = cactivity.split("\n\t\t");
        String actionName = parts[0];
        String locationName = parts[1];
        Action action = greenDaoHelper.getActionEntityDao().queryBuilder().where(ActionDao.Properties.Name.eq(actionName)).unique();
        Location location = greenDaoHelper.getLocationEntityDao().queryBuilder().where(LocationDao.Properties.LocationName.eq(locationName)).unique();
        Posture posture = greenDaoHelper.getPostureEntityDao().queryBuilder().where(PostureDao.Properties.Posture.eq(postureName)).unique();
        activity = greenDaoHelper.getActivityDao().queryBuilder().where(
                ActivityDao.Properties.UserId.eq(currentUser.getId()),
                ActivityDao.Properties.LocationId.eq(location.getId()),
                ActivityDao.Properties.PostureId.eq(posture.getId()),
                ActivityDao.Properties.ActionId.eq(action.getId())).unique();
        getCollSummary();

    }

    @Override
    public void allSuccess(Boolean success) {
        if(success){
            if (!activity.getStatus().equals("Realizada")){
                activity.setStatus("Realizada");
            }
            greenDaoHelper.getActivityDao().update(activity);
            List<Activity> activities = greenDaoHelper.getActivityDao().queryBuilder().where(
                    ActivityDao.Properties.Status.eq("No realizada")
            ).list();
            if (activities.size()>0){
                postEvent(ValidateEvent.unfilled,currentUser);
            }
            else{
                postEvent(ValidateEvent.filled,currentUser);
            }
        }
        else{
            greenDaoHelper.getUserDataEntityDao().queryBuilder().where(
                    UserDataDao.Properties.ActivityId.eq(activity.getId())).buildDelete().
                    executeDeleteWithoutDetachingEntities();
        }

    }

    private void exportToCSV(){

        File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.
                DIRECTORY_DOCUMENTS).getAbsolutePath() + "/DatosExperimento");
        folder.mkdir();
        if (!folder.mkdir()) {
            Log.e(":O", "Directory alredy exist");
        }
        File file = new File(folder, ""
                +currentUser.getName()+"_"
                +activity.getPostureId()+"_"
                +activity.getActionId()+"_"
                +activity.getLocationId()+".txt");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String lineToWrite = "";
        List<UserData> userDataList = greenDaoHelper.getUserDataEntityDao().queryBuilder().
                where(UserDataDao.Properties.ActivityId.eq(activity.getId())).list();
        for (UserData userData:userDataList
             ) {
                lineToWrite=""
                        +userData.getTimestamp()
                        +SEPARATOR+userData.getBandAccX()
                        +SEPARATOR+userData.getBandAccY()
                        +SEPARATOR+userData.getBandAccZ()
                        +SEPARATOR+userData.getBandAltimeterRate()
                        +SEPARATOR+userData.getBandAmbientLight()
                        +SEPARATOR+userData.getBandBarometerAir()
                        +SEPARATOR+userData.getBandBarometerTemp()
                        +SEPARATOR+userData.getBandGsr()
                        +SEPARATOR+userData.getBandGyrX()
                        +SEPARATOR+userData.getBandGyrY()
                        +SEPARATOR+userData.getBandGyrZ()
                        +SEPARATOR+userData.getBandHeartRate()
                        +SEPARATOR+userData.getBandQoHR()
                        +SEPARATOR+userData.getBandPedometer()
                        +SEPARATOR+userData.getBandRR()
                        +SEPARATOR+userData.getBandSkinTemperature()
                        +SEPARATOR+userData.getBandUVindex()
                        +SEPARATOR+userData.getMobileAccX()
                        +SEPARATOR+userData.getMobileAccY()
                        +SEPARATOR+userData.getMobileAccZ()
                        +SEPARATOR+userData.getMobileGyrX()
                        +SEPARATOR+userData.getMobileGyrY()
                        +SEPARATOR+userData.getMobileGyrZ()
                        +SEPARATOR+userData.getMobileMagX()
                        +SEPARATOR+userData.getMobileMagY()
                        +SEPARATOR+userData.getMobileMagZ()
                        +SEPARATOR+userData.getMobileGraX()
                        +SEPARATOR+userData.getMobileGraX()
                        +SEPARATOR+userData.getMobileGraX()
                        +SEPARATOR+userData.getMobileLinX()
                        +SEPARATOR+userData.getMobileLinY()
                        +SEPARATOR+userData.getMobileLinZ()
                        +SEPARATOR+userData.getMobileRotX()
                        +SEPARATOR+userData.getMobileRotY()
                        +SEPARATOR+userData.getMobileRotZ()
                        +SEPARATOR+userData.getMBarometer()
                        +SEPARATOR+userData.getBbBeacon()
                        +SEPARATOR+userData.getIceBeacon()
                        +SEPARATOR+userData.getMintBeacon()
                        +SEPARATOR+userData.getBbBeacon2()
                        +SEPARATOR+userData.getIceBeacon2()
                        +SEPARATOR+userData.getMintBeacon2();

                //if(!lineToWrite.contains("null")){

                    try {
                        fileWriter = new FileWriter(Environment.getExternalStoragePublicDirectory
                                (Environment.DIRECTORY_DOCUMENTS).getAbsoluteFile() +
                                "/DatosExperimento/"+currentUser.getName()+"_"
                                +activity.getPostureId()+"_"
                                +activity.getActionId()+"_"
                                +activity.getLocationId()+".txt", true);
                        fileWriter.write(lineToWrite);
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                //}
            }


    }

    public void getCollSummary(){

        //In this section the idea is do a string with information of number of nulls in a row of
        //user data.

        float totalSamples = greenDaoHelper.getUserDataEntityDao().queryBuilder().where(
                UserDataDao.Properties.ActivityId.eq(activity.getId())).count();
        String summary="Muestras Erradas\n";
        long noGsrNull= greenDaoHelper.getUserDataEntityDao().queryBuilder().where(
                UserDataDao.Properties.ActivityId.eq(activity.getId()),
                UserDataDao.Properties.BandGsr.isNull()).count();
        summary=String.format("%s%.3f",summary,noGsrNull*100/totalSamples)+"%\n";
        long noBandAccNull=greenDaoHelper.getUserDataEntityDao().queryBuilder().where(
                UserDataDao.Properties.ActivityId.eq(activity.getId()),
                UserDataDao.Properties.BandAccX.isNull()).count();
        summary=String.format("%s%.3f",summary,noBandAccNull*100/totalSamples)+"%\n";
        long noBandAltimeterNull=greenDaoHelper.getUserDataEntityDao().queryBuilder().where(
                UserDataDao.Properties.ActivityId.eq(activity.getId()),
                UserDataDao.Properties.BandAltimeterRate.isNull()).count();
        summary=String.format("%s%.3f",summary,noBandAltimeterNull*100/totalSamples)+"%\n";
        long noBandGyroNull = greenDaoHelper.getUserDataEntityDao().queryBuilder().where(
                UserDataDao.Properties.ActivityId.eq(activity.getId()),
                UserDataDao.Properties.BandGyrX.isNull()).count();
        summary=String.format("%s%.3f",summary,noBandGyroNull*100/totalSamples)+"%\n";
        long noHRNull = greenDaoHelper.getUserDataEntityDao().queryBuilder().where(
                UserDataDao.Properties.ActivityId.eq(activity.getId()),
                UserDataDao.Properties.BandHeartRate.isNull()).count();
        summary=String.format("%s%.3f",summary,noHRNull*100/totalSamples)+"%\n";
        long noBandSkinNull = greenDaoHelper.getUserDataEntityDao().queryBuilder().where(
                UserDataDao.Properties.ActivityId.eq(activity.getId()),
                UserDataDao.Properties.BandSkinTemperature.isNull()).count();
        summary=String.format("%s%.3f",summary,noBandSkinNull*100/totalSamples)+"%\n";
        long noBandUVNull = greenDaoHelper.getUserDataEntityDao().queryBuilder().where(
                UserDataDao.Properties.ActivityId.eq(activity.getId()),
                UserDataDao.Properties.BandUVindex.isNull()).count();
        summary=String.format("%s%.3f",summary,noBandUVNull*100/totalSamples)+"%\n";
        long noBandRRNull = greenDaoHelper.getUserDataEntityDao().queryBuilder().where(
                UserDataDao.Properties.ActivityId.eq(activity.getId()),
                UserDataDao.Properties.BandRR.isNull()).count();
        summary=String.format("%s%.3f",summary,noBandRRNull*100/totalSamples)+"%\n";
        long noBandBarNull = greenDaoHelper.getUserDataEntityDao().queryBuilder().where(
                UserDataDao.Properties.ActivityId.eq(activity.getId()),
                UserDataDao.Properties.BandBarometerAir.isNull()).count();
        summary=String.format("%s%.3f",summary,noBandBarNull*100/totalSamples)+"%\n";
        long noMobileAccNull = greenDaoHelper.getUserDataEntityDao().queryBuilder().where(
                UserDataDao.Properties.ActivityId.eq(activity.getId()),
                UserDataDao.Properties.MobileAccX.isNull()).count();
        summary=String.format("%s%.3f",summary,noMobileAccNull*100/totalSamples)+"%\n";
        long noMobileGyNull = greenDaoHelper.getUserDataEntityDao().queryBuilder().where(
                UserDataDao.Properties.ActivityId.eq(activity.getId()),
                UserDataDao.Properties.MobileGyrX.isNull()).count();
        summary=String.format("%s%.3f",summary,noMobileGyNull*100/totalSamples)+"%\n";

        long noMobileGrNull = greenDaoHelper.getUserDataEntityDao().queryBuilder().where(
                UserDataDao.Properties.ActivityId.eq(activity.getId()),
                UserDataDao.Properties.MobileGraX.isNull()).count();
        summary=String.format("%s%.3f",summary,noMobileGrNull*100/totalSamples)+"%\n";

        long noMobileGMaNull = greenDaoHelper.getUserDataEntityDao().queryBuilder().where(
                UserDataDao.Properties.ActivityId.eq(activity.getId()),
                UserDataDao.Properties.MobileMagX.isNull()).count();
        summary=String.format("%s%.3f",summary,noMobileGMaNull*100/totalSamples)+"%\n";

        long noMobileVliNull = greenDaoHelper.getUserDataEntityDao().queryBuilder().where(
                UserDataDao.Properties.ActivityId.eq(activity.getId()),
                UserDataDao.Properties.MobileLinX.isNull()).count();
        summary=String.format("%s%.3f",summary,noMobileVliNull*100/totalSamples)+"%\n";

        long noMobileBarNull = greenDaoHelper.getUserDataEntityDao().queryBuilder().where(
                UserDataDao.Properties.ActivityId.eq(activity.getId()),
                UserDataDao.Properties.MBarometer.isNull()).count();
        summary=String.format("%s%.3f",summary,noMobileBarNull*100/totalSamples)+"%\n";


        long nobbNull = greenDaoHelper.getUserDataEntityDao().queryBuilder().where(
                UserDataDao.Properties.ActivityId.eq(activity.getId()),
                UserDataDao.Properties.BbBeacon.isNull()).count();
        summary=String.format("%s%.3f",summary,nobbNull*100/totalSamples)+"%\n";
        long nomintNull = greenDaoHelper.getUserDataEntityDao().queryBuilder().where(
                UserDataDao.Properties.ActivityId.eq(activity.getId()),
                UserDataDao.Properties.MintBeacon.isNull()).count();
        summary=String.format("%s%.3f",summary,nomintNull*100/totalSamples)+"%\n";
        long noiceNull = greenDaoHelper.getUserDataEntityDao().queryBuilder().where(
                UserDataDao.Properties.ActivityId.eq(activity.getId()),
                UserDataDao.Properties.IceBeacon.isNull()).count();
        summary=String.format("%s%.3f",summary,noiceNull*100/totalSamples)+"%\n";
        long nobb2Null = greenDaoHelper.getUserDataEntityDao().queryBuilder().where(
                UserDataDao.Properties.ActivityId.eq(activity.getId()),
                UserDataDao.Properties.BbBeacon2.isNull()).count();
        summary=String.format("%s%.3f",summary,nobb2Null*100/totalSamples)+"%\n";
        long nomint2Null = greenDaoHelper.getUserDataEntityDao().queryBuilder().where(
                UserDataDao.Properties.ActivityId.eq(activity.getId()),
                UserDataDao.Properties.MintBeacon2.isNull()).count();
        summary=String.format("%s%.3f",summary,nomint2Null*100/totalSamples)+"%\n";
        long noice2Null = greenDaoHelper.getUserDataEntityDao().queryBuilder().where(
                UserDataDao.Properties.ActivityId.eq(activity.getId()),
                UserDataDao.Properties.IceBeacon2.isNull()).count();
        summary=String.format("%s%.3f",summary,noice2Null*100/totalSamples)+"%\n";
        postEvent(ValidateEvent.nullFinder,currentUser,summary);
    }


    private void postEvent(int type, User currentUser) {
        postEvent(type, currentUser, null);
    }

    private void postEvent(int type, User currentUser, String message) {
        ValidateEvent validateEvent = new ValidateEvent();
        validateEvent.setEventType(type);
        validateEvent.setUser(currentUser);
        if (message != null) {
            validateEvent.setMessage(message);
        }
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(validateEvent);
    }
}
