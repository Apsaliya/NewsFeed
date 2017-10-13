package com.newsfeed.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.newsfeed.Adapters.SearchAdapter;
import com.newsfeed.Models.NewsFeed;
import com.newsfeed.Models.NewsItem;
import com.newsfeed.PresenterImpls.SearchPresenterImpl;
import com.newsfeed.R;
import com.newsfeed.Repos.SearchRepository;
import com.newsfeed.ViewInterfaces.SearchView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements SearchView {
    private final String TAG = SearchActivity.class.getSimpleName();
    private SearchPresenterImpl presenter;
    private EditText searchEditText;
    private RecyclerView recyclerView;
    private boolean isLastPageReached = false;
    private ArrayList<NewsItem> newsItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPresenter();
        initViews();
    }

    private void initViews() {
        searchEditText = (EditText) findViewById(R.id.search_view);
        recyclerView = (RecyclerView) findViewById(R.id.search_results_recycler_view);
        initSearch("");
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence != null) {
                    initSearch(charSequence.toString());
                } else {
                    initSearch("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void initPresenter() {
        presenter = new SearchPresenterImpl(this, new SearchRepository());
    }

    private void initSearch(String searchText) {
        presenter.getNewsForQuery(searchText);
    }

    @Override
    public void onMoreNews(NewsFeed newsFeed) {
        newsItems.addAll(newsFeed.getNewsItems());
        updateAdapter();
    }

    @Override
    public void onNewsReceived(NewsFeed newsFeed) {
        newsItems.clear();
        newsItems.addAll(newsFeed.getNewsItems());
        updateAdapter();
    }

    private void updateAdapter() {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            ((SearchAdapter) adapter).updateLastPageStatus(isLastPageReached);
            adapter.notifyDataSetChanged();
        } else {
            SearchAdapter searchAdapter = new SearchAdapter(this, newsItems, presenter);
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(searchAdapter);
        }
    }

    @Override
    public void onLastPageReached() {
        isLastPageReached = true;
    }

    @Override
    public void onError(String errorMsg) {
        //update your error view
    }
}
