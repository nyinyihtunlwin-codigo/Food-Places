package projects.nyinyihtunlwin.foodplaces.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import projects.nyinyihtunlwin.foodplaces.R;
import projects.nyinyihtunlwin.foodplaces.data.vo.PromotionVO;
import projects.nyinyihtunlwin.foodplaces.viewholders.PromotionsViewHolder;

/**
 * Created by Dell on 1/6/2018.
 */

public class PromotionAdapter extends BaseAdapter<PromotionsViewHolder, PromotionVO> {

    public PromotionAdapter(Context context) {
        super(context);
    }

    @Override
    public PromotionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_item_promotions, parent, false);
        return new PromotionsViewHolder(view);
    }
}
