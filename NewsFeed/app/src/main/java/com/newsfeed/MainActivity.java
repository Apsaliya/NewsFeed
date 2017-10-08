package com.newsfeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.newsfeed.Models.NewsFeed;
import com.newsfeed.PresenterImpls.SearchPresenterImpl;
import com.newsfeed.ViewInterfaces.SearchView;

public class MainActivity extends AppCompatActivity implements SearchView {
    private SearchPresenterImpl presenter;
    private EditText searchEditText;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initPresenter();
    }

    private void initViews() {
        searchEditText = (EditText) findViewById(R.id.search_view);
        recyclerView = (RecyclerView) findViewById(R.id.search_results_recycler_view);

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

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                int visibleItemCount = linearLayoutManager.getChildCount();
                int lastVisibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();

                if (lastVisibleItemPosition >= visibleItemCount) {
                    // not last page, same query
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    private void initPresenter() {
        presenter = new SearchPresenterImpl();
    }

    private void initSearch(String searchText) {
        presenter.getNewsForQuery(searchText);
    }

    @Override
    public void onNewsReceived(NewsFeed newsFeed) {

    }

    @Override
    public void onLastPageReached() {

    }
}
