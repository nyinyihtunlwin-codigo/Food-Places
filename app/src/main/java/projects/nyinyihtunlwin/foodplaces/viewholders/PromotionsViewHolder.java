package projects.nyinyihtunlwin.foodplaces.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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


    public PromotionsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(PromotionVO data) {
        tvPromotionTitle.setText(data.getPromotionTitle());
        tvPromotionUntil.setText(data.getPromotionUntil());
    }

    @Override
    public void onClick(View view) {

    }
}
