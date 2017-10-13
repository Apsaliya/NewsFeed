package com.newsfeed;

import android.content.Context;

import com.newsfeed.Models.NewsFeed;
import com.newsfeed.PresenterImpls.SearchPresenterImpl;
import com.newsfeed.Repos.SearchRepository;
import com.newsfeed.ViewInterfaces.SearchView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ankit on 13/10/17.
 */

public class SearchPresenterTest {

    private final String SEARCH_QUERY = "sports";

    @Mock
    SearchRepository searchRepository;

    @Mock
    SearchView searchView;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void attachView() {
        SearchPresenterImpl presenter = new SearchPresenterImpl(null, searchRepository);
        assertNull(presenter.getView());

        presenter.attachView(searchView);
        assertNotNull(presenter.getView());
    }

    @Test
    public void errorSearchTest() {
        SearchPresenterImpl presenter = new SearchPresenterImpl(searchView, searchRepository);
        Observable<NewsFeed> observable = Observable.error(new IOException());

        when(searchRepository.getNewsForQuery(Mockito.anyString(), Mockito.anyInt())).thenReturn(observable);

        presenter.getNewsForQuery(SEARCH_QUERY);

        waitFor(100);
        // this should return appropriate error msg
        verify(searchView, times(1)).onError(Mockito.<String>any());
    }

    @Test
    public void successSearchTest() {
        SearchPresenterImpl presenter = new SearchPresenterImpl(searchView, searchRepository);
        Observable<NewsFeed> observable = Observable.just(new NewsFeed());

        when(searchRepository.getNewsForQuery(Mockito.anyString(), Mockito.anyInt())).thenReturn(observable);

        presenter.getNewsForQuery(SEARCH_QUERY);
        waitFor(100);
        verify(searchView, times(1)).onNewsReceived(Mockito.any(NewsFeed.class));
    }

    /**
     * Wait for the specific time using {@link Thread#sleep(long)}
     *
     * @param milliseconds The time we want to wait for in millis
     */
    private void waitFor(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) {
            fail();
        }
    }
}
