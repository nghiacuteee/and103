package com.cam.a3_assignment.fragment;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.cam.a3_assignment.Adapter.ProductAdapter;
import com.cam.a3_assignment.R;
import com.cam.a3_assignment.model.Response;
import com.cam.a3_assignment.model.Category;
import com.cam.a3_assignment.model.Product;
import com.cam.a3_assignment.services.HttpReq;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class HomeScreen extends Fragment {

    private ArrayList<Category> categories;

    public HomeScreen() {
        // Required empty public constructor
    }

    public static HomeScreen newInstance(String param1, String param2) {
        HomeScreen fragment = new HomeScreen();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView productList;
    private RecyclerView productList2;
    private ConstraintLayout constraintLayout;
    private HttpReq httpReq;
    private ArrayList<Product> products1;
    private ArrayList<Product> products2;
    private TextView catetitle1, catetitle2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        httpReq = new HttpReq();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);
        constraintLayout = view.findViewById(R.id.mainView);
        productList = view.findViewById(R.id.productList);
        productList2 = view.findViewById(R.id.productList2);

        catetitle1 =  view.findViewById(R.id.txtCategoryTitle);
        catetitle2 =  view.findViewById(R.id.txtCategoryTitle2);

        catetitle1.setVisibility(View.GONE);
        catetitle2.setVisibility(View.GONE);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        productList.setLayoutManager(linearLayoutManager);
        productList2.setLayoutManager(linearLayoutManager2);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        httpReq.callApi().getCategories().enqueue(responseCategory);
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        httpReq.callApi().getCategories().enqueue(responseCategory);
//    }

    Callback<Response<ArrayList<Category>>> responseCategory = new Callback<Response<ArrayList<Category>>>() {
        @Override
        public void onResponse(Call<Response<ArrayList<Category>>> call, retrofit2.Response<Response<ArrayList<Category>>> response) {
            if(response.isSuccessful()){
                if(response.body().getStatus() == 200){
                    categories = response.body().getData();
                    System.out.println(categories.get(0).getCategoryID());
                    System.out.println(categories.get(1).getCategoryID());
                    httpReq.callApi().getProductByCategoryID(categories.get(0).getCategoryID()).enqueue(responseProducts1);
                    httpReq.callApi().getProductByCategoryID(categories.get(1).getCategoryID()).enqueue(responseProducts2);
                }
            }
        }

        @Override
        public void onFailure(Call<Response<ArrayList<Category>>> call, Throwable throwable) {
            System.out.println(throwable.getMessage());
        }
    };

    Callback<Response<ArrayList<Product>>> responseProducts1 = new Callback<Response<ArrayList<Product>>>() {
        @Override
        public void onResponse(Call<Response<ArrayList<Product>>> call, retrofit2.Response<Response<ArrayList<Product>>> response) {
            if(response.isSuccessful()){
                if (response.body().getStatus() == 200){
                    catetitle1.setVisibility(View.VISIBLE);
                    productList.setAdapter(new ProductAdapter(getContext(), response.body().getData()));
                }
            }
        }

        @Override
        public void onFailure(Call<Response<ArrayList<Product>>> call, Throwable throwable) {
            System.out.println(throwable.getMessage());
        }
    };

    Callback<Response<ArrayList<Product>>> responseProducts2 = new Callback<Response<ArrayList<Product>>>() {
        @Override
        public void onResponse(Call<Response<ArrayList<Product>>> call, retrofit2.Response<Response<ArrayList<Product>>> response) {
            if(response.isSuccessful()){
                if (response.body().getStatus() == 200){
                    catetitle2.setVisibility(View.VISIBLE);
                    productList2.setAdapter(new ProductAdapter(getContext(), response.body().getData()));
                }
            }
        }

        @Override
        public void onFailure(Call<Response<ArrayList<Product>>> call, Throwable throwable) {
            System.out.println(throwable.getMessage());
        }
    };

}