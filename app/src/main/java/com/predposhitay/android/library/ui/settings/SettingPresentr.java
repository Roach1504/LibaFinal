package com.predposhitay.android.library.ui.settings;


import android.content.SharedPreferences;
import android.util.Log;

import com.predposhitay.android.library.api.App;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingPresentr {
    settingMVP mvp;
    SharedPreferences user;
    public SettingPresentr(settingMVP mvp, SharedPreferences user){
        this.mvp = mvp;
        this.user = user;
    }
    void rename(final String name, String id){
        App.getApi().setName(name, id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
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
                                String log = "";
                                try {
                                    dataJsonObj = new JSONObject(response1);
                                    log = dataJsonObj.getString("status");
                                    Log.e("ID", log);
                                    if(id.equals("Данное имя уже занято")){
                                        mvp.message("Данное имя уже занято");
                                    }
                                    else{
                                        mvp.message("Имя успешно изменено");
                                        user.edit().putString("login", name).commit();
                                    }
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
