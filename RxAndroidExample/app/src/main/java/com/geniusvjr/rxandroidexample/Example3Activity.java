package com.geniusvjr.rxandroidexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.Callable;

import rx.Scheduler;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dream on 16/4/13.
 */
public class Example3Activity extends AppCompatActivity{

    private Subscription mTvShowSubscription;
    private RecyclerView mTvShowListView;
    private ProgressBar mProgressBar;
    private TextView mErrorMessage;
    private SimpleStringAdapter mSimpleStringAdapter;
    private RestClient mResClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mResClient = new RestClient(this);
        configureLayout();
        createSingle();
    }

    private void createSingle() {
        final Single<List<String>> tvShowSingle = Single.fromCallable(new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                return mResClient.getFavoriteTvShows();
            }
        });

        mTvShowSubscription = tvShowSingle
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<List<String>>() {
                    @Override
                    public void onSuccess(List<String> value) {
                        displayTvShows(value);
                    }

                    @Override
                    public void onError(Throwable error) {
                        displayErrorMessage();
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mTvShowSubscription != null && !mTvShowSubscription.isUnsubscribed())
        {
            mTvShowSubscription.unsubscribe();
        }
    }

    private void displayTvShows(List<String> tvShows)
    {
        mSimpleStringAdapter.setString(tvShows);
        mProgressBar.setVisibility(View.GONE);
        mTvShowListView.setVisibility(View.VISIBLE);
    }

    private void displayErrorMessage()
    {
        mProgressBar.setVisibility(View.GONE);
        mErrorMessage.setVisibility(View.VISIBLE);
    }

    private void configureLayout() {
        setContentView(R.layout.activity_example_3);
        mErrorMessage = (TextView) findViewById(R.id.error_message);
        mProgressBar = (ProgressBar) findViewById(R.id.loader);
        mTvShowListView = (RecyclerView) findViewById(R.id.tv_show_list);
        mTvShowListView.setLayoutManager(new LinearLayoutManager(this));
        mSimpleStringAdapter = new SimpleStringAdapter(this);
        mTvShowListView.setAdapter(mSimpleStringAdapter);
    }
}
