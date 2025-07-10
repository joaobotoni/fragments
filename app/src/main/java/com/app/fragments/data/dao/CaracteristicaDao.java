package com.app.fragments.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.app.fragments.data.entities.Caracteristica;


import java.util.List;

@Dao
public interface CaracteristicaDao {
    @Query("SELECT * FROM xgp_caracteristica order by id_melhoramento, id_caracteristica")
    List<Caracteristica> getAll();

    @Query("SELECT * FROM xgp_caracteristica WHERE id_caracteristica = :idCaracteristica")
    Caracteristica findById(long idCaracteristica);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Caracteristica caracteristica);

    @Delete
    int delete(Caracteristica caracteristica);

    @Update
    int update(Caracteristica caracteristica);

    @Query("DELETE FROM xgp_caracteristica")
    int deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Caracteristica> caracteristica);
}
