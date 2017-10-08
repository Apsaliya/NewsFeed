package com.newsfeed.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.newsfeed.Models.NewsItem;
import com.newsfeed.PresenterImpls.SearchPresenterImpl;
import com.newsfeed.R;

import java.util.ArrayList;

/**
 * Created by ankit on 08/10/17.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.GenericViewHolder>  {

    private final int DATA_PLANK = 0;
    private final int LOAD_MORE = 1;
    private boolean isLastPage;
    private ArrayList<NewsItem> newsItems;
    private SearchPresenterImpl presenter;
    private Context context;

    public SearchAdapter (Context context, ArrayList<NewsItem> newsItems, SearchPresenterImpl presenter) {
        this.newsItems = newsItems;
        this.presenter = presenter;
        this.context = context;
    }

    public void updateLastPageStatus(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (DATA_PLANK == viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_plank, parent, false);
            return new NewItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.load_more_view, parent, false);
            return new LoadMoreHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        holder.bindView(position);
    }


    @Override
    public int getItemCount() {
        if (isLastPage) {
            return newsItems.size();
        } else {
            return newsItems.size() +1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == newsItems.size()) {
            return LOAD_MORE;
        }
        return DATA_PLANK;
    }

    public static abstract class GenericViewHolder extends RecyclerView.ViewHolder {

        public GenericViewHolder(View rowView) {
            super(rowView);
        }

        protected abstract void bindView(int position);
    }

    public class NewItemViewHolder extends GenericViewHolder {
        TextView title, author;
        public NewItemViewHolder(View rowView) {
            super(rowView);
            title = (TextView) rowView.findViewById(R.id.title);
            author = (TextView) rowView.findViewById(R.id.author);
        }

        @Override
        protected void bindView(int position) {
            final NewsItem newsItem = newsItems.get(position);
            title.setText(newsItem.getTitle());
            author.setText("By " + newsItem.getAuthor());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsItem.getUrl()));
                    context.startActivity(intent);
                }
            });
        }
    }

    public class LoadMoreHolder extends GenericViewHolder {
        public LoadMoreHolder(View rowView) {
            super(rowView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.fetchMoreNews();
                }
            });

        }

        @Override
        protected void bindView(int position) {
            // no-op
        }
    }

}
