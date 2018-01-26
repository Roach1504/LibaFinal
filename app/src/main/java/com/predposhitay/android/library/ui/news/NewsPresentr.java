package com.predposhitay.android.library.ui.news;


import android.util.Log;
import android.widget.ImageView;

import com.predposhitay.android.library.api.App;
import com.predposhitay.android.library.model.NewsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import java.io.IOException;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsPresentr {
    NewsMVP newsMVP;

    public NewsPresentr(NewsMVP newsMVP) {
        this.newsMVP = newsMVP;
    }
    public void startNewsfull(String html){
        this.newsMVP.startFullNew(html);
    }
    public void loadImage(final ImageView imageView, String id_imag){
        //newsMVP.imageLoad(imageView);
        App.getApi().getImage(id_imag).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                newsMVP.stopLoad();
                if(response.code() == 200){
                    try {
                        JSONObject json = new JSONObject(response.body().string());
                        String url = json.getJSONObject("guid").getString("rendered");
                        newsMVP.imageLoad(imageView, url);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                newsMVP.stopLoad();
            }
        });

    }

    public void loadNews(String categ, String page) {
        newsMVP.startLoad();
        App.getApi().getNews(categ, page).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                newsMVP.stopLoad();
                Log.e("Error", response.message() + " " + response.code());

                if(response.code() == 200) {
                    String json = null;
                    JSONObject jsonObject = null;
                    JSONArray jsonArray;
                    ArrayList<NewsModel> newsModels = new ArrayList<>();
                    try {
                        jsonArray = new JSONArray(response.body().string());
                        Log.e("API", jsonArray + " q");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            jsonObject = new JSONObject(jsonArray.get(i).toString());
                            NewsModel newsModel = new NewsModel();
                            // String tit =  new String(jsonObject.getJSONObject("title").getString("rendered").getBytes("UTF-8"), Charset.forName("Windows-1252"));
                            newsModel.setTitle(jsonObject.getJSONObject("title").getString("rendered"));
                            newsModel.setDate(jsonObject.getString("date").replace("T", " "));
                            newsModel.setImage(jsonObject.getString("featured_media"));
                            newsModel.setText(jsonObject.getJSONObject("content").getString("rendered"));
                            jsonObject = jsonObject.getJSONObject("excerpt");
                            json = jsonObject.getString("rendered");
                            Document doc = Jsoup.parse(json);
                            newsModel.setShorts(doc.select("p").first().text());
                            Log.e("post", json + " f");

                            newsModels.add(newsModel);
                        }
                        newsMVP.loadNews(newsModels);
                    } catch (IOException e1) {
                        newsMVP.showMessage("Ошибка загрузки данных");
                        e1.printStackTrace();
                    } catch (JSONException e) {
                        newsMVP.showMessage("Ошибка загрузки данных");
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                newsMVP.stopLoad();
                newsMVP.showMessage("Нет подключения к интернету");
            }
        });

    }
}
