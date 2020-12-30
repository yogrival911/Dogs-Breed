package com.yogdroidtech.dogsapp.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yogdroidtech.dogsapp.R;
import com.yogdroidtech.dogsapp.model.Dog;
import com.yogdroidtech.dogsapp.viewmodel.ListViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragment extends Fragment {

    private  ListViewModel viewModel;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public ListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this,view);
        MyAdapter myAdapter = new MyAdapter(getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(myAdapter);
        viewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        viewModel.refresh();
        viewModel.dogList.observe(this, new Observer<List<Dog>>() {
            @Override
            public void onChanged(List<Dog> dogs) {
                myAdapter.updateAdapter(dogs);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

}