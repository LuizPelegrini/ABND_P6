package com.example.android.abnd_p6;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ArticleAdapter extends ArrayAdapter<Article> {

    private Context mContext;   // The context coming from the MainActivity
    private List<Article> mArticles;

    // ViewHolder design pattern to remove unnecessary findViewById calls
    private static class ViewHolder {
        private TextView titleTextView;
        private TextView sectionNameTextView;
        private TextView authorNameTextView;
        private TextView dateTextView;
    }

    ArticleAdapter(@NonNull Context context, @NonNull List<Article> articles) {
        super(context, 0, articles);
        this.mArticles = articles;
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Article article = getItem(position);
        ViewHolder viewHolder;

        // If there is no view to be recycle, inflate a new one
        if(convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);

            viewHolder.titleTextView = convertView.findViewById(R.id.title_text_view);
            viewHolder.authorNameTextView = convertView.findViewById(R.id.author_text_view);
            viewHolder.sectionNameTextView = convertView.findViewById(R.id.section_text_view);
            viewHolder.dateTextView = convertView.findViewById(R.id.date_text_view);

            convertView.setTag(viewHolder);
        }else{
            // Otherwise, just get the references previously fetched
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Show the data from the array to the list item view
        if(article != null) {
            viewHolder.titleTextView.setText(article.getTitle());

            // If there is no author's name, hides the author's textview
            if(TextUtils.isEmpty(article.getAuthorName()))
                viewHolder.authorNameTextView.setVisibility(View.GONE);
            else {
                viewHolder.authorNameTextView.setText(mContext.getString(R.string.by_author_name, article.getAuthorName()));
                viewHolder.authorNameTextView.setVisibility(View.VISIBLE);
            }

            viewHolder.sectionNameTextView.setText(article.getSectionName());
            viewHolder.dateTextView.setText(article.getDatePublished());
        }

        return convertView;
    }

    public void setArticles(List<Article> articles)
    {
        mArticles.clear();
        mArticles.addAll(articles);
        notifyDataSetChanged();
    }
}
