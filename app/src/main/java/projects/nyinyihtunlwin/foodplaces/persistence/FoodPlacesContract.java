package projects.nyinyihtunlwin.foodplaces.persistence;

import android.net.Uri;
import android.provider.BaseColumns;

import projects.nyinyihtunlwin.foodplaces.FoodPlacesApp;

/**
 * Created by Dell on 1/16/2018.
 */

public class FoodPlacesContract {

    public static final String CONTENT_AUTHORITY = FoodPlacesApp.class.getPackage().getName();
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_PROMOTIONS = "promotions";
    public static final String PATH_PROMOTION_SHOPS = "promotion_shops";
    public static final String PATH_GUIDES = "guides";
    public static final String PATH_FEATURED = "featured";

    public static final class PromotionsEntry implements BaseColumns {

    }

    public static final class PromotionShopsEntry implements BaseColumns {

    }

    public static final class GuidesEntry implements BaseColumns {

    }

    public static final class FeaturedEntry implements BaseColumns {

    }

}
