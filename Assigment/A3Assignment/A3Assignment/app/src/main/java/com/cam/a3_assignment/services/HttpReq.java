package com.cam.a3_assignment.services;

import static com.cam.a3_assignment.services.ApiServices.URL_Base;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpReq {
    private ApiServices req;
    public HttpReq() {
        System.out.println("got HTTP req");
        this.req = new Retrofit.Builder().baseUrl(URL_Base).addConverterFactory(GsonConverterFactory.create()).build().create(ApiServices.class);
    }

    public ApiServices callApi(){
        System.out.println("got Interface");
        return req;
    }
}
