package com.example.luuduchoa_2123110009;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide; // ✅ Thêm Glide

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
        ImageView imageView;
        Button addButton;

        public ViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.nameText);
            priceText = itemView.findViewById(R.id.priceText);
            imageView = itemView.findViewById(R.id.itemImage);
            addButton = itemView.findViewById(R.id.detailAdd);
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

        // ✅ Sử dụng Glide để load ảnh từ URL nếu có
        if (flower.getImageUrl() != null) {
            Glide.with(context)
                    .load(flower.getImageUrl())
                    .placeholder(R.drawable.bg_flower) // ảnh tạm trong lúc load
                    .into(holder.imageView);
        } else {
            holder.imageView.setImageResource(flower.getImageResId());
        }

        holder.addButton.setOnClickListener(v -> {
            CartManager.getInstance().addToCart(flower);
        });

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, FlowerDetailActivity.class);
            intent.putExtra("name", flower.getName());
            intent.putExtra("price", flower.getPrice());
            intent.putExtra("desc", "khó lắm mới mua được " + flower.getName() + " này đó nha, nhớ mua ák.");

            // ✅ Truyền cả ảnh theo URL nếu có
            if (flower.getImageUrl() != null) {
                intent.putExtra("imageUrl", flower.getImageUrl());
            } else {
                intent.putExtra("imageResId", flower.getImageResId());
            }

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return flowerList.size();
    }
}
