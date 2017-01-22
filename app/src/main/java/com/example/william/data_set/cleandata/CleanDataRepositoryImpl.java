package com.example.william.data_set.cleandata;


import android.util.Log;

import com.example.william.data_set.UserData;
import com.example.william.data_set.UserDataDao;
import com.example.william.data_set.domain.GreenDaoHelper;
import com.example.william.data_set.includeform.events.RegisterEvent;
import com.example.william.data_set.lib.EventBus;
import com.example.william.data_set.lib.GreenRobotEventBus;

import org.greenrobot.greendao.query.CountQuery;

import java.util.List;

/**
 * Created by william on 20/11/16.
 */
public class CleanDataRepositoryImpl implements CleanDataRepository {
    private static final int NUMBER_OF_SAMPLES = 7500;
    GreenDaoHelper helper;
    long sampleCount=0;



    public CleanDataRepositoryImpl() {
        this.helper = GreenDaoHelper.getInstance();
    }

    @Override
    public void deleteSamples(int activityId) {

        sampleCount = helper.getUserDataEntityDao().queryBuilder().
                where(UserDataDao.Properties.ActivityId.eq(activityId)).count();
        Log.e("number of samples",""+sampleCount);

        UserData data = helper.getUserDataEntityDao().queryBuilder().
                where(UserDataDao.Properties.ActivityId.eq(activityId)).limit(1).unique();
        Log.e("first Id ",""+data.getId());
        long firstId = data.getId();
        long breakPoint= firstId+NUMBER_OF_SAMPLES;
        if(sampleCount>NUMBER_OF_SAMPLES)
        {
            helper.getUserDataEntityDao().queryBuilder().
                    where(UserDataDao.Properties.ActivityId.eq(activityId),
                            UserDataDao.Properties.Id.ge(breakPoint)).buildDelete().
                    executeDeleteWithoutDetachingEntities();
        }
        else
        {
            Log.e("No realizado", "El número de muestras es mínimo");
        }

    }

    @Override
    public void deleteSpecificSamples(int activityId, int first) {
        helper.getUserDataEntityDao().queryBuilder().
                where(UserDataDao.Properties.ActivityId.eq(activityId),
                        UserDataDao.Properties.Id.lt(first),
                        UserDataDao.Properties.Id.ge(first+NUMBER_OF_SAMPLES)).buildDelete().
                executeDeleteWithoutDetachingEntities();

    }

    @Override
    public void updateRecords(int activityId) {
        if (activityId>=1&&activityId<=4)
        {
            List <UserData> data = helper.getUserDataEntityDao().queryBuilder().
                    where(UserDataDao.Properties.ActivityId.eq(activityId)).list();
            for (UserData u:data
                 ) {
                u.setIceBeacon2(null);
                helper.getUserDataEntityDao().update(u);
            }
        } else if (activityId>=5&&activityId<=12)
        {
            List <UserData> data = helper.getUserDataEntityDao().queryBuilder().
                    where(UserDataDao.Properties.ActivityId.eq(activityId)).list();
            for (UserData u:data
                    ) {
                u.setBbBeacon2(null);
                helper.getUserDataEntityDao().update(u);
            }
        }
        else if (activityId>=13)
        {
            List <UserData> data = helper.getUserDataEntityDao().queryBuilder().
                    where(UserDataDao.Properties.ActivityId.eq(activityId)).list();
            for (UserData u:data
                    ) {
                u.setBbBeacon2(null);
                u.setIceBeacon2(null);
                helper.getUserDataEntityDao().update(u);
            }
        }

    }

    @Override
    public void previewData(int activityId) {
        CountQuery cq = helper.getUserDataEntityDao().queryBuilder().
                where(UserDataDao.Properties.ActivityId.eq(activityId)).buildCount();
        sampleCount = cq.count();
        Log.e("number of samples",""+sampleCount);

        List<UserData> data = helper.getUserDataEntityDao().queryBuilder().
                where(UserDataDao.Properties.ActivityId.eq(activityId)).list();
        long firstId = data.get(0).getId();
        long breakPoint= firstId+NUMBER_OF_SAMPLES;
        CountQuery cqBreak = helper.getUserDataEntityDao().queryBuilder().
                where(UserDataDao.Properties.ActivityId.eq(activityId),
                        UserDataDao.Properties.Id.le(breakPoint)).buildCount();
        Log.e("number of samples",""+cqBreak.count());

        if (sampleCount>7600)
        {
            for (int i=0;i<=100;i++){
                Log.e("FirstSamples ",""+data.get(i).getId()+""+i+" "+data.get(i).getMobileAccX());
            }
            for (int j = 7400; j<=7600; j++){
                Log.e("EndSamples ",""+data.get(j).getId()+""+j+" "+data.get(j).getMobileAccX());
            }
        }


    }
    private void postEvent(int type) {
        postEvent(type, null, null);
    }

    private void postEvent(int type, String firstSamples, String endSamples) {
        RegisterEvent registerEvent = new RegisterEvent();
        registerEvent.setEventType(type);
        if (firstSamples != null) {
            registerEvent.setErrorMesage(firstSamples);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(registerEvent);
    }
}
