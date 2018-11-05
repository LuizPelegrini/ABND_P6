package com.example.android.abnd_p6;

public class Article {

    private String mTitle;
    private String mUrl;
    private String mSectionName;
    private String mAuthorName;
    private String mDatePublished;

    public Article (String title, String url, String sectionName, String authorName, String datePublished){
        mTitle = title;
        mUrl = url;
        mSectionName = sectionName;
        mAuthorName = authorName;
        mDatePublished = datePublished;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getSectionName() {
        return mSectionName;
    }

    public String getAuthorName() {
        return mAuthorName;
    }

    public String getDatePublished() {
        return mDatePublished;
    }
}
