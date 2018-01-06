package projects.nyinyihtunlwin.foodplaces.viewitems;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.nyinyihtunlwin.foodplaces.R;

/**
 * Created by Dell on 1/6/2018.
 */

public class FoodPlaceImageSlideViewItem extends FrameLayout {
    @BindView(R.id.iv_image_slide)
    ImageView ivFoodPlaceImage;

    public FoodPlaceImageSlideViewItem(@NonNull Context context) {
        super(context);
    }

    public FoodPlaceImageSlideViewItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FoodPlaceImageSlideViewItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }

    public void setData(String imageUrl) {
    }
}
