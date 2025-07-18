package com.app.fragments.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.app.fragments.data.dao.CaracteristicaDao;
import com.app.fragments.data.dao.MelhoramentoDao;
import com.app.fragments.data.dao.MelhoramentoManejoDao;
import com.app.fragments.data.dao.ObservacaoDao;
import com.app.fragments.data.entities.Caracteristica;
import com.app.fragments.data.entities.ManejoMelhoramento;
import com.app.fragments.data.entities.Melhoramento;
import com.app.fragments.data.entities.Observacao;

@Database(
        entities = {
                Melhoramento.class,
                Caracteristica.class,
                Observacao.class,
                ManejoMelhoramento.class
        },
        version = 1,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;
    public abstract MelhoramentoDao melhoramentoDao();
    public abstract MelhoramentoManejoDao manejoMelhoramentoDao();
    public abstract CaracteristicaDao caracteristicaDao();
    public abstract ObservacaoDao observacaoDao();

    public static AppDatabase buildDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "app_database")
                            .addCallback(new AppDatabaseCallback())
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}