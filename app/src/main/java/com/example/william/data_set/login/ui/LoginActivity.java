package com.example.william.data_set.login.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.william.data_set.R;
import com.example.william.data_set.includeform.ui.PersonalFormActivity;
import com.example.william.data_set.login.LoginPresenter;
import com.example.william.data_set.login.LoginPresenterImpl;
import com.example.william.data_set.selectactivity.ui.ListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    public static final String PREFERENCE = "preference";
    public static final String KEY_LOGIN = "login";
    public static final String SESSION = "MySession";
    public static final String USERNAME = "userName";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txtPermissions)
    TextView txtPermissions;
    @BindView(R.id.cbPermissions)
    CheckBox cbPermissions;
    @BindView(R.id.edtPermissions)
    EditText edtLogin;
    @BindView(R.id.btnPermissions)
    Button btnPermissions;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    private LoginPresenter loginPresenter;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        loginPresenter = new LoginPresenterImpl(this);
        loginPresenter.onCreate();
        toolbar.setTitle("Dataset de Sedentarismo");
        txtPermissions.setText(R.string.large_text);
        btnPermissions.setText("Registrar Usuario");
        btnLogin.setText("Iniciar Sesión");
        cbPermissions.setText("Autorizo el uso de mis datos");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.onDestroy();
    }

    @OnClick({R.id.btnPermissions, R.id.btnLogin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPermissions:
                if (cbPermissions.isChecked()) {
                    navigateToFormActivity();
                } else {
                    Toast.makeText(this, "Debe aceptar los permisos para continuar", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnLogin:

                if (!edtLogin.getText().toString().matches("")) {
                    loginPresenter.login(edtLogin.getText().toString());
                } else {
                    Toast.makeText(this, "Nombre de Usuario vacio", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void showSnackBar(String message) {
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, message, Snackbar.LENGTH_SHORT);
        snackbar.show();

    }

    @Override
    public void showToast() {

    }

    @Override
    public void navigateToFormActivity() {
        Intent intent = new Intent(this, PersonalFormActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void navigateToListActivity() {
        preferences = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        editor = preferences.edit();
        editor.putBoolean(KEY_LOGIN, true);
        editor.putString(USERNAME, edtLogin.getText().toString());
        editor.commit();

        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
        finish();


    }
}
