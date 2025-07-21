package com.example.luuduchoa_2123110009;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class FlowerDetailActivity extends AppCompatActivity {

    TextView nameText, priceText, descText;
    Button addBtn;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower_detail);

        nameText = findViewById(R.id.detailName);
        priceText = findViewById(R.id.detailPrice);
        descText = findViewById(R.id.detailDesc);
        addBtn = findViewById(R.id.detailAddBtn);
        imageView = findViewById(R.id.detailImage);

        // Nhận dữ liệu từ intent
        String name = getIntent().getStringExtra("name");
        int price = getIntent().getIntExtra("price", 0);
        String desc = getIntent().getStringExtra("desc");

        // Gán dữ liệu vào giao diện
        nameText.setText(name);
        priceText.setText(price + "đ");
        descText.setText(desc);

        // ✅ Hiển thị ảnh từ URL hoặc từ resource
        String imageUrl = getIntent().getStringExtra("imageUrl");
        if (imageUrl != null) {
            Glide.with(this)
                    .load(imageUrl)
                    .placeholder(R.drawable.bg_flower)
                    .into(imageView);
        } else {
            int imageResId = getIntent().getIntExtra("imageResId", R.drawable.bg_flower);
            imageView.setImageResource(imageResId);
        }

        // ✅ Thêm vào giỏ hàng
        addBtn.setOnClickListener(v -> {
            Flower flower;
            if (imageUrl != null) {
                flower = new Flower(name, price, imageUrl);
            } else {
                int imageResId = getIntent().getIntExtra("imageResId", R.drawable.bg_flower);
                flower = new Flower(name, price, imageResId);
            }
            CartManager.getInstance().addToCart(flower);
            Toast.makeText(this, "Đã thêm vào giỏ", Toast.LENGTH_SHORT).show();
        });
    }
}
