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
                        "(1, 'PMGZ - DESMAME'), " +
                        "(2, 'GENEPLUS - SOBREANO')");

                Log.d(TAG, "Melhoramentos inseridos com sucesso");

                db.execSQL(
                        "INSERT INTO xgp_caracteristica " +
                                "(id_caracteristica, id_melhoramento, descricao, sigla, lista_opcoes, tipo_dado, nota_inicial, nota_final, eh_observacao) VALUES " +
                                "(1, 1, 'CONFORMAÇÃO', 'C', NULL, 'I', 1, 5, 'N'), " +
                                "(2, 1, 'PRECOCIDADE', 'P', NULL, 'I', 1, 5, 'N'), " +
                                "(3, 1, 'MUSCULATURA', 'M', NULL, 'I', 1, 5, 'N'), " +
                                "(4, 1, 'UMBIGO', 'U', NULL, 'I', 1, 5, 'N'), " +
                                "(5, 1, 'OBSERVAÇÃO', 'COM', NULL, 'T', NULL, NULL, 'S'), " +

                                "(6, 2, 'CONFORMAÇÃO', 'C', NULL, 'I', 1, 5, 'N'), " +
                                "(7, 2, 'PRECOCIDADE', 'P', NULL, 'I', 1, 5, 'N'), " +
                                "(8, 2, 'MUSCULATURA', 'M', NULL, 'I', 1, 5, 'N'), " +
                                "(9, 2, 'UMBIGO', 'U', NULL, 'I', 1, 5, 'N'), " +

                                "(10, 2, 'ALTURA DE GARUPA', 'ALT', NULL, 'D', 90.0, 150.0,'N'), " +
                                "(11, 2, 'PERÍMETRO ESCROTAL', 'PE', NULL, 'D', 25.0, 45.0, 'N'), " +

                                "(12, 2, 'CARACTERIZAÇÃO RACIAL', 'R', NULL, 'I', 1, 5, 'N'), " +
                                "(13, 2, 'OSSATURA', 'OSS', NULL, 'I', 1, 3, 'N'), " +
                                "(14, 2, 'PIGMENTAÇÃO', 'PIG', NULL, 'I', 1, 3, 'N'), " +

                                "(15, 2, 'PRESENÇA', 'CH', 'sim,nao', 'T', NULL, NULL, 'N'), " +
                                "(16, 2, 'TEMPERAMENTO', 'TEMP', '1,2,4,5', 'I', NULL, NULL, 'N'), " +
                                "(17, 2, 'OBSERVAÇÃO', 'COM', NULL, 'T', NULL, NULL, 'S')"
                );

                Log.d(TAG, "Características inseridas com sucesso");

                db.execSQL("INSERT INTO xgp_observacao (id_observacao, id_melhoramento, sigla, descricao) VALUES " +
                        "(1, 2, 'ODE', 'DESPIGMENTACAO'), " +
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