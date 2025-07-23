package com.app.fragments.data.db;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

public class AppDatabaseCallback extends RoomDatabase.Callback {
    private static final String TAG = "AppDatabaseCallback";

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
        super.onCreate(db);

        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                Log.d(TAG, "Iniciando inserção de dados iniciais...");

                // Inserindo dados na tabela xgp_melhoramento
                db.execSQL("INSERT INTO xgp_melhoramento (id_melhoramento, nome) VALUES " +
                        "(1, 'PMGZ'), " +
                        "(2, 'GENEPLUS'), " +
                        "(3, 'QUALITAS'), " +
                        "(4, 'GENCIS')");
                Log.d(TAG, "Melhoramentos inseridos com sucesso");

                // Inserindo dados na tabela xgp_caracteristica
                db.execSQL("INSERT INTO xgp_caracteristica (id_caracteristica, id_melhoramento, descricao, sigla, nota_inicial, nota_final, excessao, eh_observacao) VALUES " +
                        "(1, 1, 'Conformação', 'C', 1, 6, '9', 'n'), " +
                        "(2, 1, 'Pelagem', 'P', 1, 6, '9', 'n'), " +
                        "(3, 1, 'Musculosidade', 'M', 1, 6, '9', 'n'), " +
                        "(4, 1, 'Umbigo', 'U', 1, 6, '9', 'n'), " +
                        "(5, 1, 'Observacao', 'COM', 1, 6, '9', 's'), " +
                        "(6, 2, 'Conformação', 'C', 1, 6, '9', 'n'), " +
                        "(7, 2, 'Pelagem', 'P', 1, 6, '9', 'n'), " +
                        "(8, 2, 'Musculosidade', 'M', 1, 6, '9', 'n'), " +
                        "(9, 2, 'Umbigo', 'U', 1, 6, '9', 'n'), " +
                        "(10, 2, 'Observacao', 'COM', 1, 6, '9', 's'), " +
                        "(11, 3, 'Conformação', 'C', 1, 6, '9', 'n'), " +
                        "(12, 3, 'Pelagem', 'P', 1, 6, '9', 'n'), " +
                        "(13, 3, 'Musculosidade', 'M', 1, 6, '9', 'n'), " +
                        "(14, 3, 'Umbigo', 'U', 1, 6, '9', 'n'), " +
                        "(15, 3, 'Observacao', 'COM', 1, 6, '9', 's'), " +
                        "(16, 4, 'Conformação', 'C', 1, 6, '9', 'n'), " +
                        "(17, 4, 'Pelagem', 'P', 1, 6, '9', 'n'), " +
                        "(18, 4, 'Musculosidade', 'M', 1, 6, '9', 'n'), " +
                        "(19, 4, 'Umbigo', 'U', 1, 6, '9', 'n'), " +
                        "(20, 4, 'Observacao', 'COM', 1, 6, '9', 's')");
                Log.d(TAG, "Características inseridas com sucesso");

                // Inserindo dados na tabela xgp_observacao
                db.execSQL("INSERT INTO xgp_observacao (id_observacao, id_melhoramento, sigla, descricao) VALUES " +
                        "(1, 1, 'C', 'Conformação'), " +
                        "(2, 2, 'P', 'Pelagem'), " +
                        "(3, 3, 'M', 'Musculosidade'), " +
                        "(4, 4, 'U', 'Umbigo')");
                Log.d(TAG, "Observações inseridas com sucesso");

                Log.d(TAG, "Todos os dados iniciais foram inseridos com sucesso!");

            } catch (Exception e) {
                Log.e(TAG, "Erro ao inserir dados iniciais", e);
            }
        });
    }

    @Override
    public void onOpen(@NonNull SupportSQLiteDatabase db) {
        super.onOpen(db);
        Log.d(TAG, "Banco de dados aberto");
    }
}
