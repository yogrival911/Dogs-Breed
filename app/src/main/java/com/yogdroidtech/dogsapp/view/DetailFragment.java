package com.yogdroidtech.dogsapp.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavArgs;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yogdroidtech.dogsapp.R;
import com.yogdroidtech.dogsapp.model.Dog;
import com.yogdroidtech.dogsapp.viewmodel.DetailViewModel;
import com.yogdroidtech.dogsapp.viewmodel.ListViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment {
    @BindView(R.id.detailName)
    TextView detailName;

    @BindView(R.id.imageViewDetail)
    ImageView imageViewDetail;
    DetailViewModel detailViewModel;
    public DetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int positionPassed = DetailFragmentArgs.fromBundle(getArguments()).getPositionPassed();
        Log.i("yogesh",positionPassed+" position");
        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        detailViewModel.getDogs();
        detailViewModel.dogList.observe(this, new Observer<List<Dog>>() {
            @Override
            public void onChanged(List<Dog> dogs) {
                Log.i("yogesh",dogs.get(positionPassed).getName());
                Glide.with(getContext()).load(dogs.get(positionPassed).getUrl()).into(imageViewDetail);
            }
        });
    }

}