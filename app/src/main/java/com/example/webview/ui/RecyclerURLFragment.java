package com.example.webview.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webview.R;
import com.example.webview.databinding.FragmentRecyclerURLBinding;
import com.example.webview.databinding.ViewholderDataBinding;
import com.example.webview.db.Data;
import com.example.webview.db.MainViewModel;

import java.util.List;

public class RecyclerURLFragment extends Fragment {

    FragmentRecyclerURLBinding binding;
    NavController navController;
    MainViewModel mainViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentRecyclerURLBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        DataAdapter dataAdapter = new DataAdapter();

        binding.recyclerview.setAdapter(dataAdapter);

        mainViewModel.obtener().observe(getViewLifecycleOwner(), dataList -> dataAdapter.setDataList(dataList));

        binding.insert.setOnClickListener(v -> navController.navigate(R.id.action_recyclerURLFragment_to_addUrlFragment));
    }

    class DataAdapter extends RecyclerView.Adapter<DataViewHolder>{

        List<Data> dataList;
        int selected_position = 0;
        @NonNull
        @Override
        public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new DataViewHolder(ViewholderDataBinding.inflate(getLayoutInflater(), parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
            Data data = dataList.get(position);

            holder.binding.apodo.setText(data.apodo);
            holder.binding.url.setText(data.url);

            holder.binding.box.setOnClickListener(v -> {
                mainViewModel.url = holder.binding.url.getText().toString();
                navController.navigate(R.id.action_recyclerURLFragment_to_webViewFragment);
            });
        }

        @Override
        public int getItemCount() {
            return dataList == null ? 0 : dataList.size();
        }

        void setDataList(List<Data> dataList){
            this.dataList = dataList;
            notifyDataSetChanged();
        }
    }

    class DataViewHolder extends RecyclerView.ViewHolder {
        ViewholderDataBinding binding;
        public DataViewHolder(@NonNull ViewholderDataBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }
}