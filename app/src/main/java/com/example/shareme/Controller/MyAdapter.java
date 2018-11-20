package com.example.shareme.Controller;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shareme.Model.Items;
import com.example.shareme.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<Items> items;
    Borrow_page mBorrowPage = new Borrow_page();
    StorageReference storage = FirebaseStorage.getInstance().getReference();;


    public MyAdapter(Context c, ArrayList<Items> i) {
        context = c;
        items = i;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_borrow,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Items item = items.get(position);

        holder.name.setText(item.getName());
        holder.price.setText(String.valueOf(item.getPrice()));
        holder.location.setText(item.getAddress());
        Picasso.get().load(item.getImage().toString()).placeholder(R.drawable.shareme).into(holder.photo);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,Item.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, location;
        ImageView photo;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameTextView);
            price = itemView.findViewById(R.id.priceTextView);
            location = itemView.findViewById(R.id.locationTextView);
            photo = itemView.findViewById(R.id.photoImageView);
        }
    }
}










