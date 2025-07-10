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
                    "(1, 'Conformação')," +
                    "(2, 'Pelagem')," +
                    "(3, 'Musculosidade')," +
                    "(4, 'Umbigo')");


            db.execSQL("INSERT INTO xgp_caracteristica (id_caracteristica, id_melhoramento, descricao, sigla, nota_inicial, nota_final, excessao) VALUES " +
                    "(1, 1, 'Avaliação da estrutura corporal do animal', 'C', 1, 6, 'Defeito estrutural grave')," +
                    "(2, 2, 'Cor, textura e padrão da pelagem', 'P', 1, 6, 'Pelagem muito rala')," +
                    "(3, 3, 'Desenvolvimento muscular visível', 'M', 1, 6, 'Hipertrofia muscular')," +
                    "(4, 4, 'Forma e aparência do umbigo', 'U', 1, 6, 'Umbigo infeccionado')");


            db.execSQL("INSERT INTO xgp_observacao (id_observacao, id_melhoramento, sigla, descricao) VALUES " +
                    "(1, 1, 'CP', 'Conformação Prejudicada')," +
                    "(2, 2, 'PA', 'Pelagem Atípica')," +
                    "(3, 3, 'MM', 'Muita Musculosidade (hipertrofia indesejada)')," +
                    "(4, 4, 'DU', 'Defeito no Umbigo')");

            db.execSQL("INSERT INTO XGP_MANEJO_MELHORAMENTO (\n" +
                    "    id_manejo_melhoramento,\n" +
                    "    id_melhoramento,\n" +
                    "    id_caracteristica,\n" +
                    "    id_observacao,\n" +
                    "    nota,\n" +
                    "    excessao,\n" +
                    "    observacao\n" +
                    ")\n" +
                    "VALUES\n" +
                    "(1001, 1, 1, 1, 6, 'Conformação Prejudicada', 'CP'),\n" +
                    "(1002, 2, 2, 2, 6, 'Pelagem Atípica', 'CP'),\n" +
                    "(1003, 3, 3, 3, 6, 'Hipertrofia muscular', 'CP'),\n" +
                    "(1004, 4, 4, 4, 6, 'Defeito no Umbigo', 'CP');");
        });
    }
}
