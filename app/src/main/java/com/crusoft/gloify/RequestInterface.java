package com.crusoft.gloify;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

interface RequestInterface {
    String BASE_URL = "https://api.learn2crack.com";

    @GET("android/jsonandroid")
    Call<JsonResponse> getAndroidJson();

}
