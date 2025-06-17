package com.app.fragments.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.app.fragments.data.entities.XgpMelhoramento;
import com.app.fragments.data.entities.XgpMelhoramentoDetalhes;

import java.util.List;

@Dao
public interface XgpMelhoramentoDetalhesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(XgpMelhoramentoDetalhes xgpMelhoramentoDetalhes);

    @Query("DELETE FROM XGP_MELHORAMENTO_DET WHERE Id_Melhoramento = :uuid")
    void delete(long uuid);

    @Query("SELECT * FROM XGP_MELHORAMENTO_DET")
    List<XgpMelhoramentoDetalhes> getAll();
}
