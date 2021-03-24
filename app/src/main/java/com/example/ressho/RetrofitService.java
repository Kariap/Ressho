package com.example.ressho;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static Retrofit retrofit;
    private static Retrofit retrofitLogin;
    private static final String BASE_URL = "https://91c24db7-46f7-497b-a05d-f06d6cd8b1f8.mock.pstmn.io";
    private static final String BASE_URL_LOGIN = "https://postman-echo.com";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static Retrofit getRetrofitInstanceForLogin() {
        if (retrofitLogin == null) {
            retrofitLogin = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL_LOGIN)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitLogin;
    }

}