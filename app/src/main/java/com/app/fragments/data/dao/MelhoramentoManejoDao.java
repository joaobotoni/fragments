package com.app.fragments.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.app.fragments.data.entities.ManejoMelhoramento;

import java.util.List;

@Dao
public interface MelhoramentoManejoDao  {

    @Query("SELECT * FROM xgp_manejo_melhoramento order by id_manejo_melhoramento")
    List<ManejoMelhoramento> getAll();

    @Query("SELECT * FROM xgp_manejo_melhoramento WHERE id_manejo_melhoramento = :idManejoMelhoramento")
    ManejoMelhoramento findById(long idManejoMelhoramento);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(ManejoMelhoramento manejoMelhoramento);

    @Delete
    int delete(ManejoMelhoramento manejoMelhoramento);

    @Update
    int update(ManejoMelhoramento manejoMelhoramento);

    @Query("DELETE FROM xgp_manejo_melhoramento")
    int deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ManejoMelhoramento> manejoMelhoramentos);
}
