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
            db.execSQL("INSERT INTO xgp_melhoramento (id_melhoramento, nome) VALUES (1, 'Conformação')");
            db.execSQL("INSERT INTO xgp_melhoramento (id_melhoramento, nome) VALUES (2, 'Pelagem')");
            db.execSQL("INSERT INTO xgp_melhoramento (id_melhoramento, nome) VALUES (3, 'Musculosidade')");
            db.execSQL("INSERT INTO xgp_melhoramento (id_melhoramento, nome) VALUES (4, 'Umbigo')");

            db.execSQL("INSERT INTO xgp_caracteristica (id_caracteristica, id_melhoramento, sigla, nota_inicial, nota_final, excessao) VALUES (1, 1, 'C', 1, 6, NULL)");
            db.execSQL("INSERT INTO xgp_caracteristica (id_caracteristica, id_melhoramento, sigla, nota_inicial, nota_final, excessao) VALUES (2, 2, 'P', 1, 6, NULL)");
            db.execSQL("INSERT INTO xgp_caracteristica (id_caracteristica, id_melhoramento, sigla, nota_inicial, nota_final, excessao) VALUES (3, 3, 'M', 1, 6, NULL)");
            db.execSQL("INSERT INTO xgp_caracteristica (id_caracteristica, id_melhoramento, sigla, nota_inicial, nota_final, excessao) VALUES (4, 4, 'U', 1, 6, NULL)");

            db.execSQL("INSERT INTO xgp_observacao (id_observacao, id_melhoramento, sigla, descricao) VALUES (1, 1, 'CP', 'Conformação Prejudicada')");
            db.execSQL("INSERT INTO xgp_observacao (id_observacao, id_melhoramento, sigla, descricao) VALUES (2, 2, 'PA', 'Pelagem Atípica')");
            db.execSQL("INSERT INTO xgp_observacao (id_observacao, id_melhoramento, sigla, descricao) VALUES (3, 3, 'MM', 'Muita Musculosidade (hipertrofia indesejada)')");
            db.execSQL("INSERT INTO xgp_observacao (id_observacao, id_melhoramento, sigla, descricao) VALUES (4, 4, 'DU', 'Defeito no Umbigo')");
        });
    }

}
