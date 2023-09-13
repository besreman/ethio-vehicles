package com.minalu.vehicles.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.minalu.vehicles.R;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ImagePagerAdapter extends PagerAdapter {

    private List<Integer> imageList;
    private Context context;

    public ImagePagerAdapter(Context context,List<Integer> imageList){
        this.imageList = imageList;
        this.context=context;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.image_item, container, false);

        ImageView imageView =view.findViewById(R.id.imageItem);
        imageView.setImageResource(imageList.get(position));

        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
