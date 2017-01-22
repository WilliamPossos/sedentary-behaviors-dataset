package com.example.william.data_set.selectactivity.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.william.data_set.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by william on 16/10/16.
 */

public class NavigateSelectionFragment extends Fragment{
    String nombree, generoo;
    int edadd, pesoo, estaturaa;

    @BindView(R.id.imggender)
    ImageView imggender;
    @BindView(R.id.nombre)
    TextView nombre;
    @BindView(R.id.edad)
    TextView edad;
    @BindView(R.id.genero)
    TextView genero;
    @BindView(R.id.peso)
    TextView peso;
    @BindView(R.id.estatura)
    TextView estatura;

    public NavigateSelectionFragment() {
        // Required empty public constructor
    }

    public void initFragment(String nombre, String genero, int edad, int peso, int estatura) {
        this.nombree = nombre;
        this.generoo = genero;
        this.edadd = edad;
        this.pesoo = peso;
        this.estaturaa = estatura;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navigation, container, false);
        ButterKnife.bind(this, view);

        nombre.setText(nombree);
        edad.setText("" + edadd + " años");
        peso.setText("" + pesoo + " Kg");
        genero.setText(generoo);
        estatura.setText(estaturaa+" cm");
        if (generoo.equals("Masculino")) {
            imggender.setImageResource(R.drawable.iconohombre);
        } else {
            imggender.setImageResource(R.drawable.iconomujer);
        }

        return view;
    }
}
