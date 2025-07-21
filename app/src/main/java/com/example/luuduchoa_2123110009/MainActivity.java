package com.example.luuduchoa_2123110009;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private FlowerAdapter adapter;
    private Button cartButton;
    private SearchView searchView;

    private List<Flower> flowerList = new ArrayList<>();
    private List<Flower> filteredList = new ArrayList<>();

    private String apiUrl = "https://api.escuelajs.co/api/v1/products";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        cartButton = findViewById(R.id.btnViewCart);
        searchView = findViewById(R.id.searchView);

        adapter = new FlowerAdapter(this, filteredList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        cartButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CartActivity2.class);
            startActivity(intent);
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterFlowers(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterFlowers(newText);
                return true;
            }
        });

        fetchProductsFromApi();
    }

    private void filterFlowers(String keyword) {
        filteredList.clear();
        if (keyword.isEmpty()) {
            filteredList.addAll(flowerList);
        } else {
            String lowerKeyword = keyword.toLowerCase();
            for (Flower flower : flowerList) {
                if (flower.getName().toLowerCase().contains(lowerKeyword)) {
                    filteredList.add(flower);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void fetchProductsFromApi() {
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, apiUrl, null,
                response -> {
                    flowerList.clear();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject obj = response.getJSONObject(i);
                            String title = obj.getString("title");
                            int price = obj.getInt("price");
                            JSONArray images = obj.getJSONArray("images");
                            String imageUrl = images.getString(0); // ảnh đầu tiên

                            Flower flower = new Flower(title, price, imageUrl);
                            flowerList.add(flower);
                        } catch (Exception e) {
                            Log.e(TAG, "Parse error: " + e.getMessage());
                        }
                    }
                    filteredList.clear();
                    filteredList.addAll(flowerList);
                    adapter.notifyDataSetChanged();
                },
                error -> {
                    Toast.makeText(this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Volley error: " + error.toString());
                });

        queue.add(request);
    }
}
