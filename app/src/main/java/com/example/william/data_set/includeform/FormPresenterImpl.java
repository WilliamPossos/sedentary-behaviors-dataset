package com.example.william.data_set.includeform;

import android.util.Log;

import com.example.william.data_set.includeform.events.RegisterEvent;
import com.example.william.data_set.includeform.ui.PersonalFormView;
import com.example.william.data_set.lib.EventBus;
import com.example.william.data_set.lib.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by william on 5/09/16.
 */
public class FormPresenterImpl implements FormPresenter {
    EventBus eventBus;
    PersonalFormView personalFormView;
    FormInteractor formInteractor;

    public FormPresenterImpl(PersonalFormView personalFormView) {
        this.personalFormView = personalFormView;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.formInteractor = new FormInteractorimpl();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);

    }

    @Override
    public void onDestroy() {
        personalFormView = null;
        eventBus.unregister(this);

    }

    @Override
    public void RegisterNewUser(String userName, String name, String lastName,
                                int age, String gender,int weight, int stature, int waist,
                                String profession, String mail, String smoke,
                                String drink, String transport) {
        formInteractor.doRegister(userName, name, lastName, age, gender, weight,
                stature, waist, profession,mail,smoke,drink,transport);

    }

    @Override
    public void checkForAuthenticatedUser() {

    }

    @Override
    @Subscribe
    public void onEventMainThread(RegisterEvent event) {
        switch (event.getEventType()) {
            case RegisterEvent.registerError:
                onRegisterError(event.getErrorMesage());
                break;
            case RegisterEvent.registerSuccess:
                onRegisterSuccess();
                break;
            case RegisterEvent.onFailedToRecoverSession:
                onFailedToRecoverSession();
                break;
        }
    }

    private void onFailedToRecoverSession() {
        Log.i("--------","onFailedToRecoverSession");
    }

    private void onRegisterSuccess() {
        if (personalFormView != null) {
            personalFormView.newUserSuccess();
        }
    }

    private void onRegisterError(String errorMesage) {
        if (personalFormView != null) {
            personalFormView.newUserError(errorMesage);
        }
    }


}
