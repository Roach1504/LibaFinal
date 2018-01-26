package com.predposhitay.android.library.NetWork;



import android.content.SharedPreferences;
import android.util.Log;

import com.predposhitay.android.library.api.App;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Regist {
    SharedPreferences user;
    public Regist(SharedPreferences user){
        this.user = user;
    }

    public void regist(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("mmss");
        String data = dateFormat.format(new Date());
        Random random = new Random();
        final String login = "Aноним_"+ data+random.nextInt(9999);
        App.getApi().registUser(login).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("JSON", "start" + " "+ response.headers().toString());
               // Log.e("JSON", "start" + " "+ response.body().string());

                String json = null;
                try {
                    json = response.body().string().trim();
                    Log.e("Body","/"+ json);
                } catch (IOException e) {
                    Log.e("JSON", "error 1");
                    e.printStackTrace();
                }
                if(json != null){
                    Log.e("JSON", json + " mess");
                    try {
                        JSONArray mass = new JSONArray(json);
                        JSONObject dataJsonObj = null;
                        String response1 = "";
                        try {
                            dataJsonObj = new JSONObject(mass.get(0).toString());
                            response1 = dataJsonObj.getString("response");
                            JSONObject idJson = null;
                            String id = "";
                            try {
                                dataJsonObj = new JSONObject(response1);
                                id = dataJsonObj.getString("id");
                                Log.e("ID", id);

                                user.edit().putString("id", id).commit();
                                user.edit().putString("login", login).commit();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } catch (JSONException e) {
                        Log.e("JSON", "error 2");
                        e.printStackTrace();
                    }
                }
                else{
                    Log.e("JSON","error");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
