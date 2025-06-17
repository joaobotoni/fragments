package com.app.fragments.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.app.fragments.data.dao.XgpManejoMelhoramentoDao;
import com.app.fragments.data.dao.XgpMelhoramentoDao;
import com.app.fragments.data.dao.XgpMelhoramentoDetalhesDao;
import com.app.fragments.data.entities.XgpManejoMelhoramento;
import com.app.fragments.data.entities.XgpMelhoramento;
import com.app.fragments.data.entities.XgpMelhoramentoDetalhes;

@Database(entities = {XgpMelhoramento.class, XgpMelhoramentoDetalhes.class, XgpManejoMelhoramento.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;
    public abstract XgpMelhoramentoDao XgpMelhoramentoDao();

    public abstract XgpMelhoramentoDetalhesDao xgpMelhoramentoDetalhesDao();

    public abstract XgpManejoMelhoramentoDao xgpManejoMelhoramentoDao();

    public static AppDatabase buildDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "db").build();
                }
            }
        }
        return INSTANCE;
    }
}
