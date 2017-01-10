package com.tobedecided.dynamicdialer;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by akash on 11/6/16.
 */

public class ServiceGenerator {

    private static final String BASE_URL = "https://asiasoutheast.services.azureml.net/workspaces/d7bba9989b63439bb7e41496dc93519f/services/01c092065438468fafd44df5f8650b60/";

    private static OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit;

    public static <S> S createService(Class<S> serviceClass){
        retrofit = retrofitBuilder.client(clientBuilder.build()).build();
        return retrofit.create(serviceClass);
    }



}
