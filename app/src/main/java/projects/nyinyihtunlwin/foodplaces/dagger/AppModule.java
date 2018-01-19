package projects.nyinyihtunlwin.foodplaces.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import projects.nyinyihtunlwin.foodplaces.FoodPlacesApp;
import projects.nyinyihtunlwin.foodplaces.data.model.FoodPlacesModel;
import projects.nyinyihtunlwin.foodplaces.mvp.presenters.FoodPlacesPresenter;

/**
 * Created by Dell on 1/19/2018.
 */

@Module
public class AppModule {

    private FoodPlacesApp mApp;

    public AppModule(FoodPlacesApp mApp) {
        this.mApp = mApp;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mApp.getApplicationContext();
    }

    @Provides
    @Singleton
    public FoodPlacesModel provideFoodPlacesModel(Context context) {
        return null;
    }

    @Provides
    public FoodPlacesPresenter provideFoodPlacesPresenter() {
        return new FoodPlacesPresenter();
    }

}
