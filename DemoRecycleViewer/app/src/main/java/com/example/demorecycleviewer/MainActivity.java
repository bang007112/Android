package com.example.demorecycleviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.demorecycleviewer.adapter.UsersAdapter;
import com.example.demorecycleviewer.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvData;
    private UsersAdapter usersAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvData = findViewById(R.id.rcv_data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvData.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvData.addItemDecoration(itemDecoration);
        usersAdapter = new UsersAdapter(this, getProductList());
        rcvData.setAdapter(usersAdapter);
    }

    private List<Product> getProductList() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(R.mipmap.ic_launcher, "apple", "apple manufacture"));
        products.add(new Product(R.mipmap.ic_launcher, "samsung", "samsung manufacture"));
        products.add(new Product(R.mipmap.ic_launcher, "xiaomi", "xiaomi manufacture"));
        products.add(new Product(R.mipmap.ic_launcher, "huawei", "huawei manufacture"));
        products.add(new Product(R.mipmap.ic_launcher, "oppo", "oppo manufacture"));
        products.add(new Product(R.mipmap.ic_launcher, "nokia", "nokia manufacture"));
        products.add(new Product(R.mipmap.ic_launcher, "motorola", "motorola manufacture"));
        products.add(new Product(R.mipmap.ic_launcher, "readme", "readme manufacture"));
        products.add(new Product(R.mipmap.ic_launcher, "LG", "LG manufacture"));
        products.add(new Product(R.mipmap.ic_launcher, "asus", "asus manufacture"));
        return products;
    }
    protected void onDestroy() {
        super.onDestroy();
        if (usersAdapter != null) {
            usersAdapter.release();
        }
    }

}