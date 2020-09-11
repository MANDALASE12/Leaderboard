package com.kalu.cynthia.data_operation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {

    private static final String LEADER_URL = "https://gadsapi.herokuapp.com/api/";
    private static final String SUBMIT_URL = "https://docs.google.com/forms/d/e/";


    private static  HttpLoggingInterceptor logger = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient.Builder client = new OkHttpClient.Builder().addInterceptor(logger);


    private static Gson gson= new GsonBuilder().setLenient().create();

    private static Retrofit.Builder lBUILDER = new Retrofit.Builder().baseUrl(LEADER_URL)
        .addConverterFactory(GsonConverterFactory.create(gson)).client(client.build());

    private static Retrofit mRetrofit = lBUILDER.build();


    public static <S> S leaderBuildService(Class<S> serviceType){
        return mRetrofit.create(serviceType);
    }

    private static Retrofit.Builder sBuilderBUILDER = new Retrofit.Builder().baseUrl(SUBMIT_URL)
        .addConverterFactory(GsonConverterFactory.create(gson)).client(client.build());

    private static Retrofit sRetrofit = sBuilderBUILDER.build();

    @NotNull
    public static <S> S submitBuildService(Class<S> serviceType){
        return sRetrofit.create(serviceType);
    }


}
