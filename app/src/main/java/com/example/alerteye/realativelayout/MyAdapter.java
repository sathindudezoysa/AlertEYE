package com.example.alerteye.realativelayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.alerteye.R;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    List<Item>  items;
    public MyAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyAdapter.MyViewHolder holder, int position) {
        holder.date.setText(items.get(position).getDate());
        holder.value.setText(Double.toString(items.get(position).getValue()));
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView date,value;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
             date = itemView.findViewById(R.id.date);
             value = itemView.findViewById(R.id.value);
            }
        }
    }

