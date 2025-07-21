package com.example.luuduchoa_2123110009;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.List;

public class CartActivity2 extends AppCompatActivity {

    LinearLayout cartContainer;
    Button btnCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart2);

        cartContainer = findViewById(R.id.cartContainer);
        btnCheckout = findViewById(R.id.btnCheckout);

        List<Flower> cart = CartManager.getInstance().getCart();
        int total = 0;

        LayoutInflater inflater = LayoutInflater.from(this);

        for (Flower flower : cart) {
            View itemView = inflater.inflate(R.layout.item_flower, cartContainer, false);

            ImageView image = itemView.findViewById(R.id.itemImage);
            TextView name = itemView.findViewById(R.id.nameText);
            TextView price = itemView.findViewById(R.id.priceText);
            Button addButton = itemView.findViewById(R.id.detailAdd);

            name.setText(flower.getName());
            int itemTotal = flower.getPrice() * flower.getQuantity();
            price.setText("Số lượng: " + flower.getQuantity() + " | Giá: " + itemTotal + "đ");

            // ✅ Load ảnh từ URL nếu có, còn không thì dùng ảnh drawable
            if (flower.getImageUrl() != null) {
                Glide.with(this)
                        .load(flower.getImageUrl())
                        .placeholder(R.drawable.bg_flower)
                        .into(image);
            } else {
                image.setImageResource(flower.getImageResId());
            }

            // Ẩn nút “Thêm” trong giỏ hàng
            addButton.setVisibility(View.GONE);
            cartContainer.addView(itemView);

            total += itemTotal;
        }

        // ✅ Hiển thị tổng cộng
        TextView totalView = new TextView(this);
        totalView.setText("Tổng cộng: " + total + "đ");
        totalView.setTextSize(18);
        totalView.setPadding(0, 16, 0, 0);
        cartContainer.addView(totalView);

        btnCheckout.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity2.this, PaymentActivity.class);
            startActivity(intent);
        });
    }
}
