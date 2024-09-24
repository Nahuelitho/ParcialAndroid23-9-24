package com.softnahu.parcialandroid23_09_24.ui.cargar;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.softnahu.parcialandroid23_09_24.R;
import com.softnahu.parcialandroid23_09_24.databinding.FragmentCargarBinding;

public class CargarFragment extends Fragment {

    private CargarViewModel cargarViewModel;
    private FragmentCargarBinding binding;

    public static CargarFragment newInstance() {
        return new CargarFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCargarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Instanciar el ViewModel
        cargarViewModel = new ViewModelProvider(requireActivity()).get(CargarViewModel.class);


        cargarViewModel.getmErrorMsj().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String message) {
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        });

        binding.btAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarAuto();
            }
        });
    }

    private void agregarAuto() {
        String patente = binding.etPatente.getText().toString();
        String marca = binding.etMarca.getText().toString();
        String modelo = binding.etModelo.getText().toString();
        String precioString = binding.etPrecio.getText().toString();

        if (patente.isEmpty() || marca.isEmpty() || modelo.isEmpty() || precioString.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double precio = Double.parseDouble(precioString);
            cargarViewModel.cargarAuto(patente, marca, modelo, precio);

            binding.etPatente.setText("");
            binding.etMarca.setText("");
            binding.etModelo.setText("");
            binding.etPrecio.setText("");

        } catch (NumberFormatException e) {

            Toast.makeText(getActivity(), "Por favor ingresa un precio válido", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Evitar fugas de memoria
        binding = null;
    }
}