package com.app.fragments.data.repository;

import com.app.fragments.data.dao.XgpMelhoramentoDetalhesDao;
import com.app.fragments.data.db.AppDatabase;
import com.app.fragments.data.entities.XgpMelhoramentoDetalhes;

public class XgpMelhoramentoDetalhesRepository {
    private XgpMelhoramentoDetalhesDao dao;

    public XgpMelhoramentoDetalhesRepository(AppDatabase database) {
        dao = database.xgpMelhoramentoDetalhesDao();
    }

    public void insert(XgpMelhoramentoDetalhes xgpMelhoramentoDetalhes) {
        new Thread(() -> dao.insert(xgpMelhoramentoDetalhes)).start();
    }

    public void delete(long uuid) {
        new Thread(() -> dao.delete(uuid)).start();
    }
}
