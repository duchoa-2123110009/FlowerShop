package com.example.luuduchoa_2123110009;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class CartActivity2 extends AppCompatActivity {

    TextView cartContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart2);

        cartContent = findViewById(R.id.cartContent);

        List<Flower> cart = CartManager.getInstance().getCart();
        StringBuilder display = new StringBuilder();
        int total = 0;

        for (Flower flower : cart) {
            int itemTotal = flower.getPrice() * flower.getQuantity();
            display.append(flower.getName())
                    .append(" x ")
                    .append(flower.getQuantity())
                    .append(" = ")
                    .append(itemTotal)
                    .append("đ\n");
            total += itemTotal;
        }
        display.append("\nTổng cộng: ").append(total).append("đ");
        cartContent.setText(display.toString());
    }
}