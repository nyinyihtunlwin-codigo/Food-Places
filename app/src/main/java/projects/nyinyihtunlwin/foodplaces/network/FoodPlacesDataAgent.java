package projects.nyinyihtunlwin.foodplaces.network;

import android.content.Context;

/**
 * Created by Dell on 1/16/2018.
 */

public interface FoodPlacesDataAgent {

    void loadPromotions(String accessToken, int page, Context context);

    void loadGuides(String accessToken, int page,Context context);

    void loadFeatured(String accessToken, int page,Context context);
}
