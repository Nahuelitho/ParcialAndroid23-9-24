package com.softnahu.parcialandroid23_09_24.ui.cargar;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.softnahu.parcialandroid23_09_24.MainActivity;
import com.softnahu.parcialandroid23_09_24.model.Auto;
import com.softnahu.parcialandroid23_09_24.ui.listar.ListarViewModel;

public class CargarViewModel extends AndroidViewModel {
    MutableLiveData<String> mErrorMsj;

    public CargarViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getmErrorMsj() {
        if (mErrorMsj == null) {
            mErrorMsj = new MutableLiveData<>();
        }
        return mErrorMsj;
    }

    public void cargarAuto(String patente, String marca, String modelo, double precio) {
        if (patente.isEmpty() || marca.isEmpty() || modelo.isEmpty() || precio <= 0) {
            getmErrorMsj().setValue("Todos los campos deben estar completos y el precio debe ser mayor a 0");
            return;
        }

        if (verificarPatente(patente)) {
            getmErrorMsj().setValue("La Patente del auto ya existe en la lista.");
            return;
        }
        Auto autoCeroKM = new Auto(patente, marca, modelo, precio);
        MainActivity.autos.add(autoCeroKM);
        getmErrorMsj().setValue("Auto guardado con éxito");

        ListarViewModel listarViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(ListarViewModel.class);
        listarViewModel.imprimirLista();
    }

    public boolean verificarPatente(String patente) {
        for (Auto auto : MainActivity.autos) {
            if (auto.getPatente().equals(patente)) {
                return true;
            }
        }
        return false;
    }
}