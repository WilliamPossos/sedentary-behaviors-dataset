package com.example.william.data_set.completecollection;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.william.data_set.R;
import com.example.william.data_set.login.ui.RootActivity;
import com.example.william.data_set.selectactivity.ui.ListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class EndedActivity extends AppCompatActivity {

    @BindView(R.id.tile_picture)
    CircleImageView tilePicture;
    @BindView(R.id.thankyou)
    ImageView thankyou;
    @BindView(R.id.finish_title)
    TextView finishTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ended);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.tile_picture)
    public void onClick(View v) {
        Context context = v.getContext();
        Intent intent = new Intent(context, RootActivity.class);
        context.startActivity(intent);
    }
}
