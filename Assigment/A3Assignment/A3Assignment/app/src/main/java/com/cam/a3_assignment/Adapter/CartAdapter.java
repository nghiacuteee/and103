package com.cam.a3_assignment.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cam.a3_assignment.R;
import com.cam.a3_assignment.fragment.CartScreen;
import com.cam.a3_assignment.model.Product;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Product> listProduct;
    private OnQuantityChangeListener quantityChangeListener;

//    public CartAdapter(Context context) {
//        this.context = context;
//    }

    public CartAdapter(Context context, ArrayList<Product> listProduct, OnQuantityChangeListener listener) {
        this.context = context;
        this.listProduct = (listProduct != null) ? listProduct : new ArrayList<>();
        this.quantityChangeListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.product_cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = listProduct.get(position);

        holder.txtProductName.setText(product.getProductName());
        holder.txtProductPrice.setText(product.getProductPrice() + " VND");
        Glide.with(context).load(product.getProductImgURI().get(0)).into(holder.imgProduct);

        holder.tvQuantity.setText(String.valueOf(product.getQuantity()));

        holder.btnMinus.setOnClickListener(v -> {
            int quantity =Integer.parseInt(holder.tvQuantity.getText().toString());
            if (quantity > 1) {
                holder.tvQuantity.setText(String.valueOf(quantity - 1));
                notifyQuantityChange(product, quantity - 1);
            }
        });

        holder.btnPlus.setOnClickListener(v -> {
            int quantity = Integer.parseInt(holder.tvQuantity.getText().toString());
            holder.tvQuantity.setText(String.valueOf(quantity + 1));
            notifyQuantityChange(product, quantity + 1);
        });

    }

    private void notifyQuantityChange(Product product, int newQuantity) {
        if (quantityChangeListener != null) {
            quantityChangeListener.onQuantityChange(product, newQuantity);
        }
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public interface OnQuantityChangeListener {
        void onQuantityChange(Product product, int newQuantity);
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtProductName, txtProductPrice,  tvQuantity;
        ImageView imgProduct, btnMinus, btnPlus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtProductPrice = itemView.findViewById(R.id.txtProductPrice);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            btnPlus = itemView.findViewById(R.id.btnPlus);


        }
    }
}
