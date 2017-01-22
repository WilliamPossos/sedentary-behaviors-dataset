package com.example.william.data_set.validatedata;

import com.example.william.data_set.lib.EventBus;
import com.example.william.data_set.lib.GreenRobotEventBus;
import com.example.william.data_set.validatedata.event.ValidateEvent;
import com.example.william.data_set.validatedata.ui.ValidateView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by william on 17/10/16.
 */

public class ValidatePresenterImpl implements ValidatePresenter {
    private static final int COMPLETE = 1;
    private static final int INCOMPLETE = 0;
    EventBus eventBus;
    ValidateView validateView;
    ValidateInteractor validateInteractor;

    public ValidatePresenterImpl(ValidateView validateView) {
        this.validateView = validateView;
        this.validateInteractor = new ValidateInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void OnResume() {

    }

    @Subscribe
    @Override
    public void onEventMainThread(ValidateEvent event) {
        switch (event.getEventType()){
            case ValidateEvent.userSuccess:
                validateView.loadUser(event.getUser());
                break;
            case ValidateEvent.unfilled:
                validateView.NavigateToList(INCOMPLETE);
                break;
            case ValidateEvent.filled:
                validateView.NavigateToList(COMPLETE);
                break;
            case ValidateEvent.nullFinder:
                validateView.showSummary(event.getMessage());
                break;

        }
    }

    @Override
    public void getuser(String userName) {
        validateInteractor.showUserData(userName);
    }

    @Override
    public void validateData(Boolean success) {
        validateInteractor.doValidate(success);
    }

    @Override
    public void selectActivity(String postureName, String compoundActivity) {
        validateInteractor.selectActivity(postureName,compoundActivity);
    }


}
