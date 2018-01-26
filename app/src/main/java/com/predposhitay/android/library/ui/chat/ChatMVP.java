package com.predposhitay.android.library.ui.chat;


import com.predposhitay.android.library.model.Message;

import java.util.List;

public interface ChatMVP {
    void getMssage(List<Message> messages);
    void isEmty();
    void showError(String error);
    void getMessageUp(List<Message> messages);
    void sendMessage();
    void startLoading();
    void stopLoading();

}
