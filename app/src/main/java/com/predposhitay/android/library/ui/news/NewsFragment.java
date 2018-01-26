package com.predposhitay.android.library.ui.news;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.predposhitay.android.library.R;
import com.predposhitay.android.library.model.NewsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsFragment extends Fragment implements NewsMVP{
    RecyclerView recyclerView;
    NewsAdapter adapter;
    ArrayList<NewsModel> newsModels;
    NewsPresentr newsPresentr;
    ProgressDialog progressDialog;
    String id_categ = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Загрузка новостей");
        progressDialog.setCancelable(false);
        savedInstanceState = getArguments();
        if(savedInstanceState!=null) {
            id_categ = savedInstanceState.getString("read");
        }
        newsPresentr = new NewsPresentr(this);
        newsModels = new ArrayList<>();
        if(id_categ == null){
            newsPresentr.loadNews("2","1");
            adapter = new NewsAdapter(newsModels, newsPresentr, "2");
        }
        else {
            newsPresentr.loadNews("63","1");
            adapter = new NewsAdapter(newsModels, newsPresentr, "63");
        }

        recyclerView.setAdapter(adapter);
        recyclerView.getAdapter().notifyDataSetChanged();
        return recyclerView;
    }

    @Override
    public void loadNews(ArrayList<NewsModel> newsModels) {
        adapter.addNews(newsModels);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startLoad() {
        progressDialog.show();
        Log.e("progres", "start");
    }

    @Override
    public void stopLoad() {
        Log.e("progres", "stop_main");
        if(progressDialog.isShowing()) {
            progressDialog.dismiss();
            Log.e("progres", "stop");
        }
    }

    @Override
    public void startFullNew(String html) {
        android.app.FragmentManager fragmentManager = getFragmentManager();
        Fragment youFragment;
            youFragment = new PredreadFragment();
            Bundle bundle = new Bundle();
            bundle.putString("html", html);
            youFragment.setArguments(bundle);
            fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                    .replace(R.id.content, youFragment)
                    .addToBackStack("myStack")
                    .commit();

    }

    @Override
    public void imageLoad(ImageView imag, String url) {
        Picasso.with(getActivity()).load(url)
                .fit()
                .centerInside()
                .into(imag);
    }
}
