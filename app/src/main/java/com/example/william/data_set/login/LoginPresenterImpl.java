package com.example.william.data_set.login;

import com.example.william.data_set.lib.EventBus;
import com.example.william.data_set.lib.GreenRobotEventBus;
import com.example.william.data_set.login.event.LoginEvent;
import com.example.william.data_set.login.ui.LoginView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by william on 19/10/16.
 */
public class LoginPresenterImpl implements LoginPresenter{
    LoginView loginView;
    EventBus eventBus;
    LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.eventBus= GreenRobotEventBus.getInstance();
        this.loginInteractor = new LoginInteractorImpl();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }

    @Override
    public void login(String user) {
        loginInteractor.doLogin(user);
    }

    @Subscribe
    @Override
    public void onEventMainThread(LoginEvent event) {
        switch (event.getEventType()){
            case LoginEvent.success:
                loginView.navigateToListActivity();
                break;
            case LoginEvent.error:
                loginView.showSnackBar(event.getMessage());
        }

    }
}
