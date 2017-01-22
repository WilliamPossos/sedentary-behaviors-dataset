package com.example.william.data_set.includeform.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.william.data_set.R;
import com.example.william.data_set.includeform.FormPresenter;
import com.example.william.data_set.includeform.FormPresenterImpl;
import com.example.william.data_set.login.ui.LoginActivity;
import com.example.william.data_set.selectactivity.ui.ListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class PersonalFormActivity extends AppCompatActivity implements PersonalFormView {

    public static final String PREFERENCE = "preference";
    public static final String KEY_LOGIN = "login";
    public static final String SESSION = "MySession";
    public static final String USERNAME = "userName";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.editTxtUserName)
    EditText editTxtUserName;
    @BindView(R.id.editTxtName)
    EditText editTxtName;
    @BindView(R.id.editTxtLastName)
    EditText editTxtLastName;
    @BindView(R.id.editTxtAge)
    EditText editTxtAge;
    @BindView(R.id.txtViewGender)
    AppCompatTextView txtViewGender;
    @BindView(R.id.cmbGender)
    AppCompatSpinner cmbGender;
    @BindView(R.id.editTxtWeight)
    EditText editTxtWeight;
    @BindView(R.id.editTxtStature)
    EditText editTxtStature;
    @BindView(R.id.editTxtProfession)
    EditText editTxtProfession;
    @BindView(R.id.editTxtWaist)
    EditText editTxtWaist;
    @BindView(R.id.editTxtMail)
    EditText editTxtMail;
    @BindView(R.id.txtSmoke)
    AppCompatTextView txtSmoke;
    @BindView(R.id.cmbSmoke)
    AppCompatSpinner cmbSmoke;
    @BindView(R.id.txtDrink)
    AppCompatTextView txtDrink;
    @BindView(R.id.cmbDrink)
    AppCompatSpinner cmbDrink;
    @BindView(R.id.txtTransport)
    AppCompatTextView txtTransport;
    @BindView(R.id.cmbTransport)
    AppCompatSpinner cmbTransport;
    @BindView(R.id.btnConfirm)
    AppCompatButton btnConfirm;
    @BindView(R.id.layoutFormContainer)
    CoordinatorLayout layoutFormContainer;

    private FormPresenter formPresenter;
    private Context context;
    String smokeFrec;
    String drinkFrec;
    String gender;
    String transport;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        ButterKnife.bind(this);
        loadDefauldValues();


        formPresenter = new FormPresenterImpl(this);
        formPresenter.onCreate();

        preferences = getSharedPreferences(LoginActivity.PREFERENCE, MODE_PRIVATE);
        editor = preferences.edit();
        if (cmbGender != null) {
            Log.e("----", "" + cmbGender.getSelectedItem());
        }
        if (cmbSmoke != null) {
            Log.e("----", "" + cmbSmoke.getSelectedItem());
        }
        if (cmbDrink != null) {
            Log.e("----", "" + cmbDrink.getSelectedItem());
        }


        //txt.setText(preferences.getString(LoginActivity.KEY_USER, ""));


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        formPresenter.onDestroy();
    }

    private void loadDefauldValues() {
        //Gender menu
        String[] gender_menu = new String[]{
                this.getString(R.string.form_gender_male),
                this.getString(R.string.form_gender_female)};
        ArrayAdapter<String> genderAdapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, gender_menu);
        genderAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        cmbGender.setAdapter(genderAdapter);

        //¿smoke?

        ArrayAdapter<CharSequence> smokeAdapter = ArrayAdapter.createFromResource(
                this, R.array.FrecuenceSmoke, android.R.layout.simple_spinner_item);
        smokeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmbSmoke.setAdapter(smokeAdapter);
        //¿drink?
        ArrayAdapter<CharSequence> drinkAdapter = ArrayAdapter.createFromResource(
                this, R.array.FrecuenceDrink, android.R.layout.simple_spinner_item);
        drinkAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmbDrink.setAdapter(drinkAdapter);
        //¿Transport?
        ArrayAdapter<CharSequence> transportAdapter = ArrayAdapter.createFromResource(
                this, R.array.Transport, android.R.layout.simple_spinner_item);
        transportAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmbTransport.setAdapter(transportAdapter);
    }

    @OnItemSelected(R.id.cmbGender)
    public void genderItemSelected(Spinner spinner, int position) {
        gender = spinner.getSelectedItem().toString();
    }

    @OnItemSelected(R.id.cmbSmoke)
    public void smokeItemSelected(Spinner spinner, int position) {
        smokeFrec = spinner.getSelectedItem().toString();

    }

    @OnItemSelected(R.id.cmbDrink)
    public void drinkItemSelected(Spinner spinner, int position) {
        drinkFrec = spinner.getSelectedItem().toString();
    }

    @OnItemSelected(R.id.cmbTransport)
    public void transportItemSelected(Spinner spinner, int position) {
        transport = spinner.getSelectedItem().toString();
    }

    @Override
    public void navigateToActivities() {
        preferences = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        editor = preferences.edit();
        editor.putBoolean(KEY_LOGIN, true);
        editor.putString(USERNAME, editTxtUserName.getText().toString());
        editor.commit();
        Intent view = new Intent(this, ListActivity.class);
        view.putExtra(ListActivity.USERNAME, editTxtMail.getText().toString());
        startActivity(view);

    }

    @OnClick(R.id.btnConfirm)
    @Override
    public void handleRegister() {
        /*List users = GreenDaoHelper.getInstance().getUserEntityDao().queryBuilder().where(UserDao.Properties.Names.eq("w")).list();
        Log.e("+++++", "" + users);*/

        if (!editTxtUserName.getText().toString().equals("")&&
            !editTxtName.getText().toString().equals("")&&
            !editTxtLastName.getText().toString().equals("")&&
            !cmbGender.getSelectedItem().toString().equals("")&&
            !editTxtAge.getText().toString().equals("")&&
            !editTxtWeight.getText().toString().equals("")&&
            !editTxtStature.getText().toString().equals("")&&
            !editTxtWaist.getText().toString().equals("")&&
            !editTxtProfession.getText().toString().equals("")&&
            !editTxtMail.getText().toString().equals("")
            )
        {


        int age = Integer.parseInt(editTxtAge.getText().toString());
        int weight = Integer.parseInt(editTxtWeight.getText().toString());
        int height = Integer.parseInt(editTxtStature.getText().toString());
        int waist = Integer.parseInt(editTxtWaist.getText().toString());

        formPresenter.RegisterNewUser(
                editTxtUserName.getText().toString(),
                editTxtName.getText().toString(),
                editTxtLastName.getText().toString(),
                age,
                cmbGender.getSelectedItem().toString(),
                weight,
                height,
                waist,
                editTxtProfession.getText().toString(),
                editTxtMail.getText().toString(),
                smokeFrec,
                drinkFrec,
                transport);
        }
        else
        {
            newUserError(getString(R.string.error_form_inconplete));
        }

    }

    @Override
    public void newUserSuccess() {
        Snackbar.make(layoutFormContainer, R.string.register_notice_message_useradded, Snackbar.LENGTH_SHORT).show();
        navigateToActivities();
    }

    @Override
    public void newUserError(String error) {
        Snackbar.make(layoutFormContainer, error, Snackbar.LENGTH_SHORT).show();
    }
}