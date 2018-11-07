package com.example.android.abnd_p6;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Article>>{

    private static final String TAG = MainActivity.class.getSimpleName();

    ArticleAdapter mArticleAdapter;
    ConnectivityManager.NetworkCallback mNetworkCallback;
    ConnectivityManager mConnectivityManager;
    ProgressBar mProgressBar;
    TextView mInfoTextView;
    boolean mIsNetworkAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Retrieves connectivity manager and gets view references
        mConnectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        ListView listView = findViewById(R.id.list_view);
        mProgressBar = findViewById(R.id.loading_spinner);
        mInfoTextView = findViewById(R.id.info_text_view);
        mInfoTextView.setVisibility(View.INVISIBLE);

        // Init the Loader
        getSupportLoaderManager().initLoader(0, null, this);

        // Creates the adapter for the list view
        mArticleAdapter = new ArticleAdapter(this, new ArrayList<Article>());
        listView.setAdapter(mArticleAdapter);

        // Using a non-deprecated approach to listen to network status changes
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mNetworkCallback = new ConnectivityManager.NetworkCallback() {
                @Override
                public void onAvailable(Network network) {
                    super.onAvailable(network);
                    mIsNetworkAvailable = true;
                }

                @Override
                public void onLost(Network network) {
                    super.onLost(network);
                    mIsNetworkAvailable = false;
                }

                @Override
                public void onUnavailable() {
                    super.onUnavailable();
                    mIsNetworkAvailable = false;
                }
            };

            // Registers this NetworkCallback object to the ConnectivityManager
            mConnectivityManager.registerDefaultNetworkCallback(mNetworkCallback);
        }
    }

    @NonNull
    @Override
    public Loader<List<Article>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new ArticleLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Article>> loader, List<Article> articles) {
        NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
        mProgressBar.setVisibility(View.GONE);

        // For API level 24 and above
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            // If the network is available...
            if(mIsNetworkAvailable){
                // ...but there is NO data to be shown, then show the info text
                if(articles == null || articles.isEmpty()){
                    mInfoTextView.setText(getString(R.string.no_articles));
                    mInfoTextView.setVisibility(View.VISIBLE);
                }
                else {
                    // Otherwise, show the list of articles
                    mArticleAdapter.setArticles(articles);
                }
            } else {
                mInfoTextView.setText(getString(R.string.no_network));
                mInfoTextView.setVisibility(View.VISIBLE);
            }
        }



        // For API level 23 and below
        else {
            // networkInfo.isConnectedOrConnecting() is deprecated, so I used isConnected() only
            if (networkInfo == null || !networkInfo.isConnected()){
                mInfoTextView.setText(getString(R.string.no_network));
                mInfoTextView.setVisibility(View.VISIBLE);
            } else {
                if(articles == null || articles.isEmpty()){
                    mInfoTextView.setText(getString(R.string.no_articles));
                    mInfoTextView.setVisibility(View.VISIBLE);
                } else {
                    mArticleAdapter.setArticles(articles);
                }
            }
        }
    }


    @Override
    public void onLoaderReset(@NonNull Loader loader) {

    }
}
