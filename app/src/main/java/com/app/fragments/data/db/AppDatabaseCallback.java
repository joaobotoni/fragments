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

                db.execSQL("INSERT INTO xgp_melhoramento (id_melhoramento, nome) VALUES " +
                        "(1, 'PMGZ'), " +
                        "(2, 'GENEPLUS'), " +
                        "(3, 'QUALITAS'), " +
                        "(4, 'GENCIS')");
                Log.d(TAG, "Melhoramentos inseridos com sucesso");

                db.execSQL("INSERT INTO xgp_caracteristica (id_caracteristica, id_melhoramento, descricao, sigla, nota_inicial, nota_final, excessao, eh_observacao) VALUES " +
                        "(1, 1, 'Conformação', 'C', 1, 6, '9', 'n'), " +
                        "(2, 1, 'Pelagem', 'P', 1, 6, '9', 'n'), " +
                        "(3, 2, 'Musculosidade', 'M', 1, 6, '9', 'n'), " +
                        "(4, 2, 'Umbigo', 'U', 1, 6, '9', 'n'), " +
                        "(5, 2, 'Observacao', 'COM', 1, 6, '9', 's')");
                Log.d(TAG, "Características inseridas com sucesso");

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