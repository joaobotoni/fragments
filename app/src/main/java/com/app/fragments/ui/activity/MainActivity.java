package com.app.fragments.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.app.fragments.R;
import com.app.fragments.data.db.AppDatabase;
import com.app.fragments.data.entities.Melhoramento; // Importação necessária se for usar Melhoramento
import com.app.fragments.service.CaracteristicaService;
import com.app.fragments.service.ManejoMelhoramentoService;
import com.app.fragments.service.MelhoramentoService;
import com.app.fragments.service.ObservacaoService;
import com.app.fragments.ui.fragment.XgpManejoMelhoramentoFragment;

// Removidas importações não utilizadas para manter o código limpo
// import java.util.ArrayList;
// import java.util.List;
// import java.util.concurrent.CompletableFuture;
// import java.util.stream.Collectors;

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
        // 1. Inicializa o banco de dados
        AppDatabase database = AppDatabase.buildDatabase(getApplicationContext());

        // 2. Inicializa os serviços dependentes
        MelhoramentoService melhoramentoService = new MelhoramentoService(database.melhoramentoDao());
        CaracteristicaService caracteristicaService = new CaracteristicaService(database.caracteristicaDao(), melhoramentoService);
        ObservacaoService observacaoService = new ObservacaoService(database.observacaoDao(), melhoramentoService);

        // 3. Inicializa o serviço principal (ManejoMelhoramentoService)
        ManejoMelhoramentoService manejoMelhoramentoService = new ManejoMelhoramentoService(
                database.manejoMelhoramentoDao(),
                melhoramentoService,
                caracteristicaService,
                observacaoService
        );

        // 4. Define o ID do melhoramento que você deseja carregar
        //    Este é um exemplo. Em um cenário real, este ID viria de uma Intent,
        //    de uma seleção do usuário, ou de outra lógica de negócio.
        Long idDoMelhoramentoParaCarregar = 2L; // Exemplo: Carrega o melhoramento com ID 1

        // 5. Cria uma nova instância do fragmento usando o método newInstance
        XgpManejoMelhoramentoFragment fragment = XgpManejoMelhoramentoFragment.newInstance(
                manejoMelhoramentoService,
                idDoMelhoramentoParaCarregar
        );

        // 6. Adiciona o fragmento ao container
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
