package com.example.luuduchoa_2123110009;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.ViewHolder> {

    private Context context;
    private List<Flower> flowerList;

    public FlowerAdapter(Context context, List<Flower> flowerList) {
        this.context = context;
        this.flowerList = flowerList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, priceText;
        Button addButton;

        public ViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.nameText);
            priceText = itemView.findViewById(R.id.priceText);
            addButton = itemView.findViewById(R.id.btnAdd);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_flower, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Flower flower = flowerList.get(position);
        holder.nameText.setText(flower.getName());
        holder.priceText.setText(flower.getPrice() + "đ");

        holder.addButton.setOnClickListener(v -> CartManager.getInstance().addToCart(flower));
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, FlowerDetailActivity.class);
            intent.putExtra("name", flower.getName());
            intent.putExtra("price", flower.getPrice());
            intent.putExtra("desc", "Đây là hoa " + flower.getName() + " tuyệt đẹp.");
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return flowerList.size();
    }
}