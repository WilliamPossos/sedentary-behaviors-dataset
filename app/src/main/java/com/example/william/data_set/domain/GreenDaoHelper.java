package com.example.william.data_set.domain;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.example.william.data_set.ActionDao;
import com.example.william.data_set.ActivityDao;
import com.example.william.data_set.Posture;
import com.example.william.data_set.PostureDao;
import com.example.william.data_set.AndroidCollectAplication;
import com.example.william.data_set.DaoMaster;
import com.example.william.data_set.DaoSession;
import com.example.william.data_set.LocationDao;
import com.example.william.data_set.UserDao;
import com.example.william.data_set.UserDataDao;


import org.greenrobot.greendao.database.Database;

import java.io.File;

/**
 * Created by william on 5/09/16.
 */
public class GreenDaoHelper {

    private static final java.lang.String DATABASE_NAME = "collection-db";
    private static GreenDaoHelper instance;
    private DaoSession daoSession;
    private Context context;

    public Context getContext() {
        return context;
    }

    public static class SingletonHolder{
        private static final GreenDaoHelper INSTANCE = new GreenDaoHelper(AndroidCollectAplication.getAppContext());
    }

    public static GreenDaoHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /*public static GreenDaoHelper getInstance(){
        if (instance == null){
            instance = new GreenDaoHelper(this.context);
        }
        return instance;
    }*/
    public GreenDaoHelper(Context context) {
        this.context = context;
        File path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "sedentariesBehaviors/"+DATABASE_NAME);
        path.getParentFile().mkdirs();

        Log.e("path",path.getName()+"  "+path.getAbsolutePath()+"  "+path.getParent()+"  "+path.getPath());
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, path.getAbsolutePath(), null);
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public UserDao getUserEntityDao() {
        return daoSession.getUserDao();
    }
    public UserDataDao getUserDataEntityDao() {
        return daoSession.getUserDataDao();
    }
    public ActivityDao getActivityDao(){return daoSession.getActivityDao();}
    public DaoSession getDaoSession(){
        return daoSession;
    }
    public LocationDao getLocationEntityDao(){return daoSession.getLocationDao();}
    public PostureDao getPostureEntityDao(){return daoSession.getPostureDao();}
    public ActionDao getActionEntityDao(){return daoSession.getActionDao();}

}
