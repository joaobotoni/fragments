package com.app.fragments.data.repository;

import com.app.fragments.data.dao.XgpMelhoramentoDao;
import com.app.fragments.data.db.AppDatabase;
import com.app.fragments.data.entities.XgpMelhoramento;

public class XgpMelhoramentoRepository {
    private XgpMelhoramentoDao dao;

    public XgpMelhoramentoRepository(AppDatabase database) {
        dao = database.XgpMelhoramentoDao();
    }
    public void insert(XgpMelhoramento xgpMelhoramento) {
        new Thread(() -> dao.insert(xgpMelhoramento)).start();
    }

    public void delete(long uuid) {
        new Thread(() -> dao.delete(uuid)).start();
    }
}
