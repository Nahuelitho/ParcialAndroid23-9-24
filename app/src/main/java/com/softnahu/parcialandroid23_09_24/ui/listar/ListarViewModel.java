package com.softnahu.parcialandroid23_09_24.ui.listar;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.softnahu.parcialandroid23_09_24.MainActivity;
import com.softnahu.parcialandroid23_09_24.model.Auto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListarViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<Auto>> listaAutos ;

    public ListarViewModel(@NonNull Application application) {
        super(application);
        listaAutos = new MutableLiveData<>(MainActivity.autos);
    }

    public MutableLiveData<ArrayList<Auto>> getListaAutos() {
        return listaAutos;
    }

    public void imprimirLista() {
        if (MainActivity.autos != null) {
            Collections.sort(MainActivity.autos, new Comparator<Auto>() {
                @Override
                public int compare(Auto a1, Auto a2) {
                    return Double.compare(a2.getPrecio(), a1.getPrecio());
                }
            });
            listaAutos.setValue(MainActivity.autos);
        }
    }
}