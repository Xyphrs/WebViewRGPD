package com.example.webview.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.webview.R;
import com.example.webview.databinding.FragmentAddUrlBinding;
import com.example.webview.databinding.FragmentWebViewBinding;
import com.example.webview.db.MainViewModel;

public class AddUrlFragment extends Fragment {
    FragmentAddUrlBinding binding;
    NavController navController;
    MainViewModel mainViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentAddUrlBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        binding.insert.setOnClickListener(v -> {
            String apodo = binding.apodo.getText().toString();
            String url = binding.url.getText().toString();

            mainViewModel.insert(apodo, url);

            navController.popBackStack();
        });

    }
}