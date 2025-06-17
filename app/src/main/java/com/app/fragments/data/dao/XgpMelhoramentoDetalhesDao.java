package com.app.fragments.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.app.fragments.data.entities.XgpMelhoramentoDetalhes;

@Dao
public interface XgpMelhoramentoDetalhesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(XgpMelhoramentoDetalhes xgpMelhoramentoDetalhes);

    @Delete
    void delete(long uuid);
}
