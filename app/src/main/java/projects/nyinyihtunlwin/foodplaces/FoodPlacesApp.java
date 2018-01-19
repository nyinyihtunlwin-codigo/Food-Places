package projects.nyinyihtunlwin.foodplaces;

import android.app.Application;

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

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = initDagger();
        mAppComponent.inject(this);

        FoodPlacesModel.getObjInstance().startLoadingPromotions(getApplicationContext());
        FoodPlacesModel.getObjInstance().startLoadingGuides(getApplicationContext());
        FoodPlacesModel.getObjInstance().startLoadingFeatured(getApplicationContext());
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
