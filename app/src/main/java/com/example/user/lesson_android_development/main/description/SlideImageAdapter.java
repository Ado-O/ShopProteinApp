package com.example.user.lesson_android_development.main.description;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.user.lesson_android_development.R;
import com.example.user.lesson_android_development.data.ProductImage;
import java.util.List;

public class SlideImageAdapter extends PagerAdapter {

    private List<ProductImage> mList;
    private LayoutInflater mInflater;
    private Context mContext;

    public SlideImageAdapter(Context context, List<ProductImage> list) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mList = list;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = mInflater.inflate(R.layout.slide_image, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);

        //Glide
        Glide.with(mContext)
                .load(mList.get(position).getPictures())
                .into(imageView);


        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
