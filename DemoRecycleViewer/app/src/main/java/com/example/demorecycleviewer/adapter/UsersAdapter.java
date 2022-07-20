package com.example.demorecycleviewer.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demorecycleviewer.DetailActivity;
import com.example.demorecycleviewer.R;
import com.example.demorecycleviewer.model.Product;

import org.w3c.dom.Text;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.userViewHolder>{

    private List<Product> mListProduct;

    private Context mContext;

    public UsersAdapter(Context context, List<Product> mListProduct) {
        this.mContext = context;
        this.mListProduct = mListProduct;
    }

    @NonNull
    @Override
    public userViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new userViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull userViewHolder holder, int position) {
        final Product product = mListProduct.get(position);
        if(product == null){
            return;
        }
        holder.imgAvt.setImageResource(product.getProductId());
        holder.productName.setText(product.getProductName());
        holder.manufacture.setText(product.getManufacture());
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToDetail(product);
            }
        });
    }

    private void onClickGoToDetail(Product product){
        Intent intent = new Intent(mContext, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_product", product);
        intent.putExtras(bundle);
        mContext.startActivity(intent);

    }

    @Override
    public int getItemCount() {
        if(mListProduct != null){
            return mListProduct.size();
        }
        return 0;
    }

    public class userViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgAvt;
        private RelativeLayout layoutItem;
        private TextView productName;
        private TextView manufacture;

        public userViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvt = itemView.findViewById(R.id.img_avt);
            layoutItem = itemView.findViewById(R.id.layout_item);
            productName = itemView.findViewById(R.id.tv_name);
            manufacture = itemView.findViewById(R.id.tv_manufacture);
        }
    }
    public void release(){
        mContext = null;
    }
}
