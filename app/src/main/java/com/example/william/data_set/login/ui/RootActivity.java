package com.example.william.data_set.login.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.william.data_set.selectactivity.ui.ListActivity;


/**
 * Created by william on 20/10/16.
 */

public class RootActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getSharedPreferences(LoginActivity.PREFERENCE, MODE_PRIVATE);
        boolean login = preferences.getBoolean(LoginActivity.KEY_LOGIN, false);
        Intent intent = null;

        if(login)
            intent = new Intent(this, ListActivity.class);
        else
            intent =  new Intent(this, LoginActivity.class);

        startActivity(intent);
    }
}

