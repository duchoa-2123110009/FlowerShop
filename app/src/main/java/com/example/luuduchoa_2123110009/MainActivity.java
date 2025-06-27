package com.example.luuduchoa_2123110009;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FlowerAdapter adapter;
    private Button cartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        cartButton = findViewById(R.id.btnViewCart);

        List<Flower> flowerList = new ArrayList<>();
        flowerList.add(new Flower("Hoa Hồng", 10000));
        flowerList.add(new Flower("Hoa Cúc", 8000));
        flowerList.add(new Flower("Hoa Ly", 12000));
        flowerList.add(new Flower("Hoa Mẫu Đơn", 16000));
        flowerList.add(new Flower("Hoa Vạn Thọ", 18000));
        flowerList.add(new Flower("Hoa Sao Nhái", 19000));
        flowerList.add(new Flower("Hoa Ngũ Sắc", 2000));

        adapter = new FlowerAdapter(this, flowerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        Button GotoCard = findViewById(R.id.btnViewCart);
        GotoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), CartActivity2.class);
                startActivity(it);
            }
        });



    }
}