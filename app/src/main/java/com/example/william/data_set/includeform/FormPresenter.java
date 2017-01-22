package com.example.william.data_set.includeform;

import com.example.william.data_set.includeform.events.RegisterEvent;

/**
 * Created by william on 5/09/16.
 */
public interface FormPresenter {
    void onCreate();
    void onDestroy();
    void RegisterNewUser(String userName, String name, String lastName,
                         int age, String gender,int weight, int stature, int waist,
                         String profession, String mail, String smoke,
                         String drink, String transport);
    void checkForAuthenticatedUser();
    public void onEventMainThread(RegisterEvent event);

}
