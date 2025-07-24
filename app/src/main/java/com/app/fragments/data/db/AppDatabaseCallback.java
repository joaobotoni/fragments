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
                        "(id_caracteristica, id_melhoramento, descricao, sigla, nota_inicial, nota_final, excessao, eh_observacao) VALUES " +
                        "(1, 1, 'CONFORMAÇÃO', 'C', 1, 5, 'excessao', 'n'), " +
                        "(2, 1, 'PRECOCIDADE', 'P', 1, 5, 'excessao', 'n'), " +
                        "(3, 1, 'MUSCULATURA', 'M', 1, 5, 'excessao', 'n'), " +
                        "(4, 1, 'UMBIGO', 'U', 1, 5, 'excessao', 'n'), " +
                        "(5, 1, 'OBSERVAÇÃO', 'COM', 1, 5, 'excessao', 's'), " +
                        "(6, 2, 'CONFORMAÇÃO', 'C', 1, 5, 'excessao', 'n'), " +
                        "(7, 2, 'PRECOCIDADE', 'P', 1, 5, 'excessao', 'n'), " +
                        "(8, 2, 'MUSCULATURA', 'M', 1, 5, 'excessao', 'n'), " +
                        "(9, 2, 'UMBIGO', 'U', 1, 5, '9', 'excessao'), " +
                        "(10, 2, 'ALTURA DE GARUPA', 'ALT', 1, 6, 'excessao', 'n'), " +
                        "(11, 2, 'PERÍMETRO ESCROTAL', 'PE', 1, 6, 'excessao', 'n'), " +
                        "(12, 2, 'CARACTERIZAÇÃO RACIAL', 'R', 1, 5, 'excessao', 'n'), " +
                        "(13, 2, 'OSSATURA', 'OSS', 1, 3, 'excessao', 'n'), " +
                        "(14, 2, 'PIGMENTAÇÃO', 'PIG', 1, 3, 'excessao', 'n'), " +
                        "(15, 2, 'PRESENÇA', 'CH', 1, 6, 'excessao', 'n'), " +
                        "(16, 2, 'TEMPERAMENTO', 'TEMP', 1, 5, 'excessao', 'n'), " +
                        "(17, 2, 'OBSERVAÇÃO', 'COM', 1, 6, 'excessao', 's')");

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
