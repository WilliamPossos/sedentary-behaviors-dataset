package com.example.william.data_set.selectactivity;

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
import com.example.william.data_set.domain.GreenDaoHelper;
import com.example.william.data_set.lib.EventBus;
import com.example.william.data_set.lib.GreenRobotEventBus;
import com.example.william.data_set.selectactivity.event.LoadActivityEvent;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by william on 24/09/16.
 */
public class SelectionRepositoryImpl implements selectionRepository {
    private static final String TAG = "SelectionRepo";
    private static final String LOCATIONADJUST = "Sitio: ";
    private static final String INTERLINE = "\n\t\t";
    private GreenDaoHelper greenDaoHelper;

    public SelectionRepositoryImpl() {
        this.greenDaoHelper = GreenDaoHelper.getInstance();
    }

    @Override
    public void updateActivityList() {


    }

    @Override
    public void loadActivities(String mail) {

        if (greenDaoHelper.getActivityDao().loadAll().size()!=0){

            //Load locations for test relations and querys

            User currentUser = greenDaoHelper.getUserEntityDao().queryBuilder().
                    where(UserDao.Properties.Username.eq(mail)).unique();
            postEvent(LoadActivityEvent.userLoad,currentUser);

            List<Posture> postures= greenDaoHelper.getPostureEntityDao().
                    queryBuilder().orderAsc(PostureDao.Properties.Posture).build().list();
            for (Posture p: postures
                    ) {
                List<Activity> activities = null;
                QueryBuilder qb = greenDaoHelper.getActivityDao().queryBuilder();
                qb.where(ActivityDao.Properties.UserId.eq(currentUser.getId()),
                        qb.and(ActivityDao.Properties.UserId.eq(currentUser.getId()),
                                ActivityDao.Properties.PostureId.eq(p.getId())));
                activities = qb.orderAsc(ActivityDao.Properties.LocationId).list();
                //Local variables for set values on arrays

                int i=0;
                int j=0;
                int k=0;
                int l=0;
                String[] actByPosture = new String[activities.size()];
                String[] actPictures = new String[activities.size()];
                String[] actStatus = new String[activities.size()];
                String[] actLocation = new String[activities.size()];
                for (Activity act: activities
                        ) {
                    actStatus[j]=act.getStatus();
                    actByPosture[i] =greenDaoHelper.getActionEntityDao().//With this i get the action for each
                            loadByRowId(act.getActionId()).getName()//compound activity
                            +INTERLINE
                            +greenDaoHelper.getLocationEntityDao().//Whit this I get the location for
                            loadByRowId(act.getLocationId()).getLocationName();//each compound activity

                    Log.e("-----",""+actByPosture[i]+"--"+actStatus[j]);
                    i++;j++;
                }
                if (actStatus != null || actByPosture!=null) {
                    postEvent(LoadActivityEvent.activityLoad,actByPosture,actStatus,currentUser,p.getPosture());
                }

            }
        }


    }


    private void postEvent(int type, User user){
        postEvent(type,null,null,user,null);
    }
    private void postEvent(int type, String[] activities, String[] pictures,User user, String posture) {
        LoadActivityEvent activityEvent = new LoadActivityEvent();
        activityEvent.setEventType(type);
        activityEvent.setPosture(posture);
        if (pictures != null) {
            activityEvent.setPictures(pictures);
        }
        if (user != null) {
            activityEvent.setUser(user);
        }
        if (activities != null) {
            activityEvent.setActivities(activities);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(activityEvent);
    }
}
