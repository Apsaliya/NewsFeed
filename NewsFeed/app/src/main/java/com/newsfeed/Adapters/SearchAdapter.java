package com.newsfeed.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.newsfeed.Models.NewsItem;
import com.newsfeed.R;

import java.util.ArrayList;

/**
 * Created by ankit on 08/10/17.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.GenericViewHolder>  {

    ArrayList<NewsItem> newsItems;

    public SearchAdapter (ArrayList<NewsItem> newsItems) {
        this.newsItems = newsItems;
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_plank, parent, false);
        return new NewItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }


    public static abstract class GenericViewHolder extends RecyclerView.ViewHolder {

        public GenericViewHolder(View rowView) {
            super(rowView);
        }

        protected abstract void bindView(int position);
    }

    public class NewItemViewHolder extends GenericViewHolder {
        TextView title, author, time;
        public NewItemViewHolder(View rowView) {
            super(rowView);
            title = (TextView) rowView.findViewById(R.id.title);
            author = (TextView) rowView.findViewById(R.id.author);
            time  = (TextView) rowView.findViewById(R.id.created_at);
        }

        @Override
        protected void bindView(int position) {
            NewsItem newsItem = newsItems.get(position);
            title.setText(newsItem.getTitle());
            author.setText(newsItem.getAuthor());
            time.setText(newsItem.getCreated_at());
        }
    }

}
