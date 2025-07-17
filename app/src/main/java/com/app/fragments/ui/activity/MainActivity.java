package com.app.fragments.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.app.fragments.R;
import com.app.fragments.data.db.AppDatabase;
import com.app.fragments.data.entities.Melhoramento;
import com.app.fragments.service.CaracteristicaService;
import com.app.fragments.service.ManejoMelhoramentoService;
import com.app.fragments.service.MelhoramentoService;
import com.app.fragments.service.ObservacaoService;
import com.app.fragments.ui.fragment.XgpManejoMelhoramentoFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            initializeFragment();
        }
    }

    private void initializeFragment() {
        AppDatabase database = AppDatabase.buildDatabase(getApplicationContext());

        MelhoramentoService melhoramentoService = new MelhoramentoService(database.melhoramentoDao());
        CaracteristicaService caracteristicaService = new CaracteristicaService(database.caracteristicaDao(), melhoramentoService);
        ObservacaoService observacaoService = new ObservacaoService(database.observacaoDao(), melhoramentoService);

        ManejoMelhoramentoService manejoMelhoramentoService = new ManejoMelhoramentoService(
                database.manejoMelhoramentoDao(),
                melhoramentoService,
                caracteristicaService,
                observacaoService
        );

        XgpManejoMelhoramentoFragment fragment = new XgpManejoMelhoramentoFragment(manejoMelhoramentoService);
        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.fragment_container, fragment).commit();
    }
}
