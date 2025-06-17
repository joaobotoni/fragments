package com.app.fragments.data.repository;

import android.content.Context;

import com.app.fragments.data.dao.XgpMelhoramentoDetalhesDao;
import com.app.fragments.data.db.AppDatabase;
import com.app.fragments.data.entities.XgpMelhoramentoDetalhes;

public class XgpMelhoramentoDetalhesRepository {
    private XgpMelhoramentoDetalhesDao dao;

    public XgpMelhoramentoDetalhesRepository(Context context) {
        AppDatabase database = AppDatabase.buildDatabase(context);
        dao = database.xgpMelhoramentoDetalhesDao();
    }

    public void insert(XgpMelhoramentoDetalhes xgpMelhoramentoDetalhes) {
        new Thread(() -> dao.insert(xgpMelhoramentoDetalhes)).start();
    }

    public void delete(long uuid) {
        new Thread(() -> dao.delete(uuid)).start();
    }
}
