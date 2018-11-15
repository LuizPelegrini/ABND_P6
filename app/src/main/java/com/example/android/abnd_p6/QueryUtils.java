package com.example.android.abnd_p6;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class QueryUtils {

    private static final String URL_GUARDIAN =
            "https://content.guardianapis.com/search?section=games&from-date=2018-10-01&order-by=newest&show-tags=contributor&format=json&api-key=d755ecc5-4bfe-4905-ac10-523870d7e89c";

    public static URL createURL(){
        URL url = null;

        try {
            url = new URL(URL_GUARDIAN);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static ArrayList<Article> fetchAsyncArticles(URL url)
    {
        String jsonData = "";

        try{
            jsonData = makeHttpRequest(url);
        }catch(IOException e) {
            e.printStackTrace();
        }

        return parseJSON(jsonData);
    }

    private static String makeHttpRequest(URL url) throws IOException
    {
        String json = "";
        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.connect();

            // Check if the server responded successfully to the request
            if(connection.getResponseCode() == 200) {
                inputStream = connection.getInputStream();
                json = readInputStreamToString(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the resources if they were opened
            if(connection != null)
                connection.disconnect();
            if(inputStream != null)
                inputStream.close();
        }

        return json;
    }


    private static String readInputStreamToString(InputStream inputStream) throws IOException{
        StringBuilder stringBuilder = new StringBuilder();

        // Creates a buffer reader to read the stream line by line
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        // Read the stream
        while((line = bufferedReader.readLine()) != null){
            stringBuilder.append(line);
            stringBuilder.append('\n');
        }

        // Return the json as string
        return stringBuilder.toString();
    }

    private static ArrayList<Article> parseJSON(String jsonData)
    {
        if(TextUtils.isEmpty(jsonData))
            return null;

        ArrayList<Article> articles = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(jsonData);
            JSONArray results = root.getJSONObject("response").optJSONArray("results");

            if(results != null){
                for (int i = 0; i < results.length(); i++){
                    JSONObject current = results.getJSONObject(i);
                    String title = current.optString("webTitle");
                    JSONArray tags = current.optJSONArray("tags");
                    String authorName = "";

                    if(tags != null && tags.length() > 0){
                        authorName = tags.getJSONObject(0).optString("webTitle");
                    }

                    String url = current.optString("webUrl");
                    String sectionName = current.optString("sectionName");
                    String date = current.optString("webPublicationDate");

                    // Add to the articles list
                    articles.add(new Article(title, url, sectionName, authorName, date));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return articles;
    }

}
