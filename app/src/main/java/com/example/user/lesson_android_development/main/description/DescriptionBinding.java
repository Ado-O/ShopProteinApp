package com.example.user.lesson_android_development.main.description;

import android.databinding.BindingAdapter;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.lesson_android_development.R;
import com.example.user.lesson_android_development.data.ProductDescription;

import java.util.List;

public class DescriptionBinding {

    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:desc"})
    public static void setLayout(LinearLayout linearLayout, List desc) {

        linearLayout.removeAllViews();

        if (desc != null) {
            LayoutInflater inflater = LayoutInflater.from(linearLayout.getContext());
            for (ProductDescription p : ((List<ProductDescription>) desc)) {

                TextView view = (TextView) inflater.inflate(
                        R.layout.description, linearLayout, false
                );


                view.setText(p.getName());
                linearLayout.addView(view);
            }
        }

    }
}
