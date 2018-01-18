package projects.nyinyihtunlwin.foodplaces.mvp.views;

import android.content.Context;

import java.util.List;

import projects.nyinyihtunlwin.foodplaces.data.vo.FeaturedVO;
import projects.nyinyihtunlwin.foodplaces.data.vo.GuidesVO;
import projects.nyinyihtunlwin.foodplaces.data.vo.PromotionVO;

/**
 * Created by Dell on 1/19/2018.
 */

public interface FoodPlacesView {

    void displayPromotions(List<PromotionVO> promotionList);

    void displayGuides(List<GuidesVO> guideList);

    void displayFeatured(List<FeaturedVO> featuredList);

    void showLoading();

    Context getContext();
}
