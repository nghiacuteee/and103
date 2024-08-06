package com.cam.a3_assignment.services;

import com.cam.a3_assignment.model.Account;
import com.cam.a3_assignment.model.Category;
import com.cam.a3_assignment.model.Product;
import com.cam.a3_assignment.model.Response;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface ApiServices {
    public static String URL_Base = "http://10.24.6.189:3000/api/";
    @GET("get-products")
    Call<Response<ArrayList<Product>>> getProducts();

    @GET("list-products-by-type/{categoryid}")
    Call<Response<ArrayList<Product>>> getProductByCategoryID(@Path("categoryid") String category_id);

    @GET("get-product-by-id/{id}")
    Call<Response<Product>> getProductById(@Path("id") String id);

    @GET("get-product-by-name/{name}")
    Call<Response<ArrayList<Product>>> searchProduct(@Path("name") String name);

    @GET("list-category")
    Call<Response<ArrayList<Category>>> getCategories();

    @POST("sign-up")
    Call<Response<Account>> signup(@Body Account account);

    @POST("login")
    Call<Response<Account>> login(@Body Account account);

    @Multipart
    @PUT("update-account/{id}")
    Call<Response<Account>> updateAccount(@Path("id") String id,
                                          @PartMap Map<String, RequestBody> requestBodyMap,
                                          @Part MultipartBody.Part avatarURI);
}
