package projects.nyinyihtunlwin.foodplaces.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.nyinyihtunlwin.foodplaces.R;
import projects.nyinyihtunlwin.foodplaces.data.vo.PromotionVO;

/**
 * Created by Dell on 1/6/2018.
 */

public class PromotionsViewHolder extends BaseViewHolder<PromotionVO> {

    @BindView(R.id.tv_promotion_title)
    TextView tvPromotionTitle;

    @BindView(R.id.tv_exclusive)
    TextView tvExclusive;

    @BindView(R.id.tv_promotion_until)
    TextView tvPromotionUntil;

    @BindView(R.id.iv_promotion_image)
    ImageView ivPromotionImage;

    @BindView(R.id.tv_promotion_shop_name)
    TextView tvPromotionShopName;

    @BindView(R.id.tv_promotion_shop_area)
    TextView tvPromotionShopArea;

    PromotionVO mData;


    public PromotionsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(PromotionVO mData) {
        this.mData = mData;
        tvPromotionTitle.setText(mData.getPromotionTitle());
        tvPromotionUntil.setText(mData.getPromotionUntil());
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_placeholder)
                .centerCrop();
        Glide.with(itemView.getRootView().getContext()).load(mData.getPromotionImage()).apply(requestOptions).into(ivPromotionImage);
        tvPromotionShopName.setText(mData.getPromotionShop().getShopName());
        tvPromotionShopArea.setText(mData.getPromotionShop().getShopArea());
    }

    @Override
    public void onClick(View view) {

    }
}
