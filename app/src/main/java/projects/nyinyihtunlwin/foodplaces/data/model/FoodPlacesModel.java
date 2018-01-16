package projects.nyinyihtunlwin.foodplaces.data.model;

import java.util.List;

import projects.nyinyihtunlwin.foodplaces.data.vo.FeaturedVO;
import projects.nyinyihtunlwin.foodplaces.data.vo.GuidesVO;
import projects.nyinyihtunlwin.foodplaces.data.vo.PromotionVO;
import projects.nyinyihtunlwin.foodplaces.network.FoodPlacesDataAgentImpl;
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
    }

    public static FoodPlacesModel getObjInstance() {
        if (sObjInstance == null) {
            sObjInstance = new FoodPlacesModel();
        }
        return sObjInstance;
    }

    public void startLoadingPromotions() {
        FoodPlacesDataAgentImpl.getObjInstance().loadPromotions(AppConstants.ACCESS_TOKEN, mPageIndex);
    }

    public void startLoadingGuides() {
        FoodPlacesDataAgentImpl.getObjInstance().loadGuides(AppConstants.ACCESS_TOKEN, mPageIndex);
    }

    public void startLoadingFeatured() {
        FoodPlacesDataAgentImpl.getObjInstance().loadFeatured(AppConstants.ACCESS_TOKEN, mPageIndex);
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
