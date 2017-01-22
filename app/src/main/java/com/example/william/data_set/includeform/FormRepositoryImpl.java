package com.example.william.data_set.includeform;

import android.util.Log;

import com.example.william.data_set.Action;
import com.example.william.data_set.Activity;
import com.example.william.data_set.Location;
import com.example.william.data_set.Posture;
import com.example.william.data_set.User;
import com.example.william.data_set.UserDao;
import com.example.william.data_set.UserDataDao;
import com.example.william.data_set.domain.GreenDaoHelper;
import com.example.william.data_set.includeform.events.RegisterEvent;
import com.example.william.data_set.lib.EventBus;
import com.example.william.data_set.lib.GreenRobotEventBus;


/**
 * Created by WilliamStibent on 02/09/2016.
 */
public class FormRepositoryImpl implements FormRepository {
    private static final String ALREDYEXIST = "El nombre de usuario ya existe";
    private GreenDaoHelper greenDaoHelper;

    public FormRepositoryImpl() {
        this.greenDaoHelper = GreenDaoHelper.getInstance();
    }

    @Override
    public void Register(String userName, String name, String lastName,
                         int age, String gender,int weight, int stature, int waist,
                         String profession, String mail, String smoke,
                         String drink, String transport) {
        if (greenDaoHelper.getUserEntityDao().queryBuilder().
                where(UserDao.Properties.Username.eq(userName)).unique()==null){
            User user = new User();
            user.setUsername(userName);
            user.setName(name);
            user.setLastname(lastName);
            user.setAge(age);
            user.setGender(gender);
            user.setWeight(weight);
            user.setStature(stature);
            user.setWaist(waist);
            user.setProfession(profession);
            user.setMail(mail);
            user.setSmokeFrec(smoke);
            user.setDrinkFrec(drink);
            user.setTransport(transport);
            greenDaoHelper.getUserEntityDao().insert(user);

            if (greenDaoHelper.getLocationEntityDao().loadAll().size()==0){
                Location sofa = new Location(null, "SOFA");
                Location bed = new Location(null, "CAMA");
                Location desk = new Location(null, "ESCRITORIO");

                greenDaoHelper.getLocationEntityDao().insert(sofa);
                greenDaoHelper.getLocationEntityDao().insert(bed);
                greenDaoHelper.getLocationEntityDao().insert(desk);

                //Load postures for test relations and querys
                Posture sitting = new Posture(null, "sentado");
                Posture lying = new Posture(null, "reclinado");
                Posture reclining = new Posture(null, "acostado");

                greenDaoHelper.getPostureEntityDao().insert(sitting);
                greenDaoHelper.getPostureEntityDao().insert(lying);
                greenDaoHelper.getPostureEntityDao().insert(reclining);


                Action usingMobile= new Action(null,"Usando el telefono");
                Action watchingTV= new Action(null,"Viendo television");
                Action usingCompurer= new Action(null,"Usando el computador");
                Action iddle= new Action(null,"En reposo");

                greenDaoHelper.getActionEntityDao().insert(usingMobile);
                greenDaoHelper.getActionEntityDao().insert(watchingTV);
                greenDaoHelper.getActionEntityDao().insert(usingCompurer);
                greenDaoHelper.getActionEntityDao().insert(iddle);
            }
            //Load activities for test relations and querys

            Activity compound = new Activity(null, "No realizada",user.getId(),3,3,1);
            Activity compound1 = new Activity(null, "No realizada",user.getId(),3,2,1);
            Activity compound2= new Activity(null, "No realizada",user.getId(),3,3,2);
            Activity compound3 = new Activity(null, "No realizada",user.getId(),3,2,2);
            Activity compound4 = new Activity(null, "No realizada",user.getId(),1,3,1);
            Activity compound5 = new Activity(null, "No realizada",user.getId(),1,2,1);
            Activity compound6 = new Activity(null, "No realizada",user.getId(),1,1,1);
            Activity compound7 = new Activity(null, "No realizada",user.getId(),1,3,2);
            Activity compound8 = new Activity(null, "No realizada",user.getId(),1,2,2);
            Activity compound9 = new Activity(null, "No realizada",user.getId(),1,1,2);
            Activity compound10 = new Activity(null, "No realizada",user.getId(),1,2,3);
            Activity compound11 = new Activity(null, "No realizada",user.getId(),1,1,3);
            Activity compound12 = new Activity(null, "No realizada",user.getId(),2,1,1);
            Activity compound13 = new Activity(null, "No realizada",user.getId(),2,1,2);
            Activity compound14 = new Activity(null, "No realizada",user.getId(),2,1,3);
            Activity compound15 = new Activity(null, "No realizada",user.getId(),4,3,1);
            Activity compound16 = new Activity(null, "No realizada",user.getId(),4,1,1);
            Activity compound17 = new Activity(null, "No realizada",user.getId(),4,2,1);
            Activity compound18 = new Activity(null, "No realizada",user.getId(),4,3,2);
            Activity compound19 = new Activity(null, "No realizada",user.getId(),4,1,2);
            Activity compound20 = new Activity(null, "No realizada",user.getId(),4,2,2);
            Activity compound21 = new Activity(null, "No realizada",user.getId(),4,1,3);
            Activity compound22 = new Activity(null, "No realizada",user.getId(),4,2,3);

            greenDaoHelper.getActivityDao().insert(compound);
            greenDaoHelper.getActivityDao().insert(compound1);
            greenDaoHelper.getActivityDao().insert(compound2);
            greenDaoHelper.getActivityDao().insert(compound3);
            greenDaoHelper.getActivityDao().insert(compound4);
            greenDaoHelper.getActivityDao().insert(compound5);
            greenDaoHelper.getActivityDao().insert(compound6);
            greenDaoHelper.getActivityDao().insert(compound7);
            greenDaoHelper.getActivityDao().insert(compound8);
            greenDaoHelper.getActivityDao().insert(compound9);
            greenDaoHelper.getActivityDao().insert(compound10);
            greenDaoHelper.getActivityDao().insert(compound11);
            greenDaoHelper.getActivityDao().insert(compound12);
            greenDaoHelper.getActivityDao().insert(compound13);
            greenDaoHelper.getActivityDao().insert(compound14);
            greenDaoHelper.getActivityDao().insert(compound15);
            greenDaoHelper.getActivityDao().insert(compound16);
            greenDaoHelper.getActivityDao().insert(compound17);
            greenDaoHelper.getActivityDao().insert(compound18);
            greenDaoHelper.getActivityDao().insert(compound19);
            greenDaoHelper.getActivityDao().insert(compound20);
            greenDaoHelper.getActivityDao().insert(compound21);
            greenDaoHelper.getActivityDao().insert(compound22);

            if (user != null) {

                postEvent(RegisterEvent.registerSuccess);
            }
            else {
                postEvent(RegisterEvent.registerError);
            }
        }
        else{
            postEvent(RegisterEvent.registerError,ALREDYEXIST);
        }

    }
    private void postEvent(int type) {
        postEvent(type, null);
    }

    private void postEvent(int type, String errorMessage) {
        RegisterEvent registerEvent = new RegisterEvent();
        registerEvent.setEventType(type);
        if (errorMessage != null) {
            registerEvent.setErrorMesage(errorMessage);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(registerEvent);
    }
}
