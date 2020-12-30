package com.yogdroidtech.dogsapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yogdroidtech.dogsapp.R;
import com.yogdroidtech.dogsapp.model.Dog;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<Dog> dogs = new ArrayList<>();
    Context context;

    public MyAdapter(Context context) {
        this.context = context;
    }

    public void updateAdapter(List<Dog> dogsPassed){
        dogs=dogsPassed;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(context).inflate(R.layout.row_layout, parent,false);
        return new MyAdapter.MyViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(dogs.get(position).getName());
        holder.lifespan.setText(dogs.get(position).getLifeSpan());
        loadImage(holder.imageView,dogs.get(position).getUrl(),getProgressDrawable(context));
//        Glide.with(context).load(dogs.get(position).getUrl()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action  = ListFragmentDirections.actionDetail(position);
                Navigation.findNavController(holder.imageView).navigate(action);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dogs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, lifespan;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.name);
            lifespan = itemView.findViewById(R.id.lifespan);

        }

    }

    public void loadImage(ImageView imageView, String url, CircularProgressDrawable progressDrawable) {
        RequestOptions options = new RequestOptions()
                .placeholder(progressDrawable);
        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(options)
                .load(url).override(200,200)
                .into(imageView);
    }
    public CircularProgressDrawable getProgressDrawable(Context context) {
        CircularProgressDrawable cpd = new CircularProgressDrawable(context);
        cpd.setStrokeWidth(10f);
        cpd.setCenterRadius(50f);
        cpd.start();
        return cpd;
    }
}
