package com.app.fragments.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.app.fragments.data.entities.XgpManejoMelhoramento;

@Dao
public interface XgpManejoMelhoramentoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(XgpManejoMelhoramento xgpManejoMelhoramento);

    @Delete
    void delete(long uuid);
}
