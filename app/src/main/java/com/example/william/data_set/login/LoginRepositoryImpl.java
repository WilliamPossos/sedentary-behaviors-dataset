package com.example.william.data_set.login;

import com.example.william.data_set.R;
import com.example.william.data_set.User;
import com.example.william.data_set.UserDao;
import com.example.william.data_set.domain.GreenDaoHelper;
import com.example.william.data_set.lib.EventBus;
import com.example.william.data_set.lib.GreenRobotEventBus;
import com.example.william.data_set.login.event.LoginEvent;

/**
 * Created by william on 19/10/16.
 */
public class LoginRepositoryImpl implements LoginRepository {
    private static final String USERNOFOUND = "Nombre de usuario no válido";
    private GreenDaoHelper helper;

    public LoginRepositoryImpl() {
        this.helper = GreenDaoHelper.getInstance();
    }

    @Override
    public void findUser(String user) {
        User currentUser=helper.getUserEntityDao().queryBuilder().
                where(UserDao.Properties.Username.eq(user)).unique();
        if (currentUser != null) {
            postEvent(LoginEvent.success);
        }
        else{
            postEvent(LoginEvent.error, USERNOFOUND);
        }
    }

    private void postEvent(int type) {
        postEvent(type,null);
    }

    private void postEvent(int type, String message) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if (message != null) {
            loginEvent.setMessage(message);
        }
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);

    }
}
