package com.app.fragments.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.app.fragments.data.entities.Melhoramento;


import java.util.List;

@Dao
public interface MelhoramentoDao {

    @Query("SELECT * FROM xgp_melhoramento order by nome")
    List<Melhoramento> getAll();

    @Query("SELECT * " +
            "FROM xgp_melhoramento " +
            "WHERE id_melhoramento = :idMelhoramento")
    Melhoramento findById(long idMelhoramento);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Melhoramento Melhoramento);

    @Delete
    int delete(Melhoramento idMelhoramento);

    @Update
    int update(Melhoramento Melhoramento);

    @Query("DELETE FROM xgp_melhoramento")
    int deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Melhoramento> Melhoramento);
}