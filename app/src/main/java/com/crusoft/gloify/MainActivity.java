package com.crusoft.gloify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

  ArrayList<AndroidModel> arrayList = new ArrayList<>();
    RecyclerView superListView;
    private DataAdapter da;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        superListView = findViewById(R.id.superListView);
        superListView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        superListView.setLayoutManager(layoutManager);
        getandroidResponse();
    }

    private void getandroidResponse() {

        Call<JsonResponse> call = RetrofitClient.getInstance().getMyApi().getAndroidJson();
        call.enqueue(new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {


                JsonResponse myjsonresponse = response.body();
                Log.e("MainActivity:","Response-->" + myjsonresponse.getAndroid().length);
                String[] AndroidTypes = new String[20];
                  arrayList = new ArrayList<>(Arrays.asList(myjsonresponse.getAndroid()));
               for (int i = 0; i < arrayList.size(); i++) {
                   AndroidTypes[i] = arrayList.get(i).getName();
                }
                da =new  DataAdapter(getApplicationContext(),arrayList);
                superListView.setAdapter(da);

                Toast.makeText(MainActivity.this,"success",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"failed",Toast.LENGTH_LONG).show();

            }
        });

    }
}
