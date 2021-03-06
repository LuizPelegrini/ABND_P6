package com.example.android.abnd_p6.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.abnd_p6.R;
import com.example.android.abnd_p6.adapter.ArticleAdapter;
import com.example.android.abnd_p6.bean.Article;
import com.example.android.abnd_p6.loader.ArticleLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Article>>{

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String URL_GUARDIAN = "https://content.guardianapis.com/search?show-tags=contributor&api-key=d755ecc5-4bfe-4905-ac10-523870d7e89c";

    private ArticleAdapter mArticleAdapter;                         // The adapter that will fill the list
    private ConnectivityManager mConnectivityManager;               // The connectivity manager
    @BindView(R.id.loading_spinner) ProgressBar mProgressBar;       // The progress bar view that is shown during loading
    @BindView(R.id.info_text_view) TextView mInfoTextView;          // The text view that appears in case no data or no network is found
    @BindView(R.id.list_view) ListView listView;                    // The ListView responsible to show the list of articles
    private boolean mIsNetworkAvailable;                            // Control variable that says if the network is available or not

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind the views using ButterKnife library
        ButterKnife.bind(this);

        // Retrieves connectivity manager and gets view references
        mConnectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        mInfoTextView.setVisibility(View.INVISIBLE);

        // Init the Loader
        getSupportLoaderManager().initLoader(0, null, this);

        // Creates the adapter for the list view
        mArticleAdapter = new ArticleAdapter(this, new ArrayList<Article>());
        listView.setAdapter(mArticleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Gets the article
                Article article = mArticleAdapter.getItem(i);

                // Creates an intent, put some data on it, and toss the intent to a browser
                // if there is one installed
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(article.getUrl()));
                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }
            }
        });

        ListenToNetworkChanges();
    }

    @NonNull
    @Override
    public Loader<List<Article>> onCreateLoader(int i, @Nullable Bundle bundle) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Get the saved preference for the topic
        String topic = sharedPreferences.getString(
                getString(R.string.settings_topic_key),
                getString(R.string.settings_topic_default_value));

        // Get the saved preference for the order
        String orderBy = sharedPreferences.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default_value));

        // Builds the url using a Builder and the preferences provided by the user
        Uri.Builder uriBuilder = Uri.parse(URL_GUARDIAN).buildUpon();
        uriBuilder.appendQueryParameter("format", "json");
        uriBuilder.appendQueryParameter("section", topic);
        uriBuilder.appendQueryParameter("order-by", orderBy);

        return new ArticleLoader(this, uriBuilder.toString());
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

    // When the activity is destroyed (making the data unavailable), this method is called
    @Override
    public void onLoaderReset(@NonNull Loader loader) {
        // Reset data
        mArticleAdapter.setArticles(new ArrayList<Article>());
    }

    // Register callbacks to start listening to network changes
    private void ListenToNetworkChanges()
    {
        // Using a non-deprecated approach to listen to network status changes
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // Contains the callbacks responsible to listen to network changes
            ConnectivityManager.NetworkCallback mNetworkCallback = new ConnectivityManager.NetworkCallback() {
                @Override
                public void onAvailable(Network network) {
                    super.onAvailable(network);
                    mIsNetworkAvailable = true;
// ***** Tried to call loadInBackground() here but android says this method must be called from the main thread ****
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_settings){
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
