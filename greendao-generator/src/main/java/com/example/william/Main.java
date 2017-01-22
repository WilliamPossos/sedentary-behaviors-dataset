package com.example.william;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;
import org.greenrobot.greendao.generator.ToMany;

public class Main {
    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(7, "com.example.william.data_set");

        addEntities(schema);

        new DaoGenerator().generateAll(schema, "./app/src/main/java");
    }
    private static void addEntities(Schema schema) {
        Entity activity = addActivity(schema);
        Entity user = addUser(schema);
        Entity userdata = addUserData(schema);
        Entity location = addLocation(schema);
        Entity posture = addPosture(schema);//activityPosition
        Entity action = addAction(schema);
//        Entity ae_activity_location = addAEactivityLocation(schema);
//        Entity ae_activity_position = addAEactivityPosition(schema);

        //One to Many

        Property userIdOnActivity = activity.addLongProperty("userId")
                .notNull().getProperty();
        ToMany usersToActivity = user.addToMany(activity, userIdOnActivity);

        Property activityIdOnData = userdata.addLongProperty("activityId")
                .notNull().getProperty();
        ToMany activityToData = activity.addToMany(userdata,activityIdOnData);

        Property actionIdOnActivity = activity.addLongProperty("actionId")
                .notNull().getProperty();
        ToMany actionToActivity = action.addToMany(activity,actionIdOnActivity);

        Property locationIdOnActivity = activity.addLongProperty("locationId")
                .notNull().getProperty();
        ToMany locationToActivity = location.addToMany(activity,locationIdOnActivity);

        Property postureIdOnActivity = activity.addLongProperty("postureId")
                .notNull().getProperty();
        ToMany postureToActivity = posture.addToMany(activity,postureIdOnActivity);

        /*Property activityIdOnLocation = location.addLongProperty("activityId")
                .notNull().getProperty();
        ToMany activityToLocation = activity.addToMany(location,activityIdOnLocation);*/




/*      //One to Many, associative entities
            //Activity to associative relation with position (activity position)
        Property activityIdOn_actPos = ae_activity_position.addLongProperty("activityId")
                .notNull().getProperty();
        ToMany activityToAE = activity.addToMany(ae_activity_position,activityIdOn_actPos);

        Property positionIdOn_actPos = ae_activity_position.addLongProperty("positionId")
                .notNull().getProperty();
        ToMany positionToAE = activityPosition.addToMany(ae_activity_position,positionIdOn_actPos);

            //Activity to associative relation with location (activity location)

        Property activityIdOnAEact_loc =ae_activity_location.
                addLongProperty("activityId").notNull().getProperty();
        ToMany activityToAEpos = activity.addToMany(ae_activity_location,activityIdOnAEact_loc);

        Property locationIdOnAEact_loc =ae_activity_location.
                addLongProperty("locationId").notNull().getProperty();
        ToMany locationToAEpos = location.addToMany(ae_activity_location,locationIdOnAEact_loc);
*/


    }

    private static Entity addAction(Schema schema) {
        Entity action = schema.addEntity("Action");
        action.addIdProperty().autoincrement().primaryKey();
        action.addStringProperty("name");
        return action;
    }

    private static Entity addAEactivityPosition(Schema schema) {
        Entity ae_act_pos = schema.addEntity("activities_positions");

        return ae_act_pos;
    }

    private static Entity addAEactivityLocation(Schema schema) {
        Entity ae_act_loc = schema.addEntity("activities_locations");
        return ae_act_loc;
    }

    private static Entity addLocation(Schema schema) {
        Entity location = schema.addEntity("Location");
        location.addIdProperty().autoincrement().primaryKey();
        location.addStringProperty("locationName");
        return location;
    }

    private static Entity addUserData(Schema schema) {
        Entity userdata =schema.addEntity("UserData");
        userdata.addIdProperty().autoincrement().primaryKey();
        userdata.addLongProperty("timestamp");
        userdata.addDoubleProperty("bandAccX");
        userdata.addDoubleProperty("bandAccY");
        userdata.addDoubleProperty("bandAccZ");
        userdata.addFloatProperty("bandAltimeterRate");
        userdata.addIntProperty("bandAmbientLight");
        userdata.addDoubleProperty("bandBarometerAir");
        userdata.addDoubleProperty("bandBarometerTemp");
        userdata.addIntProperty("bandGsr");
        userdata.addDoubleProperty("bandGyrX");
        userdata.addDoubleProperty("bandGyrY");
        userdata.addDoubleProperty("bandGyrZ");
        userdata.addIntProperty("bandHeartRate");
        userdata.addStringProperty("bandQoHR");
        userdata.addLongProperty("bandPedometer");
        userdata.addDoubleProperty("bandRR");
        userdata.addDoubleProperty("bandSkinTemperature");
        userdata.addStringProperty("bandUVindex");
        userdata.addFloatProperty("mobileAccX");
        userdata.addFloatProperty("mobileAccY");
        userdata.addFloatProperty("mobileAccZ");
        userdata.addFloatProperty("mobileGyrX");
        userdata.addFloatProperty("mobileGyrY");
        userdata.addFloatProperty("mobileGyrZ");
        userdata.addFloatProperty("mobileMagX");
        userdata.addFloatProperty("mobileMagY");
        userdata.addFloatProperty("mobileMagZ");
        userdata.addFloatProperty("mobileGraX");
        userdata.addFloatProperty("mobileGraY");
        userdata.addFloatProperty("mobileGraZ");
        userdata.addFloatProperty("mobileLinX");
        userdata.addFloatProperty("mobileLinY");
        userdata.addFloatProperty("mobileLinZ");
        userdata.addFloatProperty("mobileRotX");
        userdata.addFloatProperty("mobileRotY");
        userdata.addFloatProperty("mobileRotZ");
        userdata.addIntProperty("iceBeacon");
        userdata.addIntProperty("mintBeacon");
        userdata.addIntProperty("bbBeacon");
        userdata.addIntProperty("iceBeacon2");
        userdata.addIntProperty("mintBeacon2");
        userdata.addIntProperty("bbBeacon2");


        return userdata;
    }

    private static Entity addUser(Schema schema) {
        Entity user = schema.addEntity("User");
        user.addIdProperty().autoincrement().primaryKey();
        user.addStringProperty("name");
        user.addStringProperty("lastname");
        user.addStringProperty("username");
        user.addIntProperty("age");
        user.addStringProperty("gender");
        user.addIntProperty("weight");
        user.addIntProperty("stature");
        user.addStringProperty("profession");
        user.addStringProperty("mail");
        user.addStringProperty("smokeFrec");
        user.addStringProperty("drinkFrec");
        user.addStringProperty("transport");
        return user;
    }
    private static Entity  addActivity(Schema schema){
        Entity activity=schema.addEntity("Activity");
        activity.addIdProperty();
        activity.addStringProperty("status");
        return activity;
    }
    private static Entity addPosture(Schema schema){
        Entity activityPosition = schema.addEntity("Posture");
        activityPosition.addIdProperty();
        activityPosition.addStringProperty("posture");
        return activityPosition;

    }

}
