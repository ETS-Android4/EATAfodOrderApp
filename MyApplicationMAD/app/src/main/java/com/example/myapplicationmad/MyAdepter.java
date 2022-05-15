package com.example.myapplicationmad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdepter extends RecyclerView.Adapter<MyAdepter.MyViewHolder> {
    Context context;

    ArrayList<FoodSupplyDetails> list;

    public MyAdepter(Context context,ArrayList<FoodSupplyDetails> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        FoodSupplyDetails foodSupplyDetails = list.get(position);
        holder.foodn.setText(foodSupplyDetails.getDishes());
        holder.foodd.setText(foodSupplyDetails.getDescription());
        holder.foodp.setText(foodSupplyDetails.getPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView foodn,foodd,foodp;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            foodn = itemView.findViewById(R.id.foodname);
            foodd = itemView.findViewById(R.id.fooddesc);
            foodp = itemView.findViewById(R.id.foodprice);


        }
    }
}
