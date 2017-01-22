package com.example.william.data_set.login;

/**
 * Created by william on 19/10/16.
 */
public class LoginInteractorImpl implements LoginInteractor {
    private LoginRepository loginRepository;

    public LoginInteractorImpl() {
        this.loginRepository = new LoginRepositoryImpl();
    }

    @Override
    public void doLogin(String userName) {
        loginRepository.findUser(userName);
    }
}
