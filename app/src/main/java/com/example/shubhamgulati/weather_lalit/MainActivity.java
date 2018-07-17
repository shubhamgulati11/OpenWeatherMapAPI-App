package com.example.shubhamgulati.weather_lalit;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    EditText etSearch;
    Button btnSearch;
    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_AUTO);
        setContentView(R.layout.activity_main);
        etSearch = findViewById(R.id.etSearch);
        btnSearch = findViewById(R.id.btnSearch);
        tvInfo= findViewById(R.id.tvInfo);
        String url = "https://api.openweathermap.org/data/2.5/weather?q=faridabad&appid=237f8c910c9a710ff49c7f1dfbe7c67d&units=metric";
        NetworkCall(url);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://api.openweathermap.org/data/2.5/weather?q="+etSearch.getText()+"&appid=237f8c910c9a710ff49c7f1dfbe7c67d&units=metric";
                NetworkCall(url);
            }
        });
    }
    private void NetworkCall(String url){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
//                Toast.makeText(MainActivity.this, "Enter Correct City", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                final APIResponse apiResponse = gson.fromJson(result,APIResponse.class);
                Log.e("TAG",result);
//                SharedPreferences sharedpreferences = getSharedPreferences("Shared prefs",Context.MODE_PRIVATE);
//                final SharedPreferences.Editor editor = sharedpreferences.edit();
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String toset=""+apiResponse.getName()+" "+apiResponse.sys.getCountry()
                                +"\nTemperature: "+apiResponse.main.getTemp()+"°C"
                                +"\nMinimum Temperature: "+apiResponse.main.getTemp_min()+"°C"
                                +"\nMaximum Temperature: "+apiResponse.main.getTemp_max()+"°C"
                                +"\nWind: "
                                +"\n Speed: "+apiResponse.wind.getSpeed()
                                +"\n Degree:"+apiResponse.wind.getDeg()+"°"
                                ;
                        //tvInfo.setText(toset);
                        Log.e("TAG",""+apiResponse.main.getTemp());
                        for(Weather w: apiResponse.getWeather()){
                            Log.e("TAG",""+w.getDescription());
                            toset=toset+"\nDescription: "+w.getDescription();
                        }
                        tvInfo.setText(toset);
//                        editor.putString("city",apiResponse.getName());
//                        editor.putString("country",apiResponse.sys.getCountry());
//                        editor.putString("temp",apiResponse.main.getTemp());
//                        editor.putString("temp_min",apiResponse.main.getTemp_min());
//                        editor.putString("temp_max",apiResponse.main.getTemp_max());


                    }
                });

            }
        });
    }
}
