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
                        "(1, 'PMGZ - DESMAME'), " +
                        "(2, 'GENEPLUS - SOBREANO')");

                Log.d(TAG, "Melhoramentos inseridos com sucesso");

                // Inserindo dados na tabela xgp_caracteristica
                db.execSQL("INSERT INTO xgp_caracteristica " +
                        "(id_caracteristica, id_melhoramento, descricao, sigla, tipo_dado, nota_inicial, nota_final, excessao) VALUES " +
                        "(1, 1, 'CONFORMAÇÃO', 'C', 'I', 1, 5, 'excessao'), " +
                        "(2, 1, 'PRECOCIDADE', 'P', 'I', 1, 5, 'excessao'), " +
                        "(3, 1, 'MUSCULATURA', 'M', 'I',1, 5, 'excessao'), " +
                        "(4, 1, 'UMBIGO', 'U', 'I', 1, 5, 'excessao'), " +
                        "(5, 1, 'OBSERVAÇÃO', 'COM', 'T',1, 5, 'excessao'), " +
                        "(6, 2, 'CONFORMAÇÃO', 'C','I', 1, 5, 'excessao'), " +
                        "(7, 2, 'PRECOCIDADE', 'P','I', 1, 5, 'excessao'), " +
                        "(8, 2, 'MUSCULATURA', 'M','I', 1, 5, 'excessao'), " +
                        "(9, 2, 'UMBIGO', 'U','I', 1, 5, 'excessao'), " +
                        "(10, 2, 'ALTURA DE GARUPA', 'ALT','D', 1, 6, 'excessao'), " +
                        "(11, 2, 'PERÍMETRO ESCROTAL', 'PE','D', 1, 6, 'excessao'), " +
                        "(12, 2, 'CARACTERIZAÇÃO RACIAL', 'R','I', 1, 5, 'excessao'), " +
                        "(13, 2, 'OSSATURA', 'OSS','I' ,1, 3, 'excessao'), " +
                        "(14, 2, 'PIGMENTAÇÃO', 'PIG','I', 1, 3, 'excessao'), " +
                        "(15, 2, 'PRESENÇA', 'CH', 'T',1, 6, 'excessao'), " +
                        "(16, 2, 'TEMPERAMENTO', 'TEMP','I', 1, 5, 'excessao'), " +
                        "(17, 2, 'OBSERVAÇÃO', 'COM','T' ,1, 6, 'excessao')");

                Log.d(TAG, "Características inseridas com sucesso");

                // Inserindo dados na tabela xgp_observacao
                db.execSQL("INSERT INTO xgp_observacao (id_observacao, id_melhoramento, sigla, descricao) VALUES " +
                        "(1, 1, 'ODE', 'DESPIGMENTACAO'), " +
                        "(2, 2, 'OLO', 'LOMBO'), " +
                        "(3, 2, 'OMA', 'MANDIBULA BOCA'), " +
                        "(4, 2, 'ORA', 'RACIAL'), " +
                        "(5, 2, 'OCU', 'CUPIM'), " +
                        "(6, 2, 'OCH', 'CHANFRO DESVIOS AFUNDAMENTOS'), " +
                        "(7, 2, 'OAP', 'APRUMOS'), " +
                        "(8, 2, 'OSF', 'OSSATURA FRACA'), " +
                        "(9, 2, 'OSS', 'OSSO SACRO SALIENTE'), " +
                        "(10, 2, 'OPR', 'PREPUCIO OU UMBIGO'), " +
                        "(11, 2, 'OTE', 'TEMPERAMENTO')");

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