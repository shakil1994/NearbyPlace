package com.example.shakil.nearbyplace.Remote;

import com.example.shakil.nearbyplace.Model.MyPlaces;
import com.example.shakil.nearbyplace.Model.PlaceDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Shakil on 2/5/2018.
 */

public interface IGoogleAPIService {
    @GET
    Call<MyPlaces> getNearByPlace(@Url String url);

    @GET
    Call<PlaceDetail> getDetailPlace(@Url String url);
}
