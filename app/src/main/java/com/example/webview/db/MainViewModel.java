package com.example.webview.db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    RGPDRepository rgpdRepository;
    public String url;

    public MainViewModel(@NonNull Application application) {
        super(application);
        rgpdRepository = new RGPDRepository(application);
    }

    public void insert(String username, String score) {
        rgpdRepository.insertarData(username, score);
    }

    public LiveData<List<Data>> obtener() {
        return rgpdRepository.obtenerData();
    }
}
