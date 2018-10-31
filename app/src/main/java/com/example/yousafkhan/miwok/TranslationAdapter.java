package com.example.yousafkhan.miwok;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class TranslationAdapter extends ArrayAdapter<Translation> {

    TranslationAdapter(Context context, ArrayList<Translation> colorsLiist) {
        super(context, 0, colorsLiist);
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
            holder.imageResource = convertView.findViewById(R.id.image_icon);
            holder.parentLayout = convertView.findViewById(R.id.listview_item_layout);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Translation translation = getItem(position);

        int imageResourceId = translation.getImageResourceID();

        // check if translation instance has image resource or not
        if(imageResourceId != -1) {
            holder.imageResource.setImageResource(translation.getImageResourceID());
        } else {
            holder.imageResource.setVisibility(View.INVISIBLE);

            // set the text views to the left of parent layout as image is not available
            // applies only to phrases activity
            ConstraintSet constraints = new ConstraintSet();
            constraints.clone(holder.parentLayout);
            constraints.connect(R.id.translation_container, ConstraintSet.LEFT, R.id.listview_item_layout, ConstraintSet.LEFT, 0);
            constraints.applyTo(holder.parentLayout);
        }

        holder.englishTextView.setText(translation.getEnglishTranslation());
        holder.miwokTextView.setText(translation.getMiwokTranslation());

        // set view's background color
        convertView.setBackgroundResource(translation.getBackgroundColor());

        return convertView;
    }

    private static class ViewHolder {
        private TextView englishTextView;
        private TextView miwokTextView;
        private ImageView imageResource;

        // needed for phrases activity
        private ConstraintLayout parentLayout;
    }
}
