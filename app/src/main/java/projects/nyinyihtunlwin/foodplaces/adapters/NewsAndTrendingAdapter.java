package projects.nyinyihtunlwin.foodplaces.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import projects.nyinyihtunlwin.foodplaces.R;
import projects.nyinyihtunlwin.foodplaces.data.vo.NewsAndTrendingVO;
import projects.nyinyihtunlwin.foodplaces.viewholders.NewsAndTrendingViewHolder;

/**
 * Created by Dell on 1/6/2018.
 */

public class NewsAndTrendingAdapter extends BaseAdapter<NewsAndTrendingViewHolder, NewsAndTrendingVO> {

    public NewsAndTrendingAdapter(Context context) {
        super(context);
    }

    @Override
    public NewsAndTrendingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_news_and_trending, parent, false);
        return new NewsAndTrendingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsAndTrendingViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
