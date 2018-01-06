package projects.nyinyihtunlwin.foodplaces.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;
import projects.nyinyihtunlwin.foodplaces.R;
import projects.nyinyihtunlwin.foodplaces.adapters.FoodPlacesImagesPagerAdapter;
import projects.nyinyihtunlwin.foodplaces.adapters.GuidesAdapter;
import projects.nyinyihtunlwin.foodplaces.adapters.NewsAndTrendingAdapter;
import projects.nyinyihtunlwin.foodplaces.adapters.PromotionAdapter;
import projects.nyinyihtunlwin.foodplaces.components.EmptyViewPod;
import projects.nyinyihtunlwin.foodplaces.components.SmartHorizontalScrollListener;
import projects.nyinyihtunlwin.foodplaces.components.SmartRecyclerView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.rv_promotions)
    SmartRecyclerView rvPromotions;

    @BindView(R.id.rv_burpple_guides)
    SmartRecyclerView rvBurppleGuides;

    @BindView(R.id.rv_news_and_trending)
    SmartRecyclerView rvNewsAndTrending;

    @BindView(R.id.vp_empty_data)
    EmptyViewPod vpEmptyData;

    @BindView(R.id.vp_empty_data_guides)
    EmptyViewPod vpEmptyDataGuides;

    @BindView(R.id.vp_empty_data_news_and_trending)
    EmptyViewPod vpEmptyDataNewsAndTrending;

    @BindView(R.id.vp_food_place_images)
    ViewPager vpFoodPlaceImages;

    @BindView(R.id.indicator)
    CircleIndicator circleIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);

        rvPromotions.setEmptyView(vpEmptyData);
        rvPromotions.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        rvBurppleGuides.setEmptyView(vpEmptyDataGuides);
        rvBurppleGuides.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        rvNewsAndTrending.setEmptyView(vpEmptyDataNewsAndTrending);
        rvNewsAndTrending.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        PromotionAdapter promotionAdapter = new PromotionAdapter(getApplicationContext());
        rvPromotions.setAdapter(promotionAdapter);

        GuidesAdapter guidesAdapter = new GuidesAdapter(getApplicationContext());
        rvBurppleGuides.setAdapter(guidesAdapter);

        NewsAndTrendingAdapter newsAndTrendingAdapter = new NewsAndTrendingAdapter(getApplicationContext());
        rvNewsAndTrending.setAdapter(newsAndTrendingAdapter);

        SmartHorizontalScrollListener scrollListenerPromotion = new SmartHorizontalScrollListener(new SmartHorizontalScrollListener.OnSmartHorizontalScrollListener() {
            @Override
            public void onListEndReached() {
                Snackbar.make(rvPromotions, "No more promotions available.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        SmartHorizontalScrollListener scrollListenerGuides = new SmartHorizontalScrollListener(new SmartHorizontalScrollListener.OnSmartHorizontalScrollListener() {
            @Override
            public void onListEndReached() {
                Snackbar.make(rvBurppleGuides, "No more guides available.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        SmartHorizontalScrollListener scrollListenerNewsAndTrending = new SmartHorizontalScrollListener(new SmartHorizontalScrollListener.OnSmartHorizontalScrollListener() {
            @Override
            public void onListEndReached() {
                Snackbar.make(rvNewsAndTrending, "No more item available.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        rvPromotions.addOnScrollListener(scrollListenerPromotion);
        rvBurppleGuides.addOnScrollListener(scrollListenerGuides);
        rvNewsAndTrending.addOnScrollListener(scrollListenerNewsAndTrending);

        FoodPlacesImagesPagerAdapter pagerAdapter = new FoodPlacesImagesPagerAdapter(getApplicationContext());
        vpFoodPlaceImages.setAdapter(pagerAdapter);
        circleIndicator.setViewPager(vpFoodPlaceImages);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
