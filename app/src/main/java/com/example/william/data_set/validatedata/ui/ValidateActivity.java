package com.example.william.data_set.validatedata.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.william.data_set.R;
import com.example.william.data_set.User;
import com.example.william.data_set.completecollection.EndedActivity;
import com.example.william.data_set.login.ui.LoginActivity;
import com.example.william.data_set.selectactivity.ui.ListActivity;
import com.example.william.data_set.validatedata.ValidatePresenter;
import com.example.william.data_set.validatedata.ValidatePresenterImpl;
import com.example.william.data_set.validatedata.fragment.NavigationValidateFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ValidateActivity extends AppCompatActivity implements ValidateView {

    public static final String ACTIVITY = "activity";
    public static final String POSTURE = "posture";
    private static final String ACTIVITYTITLE = "Validación";
    private static int VALIDATECASE = 0;
    private static int NOVALIDATECASE = 1;
    private int complete= 0;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.summaryFeature)
    TextView summaryFeature;
    @BindView(R.id.summaryPercent)
    TextView summaryPercent;
    @BindView(R.id.btn_validate)
    Button btnValidate;
    @BindView(R.id.btn_novalidate)
    Button btnNovalidate;
    @BindView(R.id.viewpager2)
    ViewPager viewpager2;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer)
    DrawerLayout drawer;

    private ValidatePresenter presenter;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate);
        ButterKnife.bind(this);

        preferences = getSharedPreferences(LoginActivity.PREFERENCE, MODE_PRIVATE);
        editor = preferences.edit();

        presenter = new ValidatePresenterImpl(this);
        presenter.onCreate();
        presenter.getuser(preferences.getString(LoginActivity.USERNAME, ""));
        presenter.selectActivity(getIntent().getStringExtra(POSTURE),
                getIntent().getStringExtra(ACTIVITY));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(ACTIVITYTITLE);


        // Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @OnClick(R.id.btn_validate)
    public void onClickVal() {
        showValidationDialog(getString(R.string.validate_title),
                getString(R.string.validate_message), VALIDATECASE);

    }

    @OnClick(R.id.btn_novalidate)
    public void onClickNoVal() {
        showValidationDialog(getString(R.string.restart_message),
                getString(R.string.invalidate_message), NOVALIDATECASE);
        presenter.validateData(false);
    }

    @Override
    public void showValidationDialog(String title, String message, final int flag) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(ValidateActivity.this);
        builder.setTitle(title);
        builder.setMessage(message);

        String positiveText = getString(R.string.accept);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (flag == 0) {
                            presenter.validateData(true);
                        } else if (flag == 1) {
                            Intent intent = new Intent(ValidateActivity.this, ListActivity.class);
                            startActivity(intent);
                        }
                    }
                });

        String negativeText = getString(R.string.cancel);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void loadUser(User user) {
        NavigationValidateFragment nf = new NavigationValidateFragment();
        nf.initFragment(user.getName(), user.getGender(), user.getAge(), user.getWeight(), user.getStature());
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.nav_view, nf);
        ft.commit();
    }

    @Override
    public void NavigateToList(int fill) {
        if (fill == 1) {
            editor.putBoolean(LoginActivity.KEY_LOGIN, false);
            editor.commit();

            Intent intent = new Intent(this, EndedActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            startActivity(new Intent(ValidateActivity.this, ListActivity.class));
        }

    }

    @Override
    public void showSummary(String message) {
        summaryPercent.setText(message);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            drawer.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}
