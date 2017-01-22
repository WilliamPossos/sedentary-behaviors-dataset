package com.example.william.data_set.validatedata;

/**
 * Created by william on 17/10/16.
 */
public class ValidateInteractorImpl implements ValidateInteractor {
    ValidateRepository validateRepository;

    public ValidateInteractorImpl() {
        this.validateRepository = new ValidateRepositoryImpl();
    }

    @Override
    public void showUserData(String userName) {
        validateRepository.getUser(userName);
    }

    @Override
    public void selectActivity(String postureName, String compoundActivity) {
        validateRepository.validateActivity(postureName,compoundActivity);
    }

    @Override
    public void doValidate(Boolean success) {
        validateRepository.allSuccess(success);
    }

    @Override
    public void showSummary() {

    }
}
