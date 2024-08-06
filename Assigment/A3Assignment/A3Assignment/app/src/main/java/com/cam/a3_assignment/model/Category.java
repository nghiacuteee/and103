package com.cam.a3_assignment.model;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("_id")
    private String categoryID;
    private String catrgoryName;

    public Category(String categoryID, String catrgoryName) {
        this.categoryID = categoryID;
        this.catrgoryName = catrgoryName;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCatrgoryName() {
        return catrgoryName;
    }

    public void setCatrgoryName(String catrgoryName) {
        this.catrgoryName = catrgoryName;
    }
}
