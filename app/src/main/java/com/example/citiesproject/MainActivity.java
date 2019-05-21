package com.example.citiesproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

public class MainActivity extends AppCompatActivity {

    private CitiesAPI api;
    private Cities myCities;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://do.diba.cat/api/dataset/municipis/format/json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(CitiesAPI.class);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCities();
            }
        });
    }

    public void getCities(){
        Call<Cities> call = api.getCities(1,20);

        call.enqueue(new Callback<Cities>(){
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<Cities> call, Response<Cities> response) {
                if(!response.isSuccessful())
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            response.code(),
                            Toast.LENGTH_SHORT);
                    toast.show();
                }

                myCities = response.body();

                mAdapter = new MyAdapter(myCities.getElements(),MainActivity.this);
                recyclerView.setAdapter(mAdapter);
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<Cities> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Unexpected error",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
