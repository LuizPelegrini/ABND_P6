package com.example.android.abnd_p6;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.net.URL;
import java.util.List;

public class ArticleLoader extends AsyncTaskLoader<List<Article>> {

    private List<Article> mData;
    private String mUrlString;

    public ArticleLoader(@NonNull Context context, String urlString) {
        super(context);
        mUrlString = urlString;
    }

    @Nullable
    @Override
    public List<Article> loadInBackground() {
        URL url = QueryUtils.createURL(mUrlString);

        if(url != null)
            return QueryUtils.fetchAsyncArticles(url);

        return null;
    }

    @Override
    protected void onStartLoading() {
        // First, try to deliver cached results if there is data
        if(mData != null)
            deliverResult(mData);
        else
            forceLoad();
    }

    @Override
    public void deliverResult(@Nullable List<Article> data) {
        mData = data;
        super.deliverResult(data);
    }
}
