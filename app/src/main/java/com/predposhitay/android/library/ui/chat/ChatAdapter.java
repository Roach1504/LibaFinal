package com.predposhitay.android.library.ui.chat;


import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.predposhitay.android.library.R;
import com.predposhitay.android.library.model.Message;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatAdapterViewHolder>{
    List<Message> messags = new ArrayList<>();
    SharedPreferences message;
    String id;
    ChatPresentr presentr;
    int pag = 20;
    public ChatAdapter(List<Message> messags, SharedPreferences message, String id, ChatPresentr presentr){
        this.messags = messags;
        this.message = message;
        this.id = id;
        this.presentr = presentr;
    }

    void addMessage(List<Message> list){
        for(Message i : list){
            this.messags.add(i);
        }
        message.edit().putString("last", messags.get(messags.size()-1).getId()).commit();
    }
    void addMessageUp(List<Message> list) {
        for (Message i : list) {
            this.messags.add(0, i);
        }
        message.edit().putString("up", messags.get(0).getId()).commit();
        message.edit().putString("last", messags.get(messags.size()-1).getId()).commit();
        Log.e("ID_UP", messags.get(0).getId());
        Log.e("ID_LAST", messags.get(messags.size()-1).getId());

    }

    @Override
    public ChatAdapter.ChatAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView  = LayoutInflater.from(parent.getContext()).inflate(R.layout.messag_item_right, parent, false);;
        switch (viewType)
        {
            case 0:
                itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.messag_item_right, parent, false);
                break;
            case 1:
                itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item_left, parent, false);
                break;
        }
        return new ChatAdapterViewHolder(itemLayoutView);
    }

    @Override
    public int getItemViewType(int position) {
        if (id.equals(messags.get(position).getAuthorId()))
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }

    @Override
    public void onBindViewHolder(ChatAdapter.ChatAdapterViewHolder holder, int position) {
        Message itemMessage= messags.get(position);
        holder.date.setText(itemMessage.getDate());
        holder.text.setText(itemMessage.getText());
        holder.login.setText(itemMessage.getNickname());
        if(message.getString("up","-1").equals(itemMessage.getId())){
            presentr.getMessage(message.getString("up","-1"),"-1","20");
        }
        if(message.getString("last","-1").equals(itemMessage.getId())) {
            presentr.getMessage(message.getString("last","14"),"1","20");
        }
    }

    @Override
    public int getItemCount() {
        return messags.size();
    }

    public class ChatAdapterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_messag)
        TextView text;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.login)
        TextView login;

        public ChatAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
