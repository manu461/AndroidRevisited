package com.example.stalker.mailchimpdemoone;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by staLker on 07-07-2016.
 */
public interface MailChimpService {
    @GET("lists/list")
    Call<ClassForList> getListMethod(@Query("apikey") String apiKey);

    @GET("lists/activity")
    Call<ClassForActivity> getActivityMethod(@Query("apikey") String apiKey, @Query("id") String id);
}
