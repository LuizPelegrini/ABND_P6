package com.example.android.abnd_p6;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ArticleAdapter extends ArrayAdapter<Article> {

    // ViewHolder design pattern to remove unnecessary findViewById calls
    private static class ViewHolder {
        private TextView titleTextView;
        private TextView sectionNameTextView;
        private TextView authorNameTextView;
        private TextView dateTextView;
    }

    ArticleAdapter(@NonNull Context context, @NonNull List<Article> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Article article = getItem(position);
        ViewHolder viewHolder;

        // If there is no view to be recycle, inflate a new one
        if(convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

            viewHolder.titleTextView = convertView.findViewById(R.id.title_text_view);

            convertView.setTag(viewHolder);
        }else{
            // Otherwise, just get the references previously fetched
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Show the data from the array to the list item view
        if(article != null) {
            viewHolder.titleTextView.setText(article.getTitle());
        }

        return convertView;
    }
}
