package com.newsfeed.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ankit on 08/10/17.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.GenericViewHolder>  {


    public SearchAdapter () {

    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public static abstract class GenericViewHolder extends RecyclerView.ViewHolder {

        public GenericViewHolder(View rowView) {
            super(rowView);
        }

        protected abstract void bindView(int position);
    }

    public class ViewHolder extends GenericViewHolder {
        public ViewHolder(View rowView) {
            super(rowView);

        }

        @Override
        protected void bindView(int position) {
            // filldata
        }
    }

}
