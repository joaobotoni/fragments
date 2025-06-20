package com.app.fragments.data.repository;

import com.app.fragments.data.dao.XgpManejoMelhoramentoDao;
import com.app.fragments.data.db.AppDatabase;
import com.app.fragments.data.entities.XgpManejoMelhoramento;




public class XgpManejoMelhoramentoRepository {
    private XgpManejoMelhoramentoDao dao;
    public XgpManejoMelhoramentoRepository(AppDatabase database) {
        dao = database.xgpManejoMelhoramentoDao();
    }

    public void insert(XgpManejoMelhoramento xgpManejoMelhoramento) {
        new Thread(() -> dao.insert(xgpManejoMelhoramento)).start();
    }

    public void delete(long uuid) {
        new Thread(() -> dao.delete(uuid)).start();
    }
}
