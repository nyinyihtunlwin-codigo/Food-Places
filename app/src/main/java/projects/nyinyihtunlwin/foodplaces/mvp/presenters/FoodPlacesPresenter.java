package projects.nyinyihtunlwin.foodplaces.mvp.presenters;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import projects.nyinyihtunlwin.foodplaces.FoodPlacesApp;
import projects.nyinyihtunlwin.foodplaces.data.model.FoodPlacesModel;
import projects.nyinyihtunlwin.foodplaces.data.vo.FeaturedVO;
import projects.nyinyihtunlwin.foodplaces.data.vo.GuidesVO;
import projects.nyinyihtunlwin.foodplaces.data.vo.PromotionVO;
import projects.nyinyihtunlwin.foodplaces.mvp.views.FoodPlacesView;

/**
 * Created by Dell on 1/19/2018.
 */

public class FoodPlacesPresenter extends BasePresenter<FoodPlacesView> {


    @Inject
    FoodPlacesModel mFoodPlacesModel;

    public FoodPlacesPresenter() {
    }

    @Override
    public void onCreate(FoodPlacesView mView) {
        super.onCreate(mView);
        FoodPlacesApp foodPlacesApp=(FoodPlacesApp)mView.getContext();
        foodPlacesApp.getAppComponent().inject(this);
    }

    @Override
    public void onStart() {
        List<PromotionVO> promotionList = mFoodPlacesModel.getmPromotionList();
        List<GuidesVO> guidesList = mFoodPlacesModel.getmGuidesList();
        List<FeaturedVO> featuredList = mFoodPlacesModel.getmFeaturedList();
        if (!promotionList.isEmpty()) {
            mView.displayPromotions(promotionList);
        } else {
            mView.showLoading();
        }
        if (!guidesList.isEmpty()) {
            mView.displayGuides(guidesList);
        } else {
            mView.showLoading();
        }
        if (!featuredList.isEmpty()) {
            mView.displayFeatured(featuredList);
        } else {
            mView.showLoading();
        }
    }


    public void onPromotionsLoaded(Context context, Cursor data) {
        if (data != null && data.moveToFirst()) {
            List<PromotionVO> promotionList = new ArrayList<>();
            do {
                PromotionVO promotionVO = PromotionVO.parseFromCursor(context, data);
                promotionList.add(promotionVO);
            } while (data.moveToNext());
            mView.displayPromotions(promotionList);
        }
    }

    public void onGuidesLoaded(Context context, Cursor data) {
        if (data != null && data.moveToFirst()) {
            List<GuidesVO> guideList = new ArrayList<>();
            do {
                GuidesVO guidesVO = GuidesVO.parseFromCursor(context, data);
                guideList.add(guidesVO);
            } while (data.moveToNext());
            mView.displayGuides(guideList);
        }
    }

    public void onFeaturedLoaded(Context context, Cursor data) {
        if (data != null && data.moveToFirst()) {
            List<FeaturedVO> featuredList = new ArrayList<>();
            do {
                FeaturedVO featuredVO = FeaturedVO.parseFromCursor(context, data);
                featuredList.add(featuredVO);
            } while (data.moveToNext());
            mView.displayFeatured(featuredList);
        }
    }

    public void onPromotionListEndReach(Context context) {
        mFoodPlacesModel.loadMorePromotions(context);
    }

    public void onGuidesListEndReach(Context context) {
        mFoodPlacesModel.loadMoreGuides(context);
    }

    public void onForceRefreshData(Context context) {
        mFoodPlacesModel.forceRefreshData(context);
    }

    @Override
    public void onStop() {

    }

}
