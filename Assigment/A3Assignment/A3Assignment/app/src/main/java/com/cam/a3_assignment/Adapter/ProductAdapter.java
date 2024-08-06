package com.cam.a3_assignment.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cam.a3_assignment.CartManager;
import com.cam.a3_assignment.R;
import com.cam.a3_assignment.fragment.CartScreen;
import com.cam.a3_assignment.model.Product;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    private final Context context;
    private ArrayList<Product> productArrayList;
    private ArrayList<Product> cartProducts = new ArrayList<>();

    public ProductAdapter(Context context, ArrayList<Product> productArrayList) {
        this.context = context;
        this.productArrayList = productArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.product_item_view, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("CommitTransaction")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productArrayList.get(holder.getAdapterPosition());
        holder.txtProductName.setText(product.getProductName());
        holder.txtProductPrice.setText(product.getProductPrice()+" VND");
        Glide.with(context).load(product.getProductImgURI().get(0)).centerCrop().into(holder.productimage);

        holder.addToCart.setOnClickListener(v -> {
            CartManager.getInstance().addProduct(product);

            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
            Log.d("Cart", "Product added: " + product.getProductName());
            Log.d("Cart", "Product added: " + product);


//            FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
//            CartScreen cartScreen = CartScreen.newInstance(cartProducts);
//            fragmentManager.beginTransaction()
//                    .replace(R.id.fragmentContainer, cartScreen)
//                    .addToBackStack(null)
//                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView productimage;
        TextView txtProductName, txtProductPrice;
        Button addToCart;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productimage = itemView.findViewById(R.id.itemImageView);
            txtProductName = itemView.findViewById(R.id.itemTitle);
            txtProductPrice = itemView.findViewById(R.id.itemPrice);
            addToCart = itemView.findViewById(R.id.btnAddToCart);
        }
    }
}
