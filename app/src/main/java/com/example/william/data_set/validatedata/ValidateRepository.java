package com.example.william.data_set.validatedata;

/**
 * Created by william on 17/10/16.
 */
public interface ValidateRepository {
    void getUser(String mail);
    void validateActivity(String posture, String activity);

    void allSuccess(Boolean success);
}
