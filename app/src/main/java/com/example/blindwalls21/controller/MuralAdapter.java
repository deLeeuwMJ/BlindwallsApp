package com.example.blindwalls21.controller;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blindwalls21.R;
import com.example.blindwalls21.model.DetailPage;
import com.example.blindwalls21.model.Mural;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MuralAdapter extends RecyclerView.Adapter<MuralAdapter.ImageViewHolder> {

    private ArrayList<Mural> dataSet;

    public MuralAdapter(ArrayList<Mural> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mainactivity_recycler_item, parent, false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view);

        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder viewHolder, int i) {

        viewHolder.title.setText(dataSet.get(i).getTitle());
        Picasso.get().load(dataSet.get(i).getImageURL()[0]).into(viewHolder.picture);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    class ImageViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView picture;


        ImageViewHolder(View itemview) {
            super(itemview);

            title = itemview.findViewById(R.id.author_id);
            picture = itemview.findViewById(R.id.mural_id);

            itemview.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), DetailPage.class);
                Mural n = dataSet.get(ImageViewHolder.super.getAdapterPosition());
                intent.putExtra("selectedMural", n);
                v.getContext().startActivity(intent);
            });
        }
    }

}