package com.example.william.data_set.selectactivity.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.william.data_set.Activity;
import com.example.william.data_set.R;
import com.example.william.data_set.User;
import com.example.william.data_set.login.ui.LoginActivity;
import com.example.william.data_set.selectactivity.SelectionPresenter;
import com.example.william.data_set.selectactivity.SelecitionPresenterImpl;
import com.example.william.data_set.selectactivity.ui.fragments.FragmentActivities;
import com.example.william.data_set.selectactivity.ui.fragments.NavigateSelectionFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListActivity extends AppCompatActivity implements ListView{

    public static final String USERNAME = "username";
    private static final String ACTIVITYTITLE = "Actividades";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer)
    DrawerLayout drawer;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private SelectionPresenter selectionPresenter;
    private RecyclerView.LayoutManager layoutManager;
    private User user;
    private String currentPosture="";
    private Adapter adapter;
    private String[] posture1;
    private String[] posture2;
    private String[] posture3;
    private String[] statusActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);
        ButterKnife.bind(this);

        preferences = getSharedPreferences(LoginActivity.PREFERENCE, MODE_PRIVATE);
        editor = preferences.edit();


        adapter = new Adapter(getSupportFragmentManager());
        selectionPresenter = new SelecitionPresenterImpl(this);
        selectionPresenter.onCreate(preferences.getString(LoginActivity.USERNAME, ""));
        //selectionPresenter.onCreate("possos@unicauca.edu.co");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(ACTIVITYTITLE);


        if (user != null) {
            NavigateSelectionFragment nf = new NavigateSelectionFragment();
            nf.initFragment(user.getLastname(),user.getGender(),user.getAge(),user.getWeight(),user.getStature());
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(R.id.nav_view, nf);
            ft.commit();
        }

        tabs.setupWithViewPager(viewpager);


        // Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    private void fragments(Adapter adapter, String[] activities, String posture, String[] statusActivity) {
        FragmentActivities fragment = new FragmentActivities();
        fragment.initFragment(activities, posture, statusActivity);
        adapter.addFragment(fragment, posture);

    }

    @Override
    public void selectActivity() {

    }

    @Override
    public void setActivities(String posture,String[] activities, String[] pictures) {
        statusActivity=pictures;

        if (!posture.equals(currentPosture)){

            if (posture1 == null) {
                posture1  = activities;
                fragments(adapter, activities, posture, pictures);
            }
            else if (posture2 == null) {
                posture2=activities;
                fragments(adapter, activities, posture, pictures);
            }
            else if (posture3 == null) {
                posture3=activities;
                fragments(adapter, activities, posture, pictures);
            }
        }
        if (posture1 != null && posture2 !=null && posture3 !=null) {
            viewpager.setAdapter(adapter);

        }

    }

    @Override
    public void setuser(User currentUser) {
        user = currentUser;
    }

    /**
     * Adapter to display recycler view.
     */
    public class Adapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            editor.putBoolean(LoginActivity.KEY_LOGIN, false);
            editor.commit();

            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}