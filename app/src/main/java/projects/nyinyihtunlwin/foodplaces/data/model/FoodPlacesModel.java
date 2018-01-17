package projects.nyinyihtunlwin.foodplaces.data.model;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import projects.nyinyihtunlwin.foodplaces.FoodPlacesApp;
import projects.nyinyihtunlwin.foodplaces.data.vo.FeaturedVO;
import projects.nyinyihtunlwin.foodplaces.data.vo.GuidesVO;
import projects.nyinyihtunlwin.foodplaces.data.vo.PromotionShopVO;
import projects.nyinyihtunlwin.foodplaces.data.vo.PromotionVO;
import projects.nyinyihtunlwin.foodplaces.events.RestApiEvents;
import projects.nyinyihtunlwin.foodplaces.network.FoodPlacesDataAgentImpl;
import projects.nyinyihtunlwin.foodplaces.persistence.FoodPlacesContract;
import projects.nyinyihtunlwin.foodplaces.utils.AppConstants;

/**
 * Created by Dell on 1/15/2018.
 */

public class FoodPlacesModel {

    private List<PromotionVO> mPromotionList;
    private List<GuidesVO> mGuidesList;
    private List<FeaturedVO> mFeaturedList;

    private int mPageIndex = 1;

    private static FoodPlacesModel sObjInstance;

    private FoodPlacesModel() {
        mPromotionList = new ArrayList<>();
        mGuidesList = new ArrayList<>();
        mFeaturedList = new ArrayList<>();
        EventBus.getDefault().register(this);
    }

    public static FoodPlacesModel getObjInstance() {
        if (sObjInstance == null) {
            sObjInstance = new FoodPlacesModel();
        }
        return sObjInstance;
    }

    public void startLoadingPromotions(Context context) {
        FoodPlacesDataAgentImpl.getObjInstance().loadPromotions(AppConstants.ACCESS_TOKEN, mPageIndex, context);
    }

    public void startLoadingGuides(Context context) {
        FoodPlacesDataAgentImpl.getObjInstance().loadGuides(AppConstants.ACCESS_TOKEN, mPageIndex, context);
    }

    public void startLoadingFeatured(Context context) {
        FoodPlacesDataAgentImpl.getObjInstance().loadFeatured(AppConstants.ACCESS_TOKEN, mPageIndex, context);
    }

    @Subscribe
    public void onPromotionsDataLoaded(RestApiEvents.PromotionsDataLoadedEvent event) {
        mPageIndex = event.getLoadedPageIndex() + 1;
        mPromotionList.addAll(event.getLoadedPromotions());
        ContentValues[] promotionsCVs = new ContentValues[event.getLoadedPromotions().size()];

        List<ContentValues> promotionShopCVList = new ArrayList<>();
        List<ContentValues> termsInPromotionCVList = new ArrayList<>();

        for (int index = 0; index < promotionsCVs.length; index++) {
            PromotionVO promotionVO = event.getLoadedPromotions().get(index);
            promotionsCVs[index] = promotionVO.parseToContentValues();

            PromotionShopVO shopVO = promotionVO.getPromotionShop();
            promotionShopCVList.add(shopVO.parseToContentValues(promotionVO.getPromotionId()));

            for (String promotionTerm : promotionVO.getPromotionTerms()) {
                ContentValues imagesInNewsCV = new ContentValues();
                imagesInNewsCV.put(FoodPlacesContract.TermsInPromotionsEntry.COLUMN_PROMOTION_ID, promotionVO.getPromotionId());
                imagesInNewsCV.put(FoodPlacesContract.TermsInPromotionsEntry.COLUMN_PROMOTION_TERM, promotionTerm);
                termsInPromotionCVList.add(imagesInNewsCV);
            }
        }
        int insertedPromotionShop = event.getContext().getContentResolver().bulkInsert(FoodPlacesContract.PromotionShopsEntry.CONTENT_URI,
                promotionShopCVList.toArray(new ContentValues[0]));
        Log.d(FoodPlacesApp.LOG_TAG, "inserted promotion shop :" + insertedPromotionShop);

        int insertedTermsInPromotion = event.getContext().getContentResolver().bulkInsert(FoodPlacesContract.TermsInPromotionsEntry.CONTENT_URI,
                termsInPromotionCVList.toArray(new ContentValues[0]));
        Log.d(FoodPlacesApp.LOG_TAG, "inserted Terms In Promotions" + insertedTermsInPromotion);

    }

    @Subscribe
    public void onGuidesDataLoaded(RestApiEvents.GuidesDataLoadedEvent guidesDataLoadedEvent) {
        mPageIndex = guidesDataLoadedEvent.getLoadedPageIndex() + 1;
        mGuidesList.addAll(guidesDataLoadedEvent.getLoadedGuides());
    }

    @Subscribe
    public void onFeaturedDataLoaded(RestApiEvents.FeaturedDataLoadedEvent featuredDataLoadedEvent) {
        mPageIndex = featuredDataLoadedEvent.getLoadedPageIndex() + 1;
        mFeaturedList.addAll(featuredDataLoadedEvent.getLoadedFeatures());
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


}
