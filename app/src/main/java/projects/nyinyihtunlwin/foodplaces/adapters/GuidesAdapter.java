package projects.nyinyihtunlwin.foodplaces.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import projects.nyinyihtunlwin.foodplaces.R;
import projects.nyinyihtunlwin.foodplaces.data.vo.GuidesVO;
import projects.nyinyihtunlwin.foodplaces.viewholders.BurppleGuidesViewHolder;

/**
 * Created by Dell on 1/6/2018.
 */

public class GuidesAdapter extends BaseAdapter<BurppleGuidesViewHolder, GuidesVO> {

    public GuidesAdapter(Context context) {
        super(context);
    }

    @Override
    public BurppleGuidesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_item_guides, parent, false);
        return new BurppleGuidesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BurppleGuidesViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 15;
    }
}
