package projects.nyinyihtunlwin.foodplaces.data.model;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import projects.nyinyihtunlwin.foodplaces.FoodPlacesApp;
import projects.nyinyihtunlwin.foodplaces.data.vo.FeaturedVO;
import projects.nyinyihtunlwin.foodplaces.data.vo.GuidesVO;
import projects.nyinyihtunlwin.foodplaces.data.vo.PromotionShopVO;
import projects.nyinyihtunlwin.foodplaces.data.vo.PromotionVO;
import projects.nyinyihtunlwin.foodplaces.events.RestApiEvents;
import projects.nyinyihtunlwin.foodplaces.network.FoodPlacesDataAgent;
import projects.nyinyihtunlwin.foodplaces.network.FoodPlacesDataAgentImpl;
import projects.nyinyihtunlwin.foodplaces.persistence.FoodPlacesContract;
import projects.nyinyihtunlwin.foodplaces.utils.AppConstants;
import projects.nyinyihtunlwin.foodplaces.utils.ConfigUtils;

/**
 * Created by Dell on 1/15/2018.
 */

public class FoodPlacesModel {

    private List<PromotionVO> mPromotionList;
    private List<GuidesVO> mGuidesList;
    private List<FeaturedVO> mFeaturedList;

    @Inject
    FoodPlacesDataAgent mDataAgent;

    @Inject
    ConfigUtils mConfigUtils;


    public FoodPlacesModel(Context context) {
        mPromotionList = new ArrayList<>();
        mGuidesList = new ArrayList<>();
        mFeaturedList = new ArrayList<>();
        EventBus.getDefault().register(this);

        FoodPlacesApp foodPlacesApp = (FoodPlacesApp) context.getApplicationContext();
        foodPlacesApp.getAppComponent().inject(this);

    }

    public void startLoadingPromotions(Context context) {
        mDataAgent.loadPromotions(AppConstants.ACCESS_TOKEN, mConfigUtils.loadPageIndex(), context);
    }

    public void startLoadingGuides(Context context) {
        mDataAgent.loadGuides(AppConstants.ACCESS_TOKEN, mConfigUtils.loadPageIndex(), context);
    }

    public void startLoadingFeatured(Context context) {
        mDataAgent.loadFeatured(AppConstants.ACCESS_TOKEN, mConfigUtils.loadPageIndex(), context);
    }

    @Subscribe
    public void onPromotionsDataLoaded(RestApiEvents.PromotionsDataLoadedEvent event) {
        mConfigUtils.savePageIndex(event.getLoadedPageIndex()+1);
        mPromotionList.addAll(event.getLoadedPromotions());

        // Stored in DB
        ContentValues[] promotionsCVs = new ContentValues[event.getLoadedPromotions().size()];

        List<ContentValues> promotionShopCVList = new ArrayList<>();
        List<ContentValues> termsInPromotionCVList = new ArrayList<>();

        for (int index = 0; index < promotionsCVs.length; index++) {
            PromotionVO promotionVO = event.getLoadedPromotions().get(index);
            promotionsCVs[index] = promotionVO.parseToContentValues();

            PromotionShopVO shopVO = promotionVO.getPromotionShop();
            promotionShopCVList.add(shopVO.parseToContentValues());

            for (String promotionTerm : promotionVO.getPromotionTerms()) {
                ContentValues termsInPromotionCV = new ContentValues();
                termsInPromotionCV.put(FoodPlacesContract.TermsInPromotionsEntry.COLUMN_PROMOTION_ID, promotionVO.getPromotionId());
                termsInPromotionCV.put(FoodPlacesContract.TermsInPromotionsEntry.COLUMN_PROMOTION_TERM, promotionTerm);
                termsInPromotionCVList.add(termsInPromotionCV);
            }
        }
        int insertedPromotionShop = event.getContext().getContentResolver().bulkInsert(FoodPlacesContract.PromotionShopsEntry.CONTENT_URI,
                promotionShopCVList.toArray(new ContentValues[0]));
        Log.d(FoodPlacesApp.LOG_TAG, "inserted promotion shop :" + insertedPromotionShop);

        int insertedTermsInPromotion = event.getContext().getContentResolver().bulkInsert(FoodPlacesContract.TermsInPromotionsEntry.CONTENT_URI,
                termsInPromotionCVList.toArray(new ContentValues[0]));
        Log.d(FoodPlacesApp.LOG_TAG, "inserted Terms In Promotions" + insertedTermsInPromotion);

        int insertedRowCount = event.getContext().getContentResolver().bulkInsert(FoodPlacesContract.PromotionsEntry.CONTENT_URI, promotionsCVs);
        Log.d(FoodPlacesApp.LOG_TAG, "inserted row in Promotions : " + insertedRowCount);
    }

    @Subscribe
    public void onGuidesDataLoaded(RestApiEvents.GuidesDataLoadedEvent event) {
        mConfigUtils.savePageIndex(event.getLoadedPageIndex()+1);
        mGuidesList.addAll(event.getLoadedGuides());

        ContentValues[] guideCVs = new ContentValues[event.getLoadedGuides().size()];
        for (int index = 0; index < guideCVs.length; index++) {
            GuidesVO guidesVO = event.getLoadedGuides().get(index);
            guideCVs[index] = guidesVO.parseToContentValues();
        }

        int insertedRowCount = event.getContext().getContentResolver().bulkInsert(FoodPlacesContract.GuidesEntry.CONTENT_URI, guideCVs);
        Log.d(FoodPlacesApp.LOG_TAG, "Inserted row in Guides : " + insertedRowCount);
    }

    @Subscribe
    public void onFeaturedDataLoaded(RestApiEvents.FeaturedDataLoadedEvent event) {
        mConfigUtils.savePageIndex(event.getLoadedPageIndex()+1);
        mFeaturedList.addAll(event.getLoadedFeatures());

        ContentValues[] featuredCVs = new ContentValues[event.getLoadedFeatures().size()];
        for (int index = 0; index < featuredCVs.length; index++) {
            FeaturedVO featuredVO = event.getLoadedFeatures().get(index);
            featuredCVs[index] = featuredVO.parseToContentValues();
        }

        int insertedRowCount = event.getContext().getContentResolver().bulkInsert(FoodPlacesContract.FeaturedEntry.CONTENT_URI, featuredCVs);
        Log.d(FoodPlacesApp.LOG_TAG, "Inserted row in Guides : " + insertedRowCount);
    }

    public List<PromotionVO> getmPromotionList() {
        return mPromotionList;
    }

    public List<GuidesVO> getmGuidesList() {
        return mGuidesList;
    }

    public List<FeaturedVO> getmFeaturedList() {
        return mFeaturedList;
    }


    public void loadMorePromotions(Context context) {
        mDataAgent.loadPromotions(AppConstants.ACCESS_TOKEN, mConfigUtils.loadPageIndex(), context);
    }

    public void loadMoreGuides(Context context) {
        mDataAgent.loadGuides(AppConstants.ACCESS_TOKEN, mConfigUtils.loadPageIndex(), context);
    }

    public void forceRefreshData(Context context) {
        mPromotionList = new ArrayList<>();
        mGuidesList = new ArrayList<>();
        mFeaturedList = new ArrayList<>();
        mConfigUtils.savePageIndex(1);
        startLoadingFeatured(context);
        startLoadingPromotions(context);
        startLoadingGuides(context);
    }
}
