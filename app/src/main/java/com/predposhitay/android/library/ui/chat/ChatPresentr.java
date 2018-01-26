package com.predposhitay.android.library.ui.chat;



import android.util.Log;

import com.predposhitay.android.library.api.App;
import com.predposhitay.android.library.model.MessageModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatPresentr {
    ChatMVP mvp;

    ChatPresentr(ChatMVP mvp){
        this.mvp = mvp;
    }

    void getMessage(String last_message_id, String direction, String limit){
        mvp.startLoading();
        if(direction.equals("1")) {
            App.getApi().getMessage(last_message_id, direction, limit).enqueue(new Callback<MessageModel>() {
                @Override
                public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                    mvp.stopLoading();
                    if (response.message().equals("OK")) {
                        if (response.body().getMessages().isEmpty()) {
                            mvp.isEmty();
                        } else {
                            Log.e("message", response.body().getMessages().get(0).getId());
                            mvp.getMssage(response.body().getMessages());
                        }
                    } else {
                        mvp.showError("Сервис не доступен");
                    }

                }

                @Override
                public void onFailure(Call<MessageModel> call, Throwable t) {
                    mvp.stopLoading();
                    mvp.showError("Нет соеденения");
                }
            });
        }
        else{
            App.getApi().getMessage(last_message_id, direction, limit).enqueue(new Callback<MessageModel>() {
                @Override
                public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                    mvp.stopLoading();
                    if (response.message().equals("OK")) {
                        if (response.body().getMessages().isEmpty()) {
                            mvp.isEmty();
                        } else {
                            Log.e("messageUP", response.body().getMessages().get(0).getId());
                            mvp.getMessageUp(response.body().getMessages());
                        }
                    } else {
                        mvp.showError("Сервис не доступен");
                    }
                }

                @Override
                public void onFailure(Call<MessageModel> call, Throwable t) {
                    mvp.stopLoading();
                    mvp.showError("Нет соеденения");
                }
            });
        }
    }

    void sendMessage(String text, String id){
        mvp.startLoading();
        App.getApi().sendMessage(text, id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                mvp.stopLoading();;
                mvp.sendMessage();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mvp.stopLoading();
            }
        });
    }
}
