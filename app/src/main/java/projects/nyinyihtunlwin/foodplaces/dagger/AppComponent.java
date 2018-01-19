package projects.nyinyihtunlwin.foodplaces.dagger;

import javax.inject.Singleton;

import dagger.Component;
import projects.nyinyihtunlwin.foodplaces.FoodPlacesApp;
import projects.nyinyihtunlwin.foodplaces.activities.MainActivity;
import projects.nyinyihtunlwin.foodplaces.data.model.FoodPlacesModel;
import projects.nyinyihtunlwin.foodplaces.mvp.presenters.FoodPlacesPresenter;

/**
 * Created by Dell on 1/19/2018.
 */

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    void inject(FoodPlacesApp foodPlacesApp);

    void inject(FoodPlacesModel foodPlacesModel);

    void inject(FoodPlacesPresenter foodPlacesPresenter);

    void inject(MainActivity mainActivity);
}
