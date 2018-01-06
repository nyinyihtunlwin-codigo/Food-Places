package projects.nyinyihtunlwin.foodplaces.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import projects.nyinyihtunlwin.foodplaces.R;
import projects.nyinyihtunlwin.foodplaces.viewitems.FoodPlaceImageSlideViewItem;

/**
 * Created by Dell on 1/6/2018.
 */

public class FoodPlacesImagesPagerAdapter extends PagerAdapter {

    private LayoutInflater layoutInflater;
    private List<String> mImages;

    public FoodPlacesImagesPagerAdapter(Context context) {
        super();
        layoutInflater = LayoutInflater.from(context);
        mImages = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        FoodPlaceImageSlideViewItem view = (FoodPlaceImageSlideViewItem) layoutInflater.inflate(R.layout.view_item_food_place_image_slide, container, false);
        container.addView(view);  // attach created view to container

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void setImages(List<String> images) {
        this.mImages = images;
        notifyDataSetChanged();
    }
}
