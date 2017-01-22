package com.example.william.data_set.cleandata.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.william.data_set.R;
import com.example.william.data_set.cleandata.CleanDataPresenter;
import com.example.william.data_set.cleandata.CleanDataPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CleanDataActivity extends AppCompatActivity implements CleanDataView {
    @BindView(R.id.activityId)
    EditText activityId;
    @BindView(R.id.specificDelete)
    EditText specificDelete;
    private CleanDataPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clean_data);
        ButterKnife.bind(this);
        specificDelete.setText("");

        presenter = new CleanDataPresenterImpl(this);
        presenter.onCreate();
    }

    @Override
    public void adjustSuccess() {

    }

    @OnClick({R.id.btnPreview, R.id.btnDelete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPreview:
                presenter.previewSamples(Integer.parseInt(activityId.getText().toString()));
                break;
            case R.id.btnDelete:
                if(specificDelete.getText().equals("")){
                    presenter.adjustSamples(Integer.parseInt(activityId.getText().toString()));
                }
                else
                {
                    presenter.adjustSpecificSamples(Integer.parseInt(activityId.getText().toString()), Integer.parseInt(specificDelete.getText().toString()));
                }

                break;
        }
    }
}
