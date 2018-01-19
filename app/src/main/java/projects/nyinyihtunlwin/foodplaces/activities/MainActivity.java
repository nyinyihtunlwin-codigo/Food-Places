package projects.nyinyihtunlwin.foodplaces.activities;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;
import projects.nyinyihtunlwin.foodplaces.FoodPlacesApp;
import projects.nyinyihtunlwin.foodplaces.R;
import projects.nyinyihtunlwin.foodplaces.adapters.FoodPlacesImagesPagerAdapter;
import projects.nyinyihtunlwin.foodplaces.adapters.GuidesAdapter;
import projects.nyinyihtunlwin.foodplaces.adapters.PromotionAdapter;
import projects.nyinyihtunlwin.foodplaces.components.EmptyViewPod;
import projects.nyinyihtunlwin.foodplaces.components.SmartHorizontalScrollListener;
import projects.nyinyihtunlwin.foodplaces.components.SmartRecyclerView;
import projects.nyinyihtunlwin.foodplaces.data.vo.FeaturedVO;
import projects.nyinyihtunlwin.foodplaces.data.vo.GuidesVO;
import projects.nyinyihtunlwin.foodplaces.data.vo.PromotionVO;
import projects.nyinyihtunlwin.foodplaces.mvp.presenters.FoodPlacesPresenter;
import projects.nyinyihtunlwin.foodplaces.mvp.views.FoodPlacesView;
import projects.nyinyihtunlwin.foodplaces.persistence.FoodPlacesContract;

public class MainActivity
        extends BaseActivity
        implements LoaderManager.LoaderCallbacks<Cursor>, FoodPlacesView {

    private static final int PROMOTIONS_LOADER_ID = 1001;
    private static final int GUIDES_LOADER_ID = 1002;
    private static final int FEATURED_LOADER_ID = 1003;

    @BindView(R.id.rv_promotions)
    SmartRecyclerView rvPromotions;

    @BindView(R.id.rv_burpple_guides)
    SmartRecyclerView rvBurppleGuides;

    @BindView(R.id.vp_empty_data)
    EmptyViewPod vpEmptyData;

    @BindView(R.id.vp_empty_data_guides)
    EmptyViewPod vpEmptyDataGuides;


    @BindView(R.id.vp_food_place_images)
    ViewPager vpFoodPlaceImages;

    @BindView(R.id.indicator)
    CircleIndicator circleIndicator;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private PromotionAdapter mPromotionAdapter;
    private GuidesAdapter mGuidesAdapter;

    @Inject
    FoodPlacesPresenter mPresenter;


    private Handler handler;
    private int delay = 3000;
    private int page = 0;
    private FoodPlacesImagesPagerAdapter pagerAdapter;
    Runnable runnable = new Runnable() {
        public void run() {
            if (pagerAdapter.getCount() == page) {
                page = 0;
            } else {
                page++;
            }
            vpFoodPlaceImages.setCurrentItem(page, true);
            handler.postDelayed(this, delay);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);

        handler = new Handler();

        FoodPlacesApp foodPlacesApp = (FoodPlacesApp) getApplicationContext();
        foodPlacesApp.getAppComponent().inject(this);

        mPresenter.onCreate(this);

        rvPromotions.setEmptyView(vpEmptyData);
        rvPromotions.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        rvBurppleGuides.setEmptyView(vpEmptyDataGuides);
        rvBurppleGuides.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        mPromotionAdapter = new PromotionAdapter(getApplicationContext());
        rvPromotions.setAdapter(mPromotionAdapter);

        mGuidesAdapter = new GuidesAdapter(getApplicationContext());
        rvBurppleGuides.setAdapter(mGuidesAdapter);

        SmartHorizontalScrollListener scrollListenerPromotion = new SmartHorizontalScrollListener(new SmartHorizontalScrollListener.OnSmartHorizontalScrollListener() {
            @Override
            public void onListEndReached() {
                mPresenter.onPromotionListEndReach(getApplicationContext());
            }
        });
        SmartHorizontalScrollListener scrollListenerGuides = new SmartHorizontalScrollListener(new SmartHorizontalScrollListener.OnSmartHorizontalScrollListener() {
            @Override
            public void onListEndReached() {
                mPresenter.onGuidesListEndReach(getApplicationContext());
            }
        });
        rvPromotions.addOnScrollListener(scrollListenerPromotion);
        rvBurppleGuides.addOnScrollListener(scrollListenerGuides);

        pagerAdapter = new FoodPlacesImagesPagerAdapter(getApplicationContext());
        vpFoodPlaceImages.setAdapter(pagerAdapter);


        getSupportLoaderManager().initLoader(PROMOTIONS_LOADER_ID, null, this);
        getSupportLoaderManager().initLoader(GUIDES_LOADER_ID, null, this);
        getSupportLoaderManager().initLoader(FEATURED_LOADER_ID, null, this);

        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onForceRefreshData(getApplicationContext());
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, delay);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cursorLoader = null;
        switch (id) {
            case PROMOTIONS_LOADER_ID:
                cursorLoader = new CursorLoader(getApplicationContext(),
                        FoodPlacesContract.PromotionsEntry.CONTENT_URI,
                        null,
                        null,
                        null,
                        null);
                break;
            case GUIDES_LOADER_ID:
                cursorLoader = new CursorLoader(getApplicationContext(),
                        FoodPlacesContract.GuidesEntry.CONTENT_URI,
                        null,
                        null,
                        null,
                        null);
                break;
            case FEATURED_LOADER_ID:
                cursorLoader = new CursorLoader(getApplicationContext(),
                        FoodPlacesContract.FeaturedEntry.CONTENT_URI,
                        null,
                        null,
                        null,
                        null);
                break;
        }
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch (loader.getId()) {
            case PROMOTIONS_LOADER_ID:
                mPresenter.onPromotionsLoaded(getApplicationContext(), data);
                break;
            case GUIDES_LOADER_ID:
                mPresenter.onGuidesLoaded(getApplicationContext(), data);
                break;
            case FEATURED_LOADER_ID:
                mPresenter.onFeaturedLoaded(getApplicationContext(), data);
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void displayPromotions(List<PromotionVO> promotionList) {
        mPromotionAdapter.appendNewData(promotionList);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void displayGuides(List<GuidesVO> guideList) {
        mGuidesAdapter.appendNewData(guideList);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void displayFeatured(List<FeaturedVO> featuredList) {
        pagerAdapter.setFeatures(featuredList);
        circleIndicator.setViewPager(vpFoodPlaceImages);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }
}
