package com.app.fragments.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.app.fragments.R;
import com.app.fragments.ui.fragment.XgpManejoMelhoramentoFragment;

public class MainActivity extends AppCompatActivity {
    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view_xgp_manejo_melhoramento, XgpManejoMelhoramentoFragment.class, null).commit();
        }
    }
}