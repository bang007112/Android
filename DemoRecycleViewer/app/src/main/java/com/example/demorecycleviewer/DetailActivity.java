package com.example.demorecycleviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.demorecycleviewer.model.Product;

public class DetailActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        Product product = (Product) bundle.get("object_product");
        TextView ProductName = findViewById(R.id.tv_name_product);
        ProductName.setText(product.getProductName());
    }
}