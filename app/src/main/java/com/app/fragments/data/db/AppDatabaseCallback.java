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
            db.execSQL("INSERT INTO xgp_melhoramento (id_melhoramento, nome) VALUES\n" +
                    "  (1, 'PMGZ'),\n" +
                    "  (2, 'GENEPLUS'),\n" +
                    "  (3, 'QUALITAS'),\n" +
                    "  (4, 'GENCIS');");

            db.execSQL("INSERT INTO xgp_caracteristica (id_caracteristica, id_melhoramento, descricao, sigla, nota_inicial, nota_final, excessao, eh_observacao) VALUES\n" +
                    "  (1, 1, 'Conformação', 'C', 1, 6, '9', 'n'),\n" +
                    "  (2, 1, 'Pelagem', 'P', 1, 6, '9', 'n'),\n" +
                    "  (3, 2, 'Musculosidade', 'M', 1, 6, '9','n'),\n" +
                    "  (4, 2, 'Umbigo', 'U', 1, 6, '9','n'),\n" +
                    "  (5, 2, 'Observacao', 'COM', 1, 6, '9', 's');");

            db.execSQL("INSERT INTO xgp_observacao (id_observacao, id_melhoramento, sigla, descricao) VALUES\n" +
                    "  (1, 1, 'C', 'Conformação'),\n" +
                    "  (2, 2, 'P', 'Pelagem'),\n" +
                    "  (3, 3, 'M', 'Musculosidade'),\n" +
                    "  (4, 4, 'U', 'Umbigo');");
        });
    }
}
