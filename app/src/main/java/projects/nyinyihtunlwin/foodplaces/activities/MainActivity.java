package projects.nyinyihtunlwin.foodplaces.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;
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
import projects.nyinyihtunlwin.foodplaces.persistence.FoodPlacesContract;

public class MainActivity
        extends BaseActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

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

    private PromotionAdapter mPromotionAdapter;
    private GuidesAdapter mGuidesAdapter;


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
        rvPromotions.addOnScrollListener(scrollListenerPromotion);
        rvBurppleGuides.addOnScrollListener(scrollListenerGuides);

        pagerAdapter = new FoodPlacesImagesPagerAdapter(getApplicationContext());
        vpFoodPlaceImages.setAdapter(pagerAdapter);
        circleIndicator.setViewPager(vpFoodPlaceImages);

        getSupportLoaderManager().initLoader(PROMOTIONS_LOADER_ID, null, this);
        getSupportLoaderManager().initLoader(GUIDES_LOADER_ID, null, this);
        getSupportLoaderManager().initLoader(FEATURED_LOADER_ID, null, this);

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
                if (data != null && data.moveToFirst()) {
                    List<PromotionVO> promotionList = new ArrayList<>();
                    do {
                        PromotionVO promotionVO = PromotionVO.parseFromCursor(getApplicationContext(), data);
                        promotionList.add(promotionVO);
                    } while (data.moveToNext());
                    mPromotionAdapter.appendNewData(promotionList);
                }
                break;
            case GUIDES_LOADER_ID:
                if (data != null && data.moveToFirst()) {
                    List<GuidesVO> guideList = new ArrayList<>();
                    do {
                        GuidesVO guidesVO = GuidesVO.parseFromCursor(getApplicationContext(), data);
                        guideList.add(guidesVO);
                    } while (data.moveToNext());
                    mGuidesAdapter.appendNewData(guideList);
                }
                break;
            case FEATURED_LOADER_ID:
                if (data != null && data.moveToFirst()) {
                    List<FeaturedVO> featuredList = new ArrayList<>();
                    do {
                        FeaturedVO featuredVO = FeaturedVO.parseFromCursor(getApplicationContext(), data);
                        featuredList.add(featuredVO);
                    } while (data.moveToNext());
                }
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
