package projects.nyinyihtunlwin.foodplaces.network;

/**
 * Created by Dell on 1/16/2018.
 */

public interface FoodPlacesDataAgent {

    void loadPromotions(String accessToken, int page);

    void loadGuides(String accessToken, int page);

    void loadFeatured(String accessToken, int page);
}
