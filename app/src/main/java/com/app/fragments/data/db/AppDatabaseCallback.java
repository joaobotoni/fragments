package com.app.fragments.data.db;


import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

public class AppDatabaseCallback extends RoomDatabase.Callback {

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
        super.onCreate(db);

        Executors.newSingleThreadExecutor().execute(() -> {
            db.execSQL("INSERT INTO xgp_melhoramento (id_melhoramento, nome) VALUES " +
                    "(1, 'PMGZ')," +
                    "(2, 'GENEPLUS')," +
                    "(3, 'QUALITAS')," +
                    "(4, 'GENCIS')");


            db.execSQL("INSERT INTO xgp_caracteristica (id_caracteristica, id_melhoramento, descricao, sigla, nota_inicial, nota_final, excessao) VALUES " +
                    "(1, 1, 'Conformação', 'C', 1, 6, '')," +
                    "(2, 2, 'Pelagem', 'P', 1, 6, '')," +
                    "(3, 3, 'Musculosidade', 'M', 1, 6, '')," +
                    "(4, 4, 'Umbigo', 'U', 1, 6, '')");

            db.execSQL("INSERT INTO xgp_observacao (id_observacao, id_melhoramento, sigla, descricao) VALUES " +
                    "(1, 1, 'C', 'Conformação')," +
                    "(2, 2, 'P', 'Pelagem')," +
                    "(3, 3, 'M', 'Musculosidade')," +
                    "(4, 4, 'U', 'Umbigo')");
        });
    }
}
