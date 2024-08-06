package com.cam.a3_assignment.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Product implements Parcelable {
    @SerializedName("_id")
    private String productID;
    @SerializedName("name")
    private String productName;
    @SerializedName("price")
    private int productPrice;
    @SerializedName("imageurl")
    private ArrayList<String> productImgURI;
    @SerializedName("description")
    private String productDes;
    @SerializedName("category_id")
    private String productCategoryID;
    private int quantity;
    private int status;

    public Product(String productID, String productName, int productPrice, ArrayList<String> productImgURI, String productDes, String productCategoryID, int quantity, int status) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImgURI = productImgURI;
        this.productDes = productDes;
        this.productCategoryID = productCategoryID;
        this.quantity = quantity;
        this.status = status;
    }

    protected Product(Parcel in) {
        productID = in.readString();
        productName = in.readString();
        productPrice = in.readInt();
        productImgURI = in.createStringArrayList();
        productDes = in.readString();
        productCategoryID = in.readString();
        quantity = in.readInt();
        status = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public ArrayList<String> getProductImgURI() {
        return productImgURI;
    }

    public void setProductImgURI(ArrayList<String> productImgURI) {
        this.productImgURI = productImgURI;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDes() {
        return productDes;
    }

    public void setProductDes(String productDes) {
        this.productDes = productDes;
    }

    public String getProductCategoryID() {
        return productCategoryID;
    }

    public void setProductCategoryID(String productCategoryID) {
        this.productCategoryID = productCategoryID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(productName);
        dest.writeString(String.valueOf(productPrice));
        dest.writeStringList(productImgURI);
    }
}
