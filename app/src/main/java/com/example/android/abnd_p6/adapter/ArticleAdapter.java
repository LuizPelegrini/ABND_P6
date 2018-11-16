package com.example.android.abnd_p6.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.abnd_p6.bean.Article;
import com.example.android.abnd_p6.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleAdapter extends ArrayAdapter<Article> {

    private Context mContext;                   // The context coming from the MainActivity
    private List<Article> mArticles;            // The list of articles used by this Adapter
    private SimpleDateFormat mDateFormatter;    // A simple date formatter

    // ViewHolder design pattern to remove unnecessary findViewById calls
    static class ViewHolder {
       @BindView(R.id.title_text_view) TextView titleTextView;
       @BindView(R.id.section_text_view) TextView sectionNameTextView;
       @BindView(R.id.author_text_view) TextView authorNameTextView;
       @BindView(R.id.date_text_view) TextView dateTextView;

       public ViewHolder(View view){
           // Assign all the references of this class' textViews by looking up at given view
           ButterKnife.bind(this, view);
       }
    }

    public ArticleAdapter(@NonNull Context context, @NonNull List<Article> articles) {
        super(context, 0, articles);
        this.mArticles = articles;
        this.mContext = context;
        this.mDateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Article article = getItem(position);
        ViewHolder viewHolder;

        // If there is no view to be recycle, inflate a new one
        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        }else{
            // Otherwise, just get the references previously fetched
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Show the data from the array to the list item view
        if(article != null) {
            viewHolder.titleTextView.setText(article.getTitle());

            // If there is no author's name, hides the author's TextView
            if(TextUtils.isEmpty(article.getAuthorName()))
                viewHolder.authorNameTextView.setVisibility(View.GONE);
            else {
                viewHolder.authorNameTextView.setText(mContext.getString(R.string.by_author_name, article.getAuthorName()));
                viewHolder.authorNameTextView.setVisibility(View.VISIBLE);
            }

            viewHolder.sectionNameTextView.setText(article.getSectionName());
            viewHolder.dateTextView.setText(mContext.getString(R.string.datetime_info, formatISODate(article.getDatePublished())));
        }

        return convertView;
    }

    public void setArticles(List<Article> articles)
    {
        mArticles.clear();
        mArticles.addAll(articles);
        notifyDataSetChanged();
    }

    // Format ISO 8601 date to be parsed as (dd MM yyyy)
    private String formatISODate(String dateTimeInfo){
        String dateTimeFormatted = "";

        try {
            Date date = mDateFormatter.parse(dateTimeInfo);
            SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
            dateTimeFormatted = formatter.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateTimeFormatted;
    }
}
