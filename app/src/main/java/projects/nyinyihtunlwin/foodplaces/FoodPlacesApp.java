package projects.nyinyihtunlwin.foodplaces;

import android.app.Application;

import javax.inject.Inject;

import projects.nyinyihtunlwin.foodplaces.dagger.AppComponent;
import projects.nyinyihtunlwin.foodplaces.dagger.AppModule;
import projects.nyinyihtunlwin.foodplaces.dagger.DaggerAppComponent;
import projects.nyinyihtunlwin.foodplaces.data.model.FoodPlacesModel;

/**
 * Created by Dell on 1/5/2018.
 */

public class FoodPlacesApp extends Application {

    public static final String LOG_TAG = "FoodPlacesApp";

    private AppComponent mAppComponent;

    @Inject
    FoodPlacesModel mFoodPlacesModel;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = initDagger();
        mAppComponent.inject(this);

        mFoodPlacesModel.startLoadingPromotions(getApplicationContext());
        mFoodPlacesModel.startLoadingGuides(getApplicationContext());
        mFoodPlacesModel.startLoadingFeatured(getApplicationContext());
    }

    private AppComponent initDagger() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
