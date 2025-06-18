package com.app.fragments.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.app.fragments.R;
import com.app.fragments.data.db.AppDatabase;
import com.app.fragments.ui.fragment.XgpConsultaQtdPesoFragment;
import com.app.fragments.ui.fragment.XgpManejoMelhoramentoFragment;

public class MainActivity extends AppCompatActivity {
    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            AppDatabase database = AppDatabase.buildDatabase(getApplicationContext());
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container, XgpConsultaQtdPesoFragment.class, null).commit();
        }
    }
}