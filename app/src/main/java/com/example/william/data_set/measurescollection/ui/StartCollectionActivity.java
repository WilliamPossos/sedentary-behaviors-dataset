package com.example.william.data_set.measurescollection.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.william.data_set.R;
import com.example.william.data_set.login.ui.LoginActivity;
import com.example.william.data_set.measurescollection.MeasuresCollPresenteImpl;
import com.example.william.data_set.measurescollection.MeasuresCollPresenter;
import com.example.william.data_set.selectactivity.ui.ListActivity;
import com.example.william.data_set.validatedata.ui.ValidateActivity;
import com.github.lzyzsd.circleprogress.ArcProgress;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartCollectionActivity extends Activity implements MeasuresCollView {

    private Timer timer;
    private MeasuresCollPresenter collectionPresenter;
    public static final String POSTURE = "posture";
    public static String ACTIVITY = "activity";
    private int RESTART;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @BindView(R.id.name_activity)
    TextView nameActivity;
    @BindView(R.id.arc_progress)
    ArcProgress arcProgress;
    @BindView(R.id.content_time)
    LinearLayout contentTime;
    @BindView(R.id.fabStart)
    FloatingActionButton fabStart;
    @BindView(R.id.fabRestart)
    FloatingActionButton fabRestart;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        preferences = getSharedPreferences(LoginActivity.PREFERENCE, MODE_PRIVATE);
        editor = preferences.edit();


        final WeakReference<Activity> reference = new WeakReference<Activity>(this);
        collectionPresenter = new MeasuresCollPresenteImpl(this);
        collectionPresenter.onCreate(reference);

        if (getIntent().getStringExtra(ACTIVITY) == null) {
            startActivity(new Intent(this, ListActivity.class));
        }else{
            String activity =getIntent().getStringExtra(ACTIVITY);
            String posture=getIntent().getStringExtra(POSTURE);
            nameActivity.setText(posture.substring(0,1).toUpperCase()+posture.substring(1)
                    + " " + activity.toLowerCase());
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        collectionPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        collectionPresenter.onDestroy();
    }

    @Override
    @OnClick(R.id.fabStart)
    public void startCapture() {
        updateProgressBar(1,1900);
        collectionPresenter.initDataStorage(
                getIntent().getStringExtra(POSTURE),
                getIntent().getStringExtra(ACTIVITY),
                preferences.getString(LoginActivity.USERNAME, ""));
    }

    @Override
    @OnClick(R.id.fabRestart)
    public void restartCapture() {
        arcProgress.setProgress(0);
        timer.cancel();
        collectionPresenter.restartDataStorage();
    }

    @Override
    public void finishCapture() {
        timer.cancel();
        showRestartDialog(R.string.complete_capture_title,R.string.complete_capture_message);
    }
    public void finishh(){
        collectionPresenter.finishCollection();
        Intent view = new Intent(this, ValidateActivity.class);
        view.putExtra(ValidateActivity.ACTIVITY,getIntent().getStringExtra(ACTIVITY));
        view.putExtra(ValidateActivity.POSTURE,getIntent().getStringExtra(POSTURE));
        startActivity(view);
    }

    @Override
    public void showStartButton() {
        fabStart.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideStartButton() {
        fabStart.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showRestartButton() {
        fabRestart.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideRestartButton() {
        fabRestart.setVisibility(View.INVISIBLE);

    }


    @Override
    public void showRestartDialog(final int title, int message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(StartCollectionActivity.this);
        builder.setTitle(getString(title));
        builder.setMessage(getString(message));

        String positiveText = getString(R.string.accept);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        if(title==R.string.complete_capture_title){
                        finishh();
                        }
                    }
                });


        AlertDialog dialog = builder.create();
        // display dialog
        dialog.setCancelable(false);
        dialog.show();
    }
    @Override
    public void updateProgressBar(final int i, int j) {
        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (arcProgress.getProgress() < 100) {
                            arcProgress.setProgress(arcProgress.getProgress() + i);
                        } else {
                            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            // Vibrate for 500 milliseconds
                            v.vibrate(500);
                            finishCapture();
                        }
                    }
                });
            }
        }, 0, j);
    }

}