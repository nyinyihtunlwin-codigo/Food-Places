package projects.nyinyihtunlwin.foodplaces.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import projects.nyinyihtunlwin.foodplaces.FoodPlacesApp;
import projects.nyinyihtunlwin.foodplaces.data.model.FoodPlacesModel;
import projects.nyinyihtunlwin.foodplaces.mvp.presenters.FoodPlacesPresenter;
import projects.nyinyihtunlwin.foodplaces.network.FoodPlacesDataAgent;
import projects.nyinyihtunlwin.foodplaces.network.FoodPlacesDataAgentImpl;
import projects.nyinyihtunlwin.foodplaces.utils.ConfigUtils;

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
    public FoodPlacesDataAgent provideFoodPlacesDataAgent() {
        return new FoodPlacesDataAgentImpl();
    }

    @Provides
    @Singleton
    public FoodPlacesModel provideFoodPlacesModel(Context context) {
        return new FoodPlacesModel(context);
    }

    @Provides
    @Singleton
    public FoodPlacesPresenter provideFoodPlacesPresenter() {
        return new FoodPlacesPresenter();
    }

    @Provides
    @Singleton
    public ConfigUtils provideConfigUtils(Context context) {
        return new ConfigUtils(context);
    }
}
