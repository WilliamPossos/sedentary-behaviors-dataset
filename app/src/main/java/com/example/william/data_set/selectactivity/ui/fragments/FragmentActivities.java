package com.example.william.data_set.selectactivity.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.william.data_set.R;
import com.example.william.data_set.measurescollection.ui.StartCollectionActivity;


public class FragmentActivities extends Fragment {

    private String posture;
    private String[] activities;
    private String[] statusActivity;

    public FragmentActivities(){}

    public void initFragment(String[] activities, String posture, String[] statusActivity){
        this.activities=activities;
        this.posture = posture;
        this.statusActivity = statusActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);


        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return recyclerView;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView picture;
        public TextView name;
        public TextView description;



        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_activities, parent, false));
            picture = (ImageView) itemView.findViewById(R.id.card_image);
            name = (TextView) itemView.findViewById(R.id.card_title);
            description = (TextView) itemView.findViewById(R.id.card_text);
            description.setText(posture);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(description.getText()=="Realizada"){
                        Toast.makeText(v.getContext(),"Esta actividad ya fue realizada",Toast.LENGTH_SHORT).show();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, StartCollectionActivity.class);
                        intent.putExtra(StartCollectionActivity.POSTURE, posture);
                        intent.putExtra(StartCollectionActivity.ACTIVITY, "" + name.getText());
                        context.startActivity(intent);
                    }
                }
            });
        }
    }

    /**
     * Adapter to display recycler view.
     */
    public class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of Card in RecyclerView.

        private int LENGTH = 9;

        private final Drawable[] mPlacePictures;
        private final TypedArray a;


        public ContentAdapter(Context context) {
            Resources resources = context.getResources();

            if (posture.equals("acostado")){
                LENGTH=5;
                //activities = resources.getStringArray(R.array.activitiesR);
                a = resources.obtainTypedArray(R.array.activities_pictureR);
            }else {
                //activities = resources.getStringArray(R.array.activities);
                a = resources.obtainTypedArray(R.array.activities_picture);
            }

            //mPlaceDesc = resources.getStringArray(R.array.activities);

            mPlacePictures = new Drawable[a.length()];
            for (int i = 0; i < mPlacePictures.length; i++) {
                mPlacePictures[i] = a.getDrawable(i);
            }
            for (int i = 0; i < mPlacePictures.length; i++) {
                Log.e("----",""+mPlacePictures[i]);
            }

            a.recycle();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.picture.setImageDrawable(mPlacePictures[position % mPlacePictures.length]);
            holder.name.setText(activities[position % activities.length]);
            holder.description.setText(statusActivity[position % statusActivity.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}