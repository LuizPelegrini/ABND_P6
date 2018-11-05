package com.example.android.abnd_p6;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class QueryUtils {

    private static final String URL_GUARDIAN = "";

    public static URL createURL(){
        URL url = null;

        try {
            url = new URL(URL_GUARDIAN);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static ArrayList<Article> parseJSON(String jsonData)
    {
        if(TextUtils.isEmpty(jsonData))
            return null;

        ArrayList<Article> articles = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(jsonData);
            JSONArray results = root.getJSONObject("response").optJSONArray("results");

            if(results != null){
                for (int i = 0; i < results.length(); i++) {
                    String title;
                    String authorName;

                    String webTitle = results.getJSONObject(i).optString("webTitle");
                    String[] titleAndPossibleAuthor = getTitle(webTitle);

                    // If the titleAndPossibleAuthor field already contains the author's name, just grab it
                    // Otherwise, a deeper search is necessary
                    if(titleAndPossibleAuthor != null){
                        title = titleAndPossibleAuthor[0];
                        authorName = titleAndPossibleAuthor[1];
                    } else {
                        title = webTitle;
                        authorName = getAuthorName(results.getJSONObject(i));
                    }

                    String url = results.getJSONObject(i).optString("webUrl");
                    String sectionName = results.getJSONObject(i).optString("sectionName");
                    String date = results.getJSONObject(i).optString("webPublicationDate");

                    // Add to the articles list
                    articles.add(new Article(title, url, sectionName, authorName, date));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return articles;
    }

    private static String getAuthorName(JSONObject jsonObject){
        // TODO: if the title already contains the author name, take it. Otherwise, get deeper and retrieve contributor's name
        return "";
    }

    // Returns the title of the article. It may also return the author's name if it is on the title's string
    private static String[] getTitle(String title)
    {
        if(title.contains("|")){
            return title.split(" \\| ");
        }

        return null;
    }

}
