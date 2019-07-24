package com.example.newsapiretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
   // List<Article> list;
    //List<India.Article> indiaList;
    SwipeRefreshLayout refreshLayout;
    private final static String API_KEY = "e2ff7bbf352444838dfcae62a20f98b7";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshLayout = findViewById(R.id.swipe);
        getNews();
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                           new Handler().postDelayed(new Runnable() {
                              @Override
                               public void run() {
                                   refreshLayout.setRefreshing(false);
                                   getNews();
                               }
                           },3000);
            }
        });


       }

    private void getNews() {
        Api api = RetrofitBuilder.getClient().create(Api.class);
        Call<India> call = api.headlines("in",API_KEY);
        call.enqueue(new Callback<India>() {
            @Override
            public void onResponse(Call<India> call, Response<India> response) {
                List<Article> list = response.body().getArticles();
                Log.d("responnnnn",""+list);
                recyclerView = findViewById(R.id.rv);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                MyAdapter myAdapter=new MyAdapter(MainActivity.this,list);
                Log.d("lisssssss",""+list);
                recyclerView.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<India> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

