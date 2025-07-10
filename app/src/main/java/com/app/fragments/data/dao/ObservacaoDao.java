package com.app.fragments.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.app.fragments.data.entities.Observacao;


import java.util.List;

@Dao
public interface ObservacaoDao {
    @Query("SELECT * FROM xgp_observacao order by id_melhoramento, id_observacao")
    List<Observacao> getAll();

    @Query("SELECT * FROM XGP_OBSERVACAO WHERE id_observacao = :idObservacao")
    Observacao findById(long idObservacao);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Observacao observacao);

    @Delete
    int delete(Observacao observacao);

    @Update
    int update(Observacao observacao);

    @Query("DELETE FROM xgp_observacao")
    int deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Observacao> observacao);
}
