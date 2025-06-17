package com.app.fragments.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.app.fragments.data.entities.XgpManejoMelhoramento;
import com.app.fragments.data.entities.XgpMelhoramento;

import java.util.List;

@Dao
public interface XgpMelhoramentoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(XgpMelhoramento xgpMelhoramento);

    @Query("DELETE FROM XGP_MELHORAMENTO WHERE Id_Melhoramento = :uuid")
    void delete(long uuid);

    @Query("SELECT * FROM XGP_MELHORAMENTO")
    List<XgpMelhoramento> getAll();

}
