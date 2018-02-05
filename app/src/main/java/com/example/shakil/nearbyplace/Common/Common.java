package com.example.shakil.nearbyplace.Common;

import com.example.shakil.nearbyplace.Model.Results;
import com.example.shakil.nearbyplace.Remote.IGoogleAPIService;
import com.example.shakil.nearbyplace.Remote.RetrofitClient;

/**
 * Created by Shakil on 2/5/2018.
 */

public class Common {

    public static Results currentResult;

    private static final String GOOGLE_API_URL = "https://maps.googleapis.com/";
    public static IGoogleAPIService getGoogleAPIService(){
        return RetrofitClient.getClient(GOOGLE_API_URL).create(IGoogleAPIService.class);
    }
}
