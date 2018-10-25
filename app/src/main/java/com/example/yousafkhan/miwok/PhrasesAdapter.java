package com.example.yousafkhan.miwok;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PhrasesAdapter extends ArrayAdapter<Translation> {

    PhrasesAdapter(Context context, ArrayList<Translation> phrasesList) {
        super(context, 0, phrasesList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.listview_item, parent, false);

            holder = new ViewHolder();
            holder.englishTextView = convertView.findViewById(R.id.english_word);
            holder.miwokTextView = convertView.findViewById(R.id.miwok_word);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Translation translation = getItem(position);

        holder.englishTextView.setText(translation.getEnglishTranslation());
        holder.miwokTextView.setText(translation.getMiwokTranslation());

        // hide the imageview in phrases listview item layout
        convertView.findViewById(R.id.image_icon).setVisibility(View.INVISIBLE);

        return convertView;
    }

    private static class ViewHolder {
        private TextView englishTextView;
        private TextView miwokTextView;
    }
}
