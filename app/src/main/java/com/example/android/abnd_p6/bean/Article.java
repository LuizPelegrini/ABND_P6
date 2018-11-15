package com.example.android.abnd_p6.bean;

public class Article {

    private String mTitle;          // The title of the article
    private String mUrl;            // The url to the article
    private String mSectionName;    // The section this article belongs to
    private String mAuthorName;     // The author who wrote/published this article
    private String mDatePublished;  // The date this article was published

    public Article (String title, String url, String sectionName, String authorName, String datePublished){
        mTitle = title;
        mUrl = url;
        mSectionName = sectionName;
        mAuthorName = authorName;
        mDatePublished = datePublished;
    }

    ///// GETTERS /////
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
