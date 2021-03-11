package com.example.webview.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RGPDRepository {
    Executor executor = Executors.newSingleThreadExecutor();

    RGPDDatabase.DataDao dao;

    RGPDRepository(Application application) {
        dao = RGPDDatabase.getInstance(application).getDataDao();
    }

    LiveData<List<Data>> obtenerData() {
        return dao.obtener();
    }

    void insertarData(String apodo, String url) {
        executor.execute(() -> dao.insert(new Data(apodo, url)));
    }
}
