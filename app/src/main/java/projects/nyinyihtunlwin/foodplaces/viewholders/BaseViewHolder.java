package projects.nyinyihtunlwin.foodplaces.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Dell on 1/6/2018.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {

    private T mData;

    public BaseViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    public abstract void setData(T data);
}
