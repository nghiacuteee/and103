package com.cam.a3_assignment.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cam.a3_assignment.Adapter.CartAdapter;
import com.cam.a3_assignment.CartManager;
import com.cam.a3_assignment.R;
import com.cam.a3_assignment.model.Product;

import java.util.ArrayList;


public class CartScreen extends Fragment implements CartAdapter.OnQuantityChangeListener {

    private ArrayList<Product> cartProducts;
    private TextView txtPrice;
    private CartAdapter cartAdapter;

    public CartScreen() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartProducts = CartManager.getInstance().getCartProducts();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_screen, container, false);
        RecyclerView cartList = view.findViewById(R.id.cartList);
        txtPrice = view.findViewById(R.id.txtPrice);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        cartList.setLayoutManager(linearLayoutManager);
        cartList.setAdapter(new CartAdapter(getContext(), cartProducts, this));

        updateTotalPrice();

        cartList.getAdapter().notifyDataSetChanged();



        return view;
    }

    private void updateTotalPrice() {
        int totalPrice = 0;
        for (Product product : cartProducts) {
            int quantity = product.getQuantity(); // Thêm phương thức getQuantity vào Product class
            int price = product.getProductPrice();
            totalPrice += price * quantity;
        }
        txtPrice.setText("Tông: " +String.valueOf(totalPrice) + " VND");
    }

    @Override
    public void onQuantityChange(Product product, int newQuantity) {
        product.setQuantity(newQuantity); // Thêm phương thức setQuantity vào Product class
        updateTotalPrice();
    }
}