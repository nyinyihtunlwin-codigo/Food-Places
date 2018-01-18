package projects.nyinyihtunlwin.foodplaces.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.nyinyihtunlwin.foodplaces.R;
import projects.nyinyihtunlwin.foodplaces.data.vo.GuidesVO;

/**
 * Created by Dell on 1/6/2018.
 */

public class BurppleGuidesViewHolder extends BaseViewHolder<GuidesVO> {

    @BindView(R.id.tv_guide_item_name)
    TextView tvGuideItemName;

    @BindView(R.id.iv_guide_image)
    ImageView ivGuide;

    GuidesVO mData;

    public BurppleGuidesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(GuidesVO mData) {
        this.mData = mData;
        tvGuideItemName.setText(mData.getGuideTitle());
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_placeholder)
                .centerCrop();
        Glide.with(itemView.getRootView().getContext()).load(mData.getGuideImage()).apply(requestOptions).into(ivGuide);
    }

    @Override
    public void onClick(View view) {

    }
}
