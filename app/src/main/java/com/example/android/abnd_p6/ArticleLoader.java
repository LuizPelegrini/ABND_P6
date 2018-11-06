package com.example.android.abnd_p6;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.net.URL;
import java.util.List;

public class ArticleLoader extends AsyncTaskLoader<List<Article>> {

    public ArticleLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public List<Article> loadInBackground() {
        URL url = QueryUtils.createURL();

        if(url != null)
            return QueryUtils.fetchAsyncArticles(url);

        return null;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }


}
