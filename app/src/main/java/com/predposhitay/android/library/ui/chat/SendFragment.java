package com.predposhitay.android.library.ui.chat;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.predposhitay.android.library.R;
import com.predposhitay.android.library.model.Message;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SendFragment extends Fragment implements backMVP, ChatMVP{
    @BindView(R.id.send_message)
    ImageView sendMessage;
    @BindView(R.id.text_message)
    TextView text;
    @BindView(R.id.message_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton fab;
    @BindView(R.id.send_text)
    View sendText;
    @BindView(R.id.back)
    ImageView back;

    View nav;
    ChatAdapter adapter;

    ChatPresentr presentr;

    SharedPreferences message;
    SharedPreferences user;
    LinearLayoutManager linearLayoutManager;

    ProgressDialog loading;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chat_item, container, false);
        ButterKnife.bind(this, view);

        loading = new ProgressDialog(getActivity());
        loading.setMessage("Загрузка сообщений");
        loading.setIndeterminate(true);
        loading.setCancelable(false);
        nav = getActivity().findViewById(R.id.navigation);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(adapter);
        List<Message> mass = new ArrayList<>();
        presentr = new ChatPresentr(this);
        message = getActivity().getSharedPreferences("message", Context.MODE_PRIVATE);
        user = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);


        presentr.getMessage(message.getString("last","99999999"),"-1","20");
        linearLayoutManager.setStackFromEnd(true);

        adapter = new ChatAdapter(mass, message, user.getString("id", "-1"), presentr);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.getAdapter().notifyDataSetChanged();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendText.setVisibility(View.VISIBLE);

                fab.setVisibility(View.GONE);
                nav.setVisibility(View.GONE);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendText.setVisibility(View.INVISIBLE);
                fab.setVisibility(View.VISIBLE);
                nav.setVisibility(View.VISIBLE);

            }
        });

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = text.getText().toString();
                input = input.replace("\n\n","").replace("   ","").replace("  "," ");
                if(input.length()==0){
                    Toast.makeText(getActivity(),"Введите текст сообщения", Toast.LENGTH_SHORT).show();
                }
                else {
                    loading.setMessage("Отправка сообщения");
                    presentr.sendMessage(input,user.getString("id","-1"));
                }
            }
        });
        return view;
    }

    @Override
    public void backpress() {
        sendText.setVisibility(View.INVISIBLE);
        fab.setVisibility(View.VISIBLE);
        nav.setVisibility(View.VISIBLE);
    }

    @Override
    public void getMssage(List<Message> messages) {
        adapter.addMessage(messages);
        mRecyclerView.getAdapter().notifyDataSetChanged();
       // linearLayoutManager.setStackFromEnd(false);
    }

    @Override
    public void isEmty() {
        Toast.makeText(getActivity(),"Сообщений нет", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(),error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getMessageUp(List<Message> messages) {
        adapter.addMessageUp(messages);
        mRecyclerView.getAdapter().notifyDataSetChanged();

    }

    @Override
    public void sendMessage() {
        text.setText("");
        Log.e("Send Message", message.getString("last","i"));
        presentr.getMessage(message.getString("last","99999999"),"1","1");
        linearLayoutManager.setStackFromEnd(true);
        mRecyclerView.smoothScrollToPosition(mRecyclerView.getAdapter().getItemCount() - 1);
//        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void startLoading() {
        loading.show();
    }

    @Override
    public void stopLoading() {
        loading.setMessage("Загрузка сообщений");
        if(loading.isShowing())
            loading.dismiss();
    }
}
