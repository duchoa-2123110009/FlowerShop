package com.example.luuduchoa_2123110009;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

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

        String name = getIntent().getStringExtra("name");
        int price = getIntent().getIntExtra("price", 0);
        String desc = getIntent().getStringExtra("desc");

        nameText.setText(name);
        priceText.setText(price + "đ");
        descText.setText(desc);
        imageView.setImageResource(R.drawable.bg_flower); // icon mẫu

        addBtn.setOnClickListener(v -> {
            CartManager.getInstance().addToCart(new Flower(name, price));
            Toast.makeText(this, "Đã thêm vào giỏ", Toast.LENGTH_SHORT).show();
        });
    }
}