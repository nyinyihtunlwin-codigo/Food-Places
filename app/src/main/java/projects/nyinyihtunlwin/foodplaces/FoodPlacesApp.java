package projects.nyinyihtunlwin.foodplaces;

import android.app.Application;

import projects.nyinyihtunlwin.foodplaces.data.model.FoodPlacesModel;

/**
 * Created by Dell on 1/5/2018.
 */

public class FoodPlacesApp extends Application {

    public static final String LOG_TAG = "FoodPlacesApp";

    @Override
    public void onCreate() {
        super.onCreate();
        FoodPlacesModel.getObjInstance().startLoadingPromotions();
        FoodPlacesModel.getObjInstance().startLoadingGuides();
        FoodPlacesModel.getObjInstance().startLoadingFeatured();
    }
}
