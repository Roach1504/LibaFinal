package com.predposhitay.android.library.ui.news;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.predposhitay.android.library.R;
import com.predposhitay.android.library.model.NewsModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsAdapterViewHolder> {

    List<NewsModel> news = new ArrayList<>();
    int page=1;
    NewsPresentr presentr;
    String id_categ;

    public NewsAdapter(List<NewsModel> news, NewsPresentr presentr, String id_categ){
        this.news = news;
        this.presentr = presentr;
        this.id_categ = id_categ;
    }

    public void addNews(List<NewsModel> news){
        this.news.addAll(news);
        page++;
    }

    @Override
    public NewsAdapter.NewsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_item, parent, false);
        return new NewsAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.NewsAdapterViewHolder holder, final int position) {

        holder.title.setText(news.get(position).getTitle());
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       // String data = dateFormat.format(new Date());
        holder.date.setText(news.get(position).getDate());
        holder.shorts.setText(news.get(position).getShorts());
        if(position == news.size()-4){
            Log.e("API", "page");
            presentr.loadNews(id_categ,page+"");
        }

        presentr.loadImage(holder.image, news.get(position).getImage());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("HTML one", news.get(position).getText());
                presentr.startNewsfull(news.get(position).getText());
            }
        });
//        Picasso.with().load(("http://9834436605.myjino.ru/" + s).replace(" ", ""))
//                .placeholder(R.drawable.ic_image_amber_500_24dp)
//                .into(imageView);

//        holder.image.setImageResource(R.drawable.avd_hide_password_1);
       // holder.image.setImageResource(R.drawable.im);

    }



    @Override
    public int getItemCount() {
        return news.size();
    }

    public class NewsAdapterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.shorts)
        TextView shorts;
        @BindView(R.id.data)
        TextView date;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.item_people)
        View view;

        public NewsAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
