package com.example.yousafkhan.miwok;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FamilyAdapter extends ArrayAdapter<Translation> {

    FamilyAdapter(Context context, ArrayList<Translation> familyList) {
        super(context, 0, familyList);
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

        return convertView;
    }

    static class ViewHolder {
        private TextView englishTextView;
        private TextView miwokTextView;
    }
}
