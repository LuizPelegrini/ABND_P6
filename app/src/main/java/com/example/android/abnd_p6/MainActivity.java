package com.example.android.abnd_p6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.list_view);

        ArrayList<Article> articles = new ArrayList<>();
        articles.add(new Article("Test1", "Test1", "Test1","Test1", "Test1"));
        articles.add(new Article("Test2", "Test2", "Test2","Test2", "Test2"));
        articles.add(new Article("Test3", "Test3", "Test3","Test3", "Test3"));
        articles.add(new Article("Test4", "Test4", "Test4","Test4", "Test4"));
        articles.add(new Article("Test1", "Test1", "Test1","Test1", "Test1"));
        articles.add(new Article("Test2", "Test2", "Test2","Test2", "Test2"));
        articles.add(new Article("Test3", "Test3", "Test3","Test3", "Test3"));
        articles.add(new Article("Test4", "Test4", "Test4","Test4", "Test4"));
        articles.add(new Article("Test1", "Test1", "Test1","Test1", "Test1"));
        articles.add(new Article("Test2", "Test2", "Test2","Test2", "Test2"));
        articles.add(new Article("Test3", "Test3", "Test3","Test3", "Test3"));
        articles.add(new Article("Test4", "Test4", "Test4","Test4", "Test4"));

        ArticleAdapter articleAdapter = new ArticleAdapter(this, articles);
        listView.setAdapter(articleAdapter);
    }
}
