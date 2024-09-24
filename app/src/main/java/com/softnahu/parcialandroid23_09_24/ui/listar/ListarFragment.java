package com.softnahu.parcialandroid23_09_24.ui.listar;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softnahu.parcialandroid23_09_24.R;
import com.softnahu.parcialandroid23_09_24.databinding.FragmentCargarBinding;
import com.softnahu.parcialandroid23_09_24.databinding.FragmentListarBinding;
import com.softnahu.parcialandroid23_09_24.model.Auto;

import java.util.ArrayList;

public class ListarFragment extends Fragment {

    private ListarViewModel lViewModel;
    private FragmentListarBinding binding;
    private ListaAdapter adapter;


    public static ListarFragment newInstance() {
        return new ListarFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        lViewModel = new ViewModelProvider(this).get(ListarViewModel.class);
        binding = FragmentListarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        lViewModel.getListaAutos().observe(getViewLifecycleOwner(), new Observer<ArrayList<Auto>>() {
            @Override
            public void onChanged(ArrayList<Auto> autos) {
                adapter = new ListaAdapter(autos,inflater);
                GridLayoutManager grilla = new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
                binding.rvLista.setAdapter(adapter);
                binding.rvLista.setLayoutManager(grilla);
            }
        });
        lViewModel.imprimirLista();
        return root;
    }


}