package com.example.newsapiretrofit;

import android.util.Log;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("top-headlines")
    Call<India> headlines(@Query("country") String in,@Query("apikey") String api);

}
