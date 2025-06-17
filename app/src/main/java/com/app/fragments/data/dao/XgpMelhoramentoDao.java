package com.app.fragments.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.app.fragments.data.entities.XgpMelhoramento;

@Dao
public interface XgpMelhoramentoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(XgpMelhoramento xgpMelhoramento);

    @Delete
    void delete(long uuid);

}
