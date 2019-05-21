package com.example.citiesproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CitiesAPI {
    @GET("pag-ini/{pag-ini}/pag-fi/{pag-fi}")
    Call<Cities> getCities(@Path("pag-ini") int pagIni, @Path("pag-fi") int pagFi);
}
