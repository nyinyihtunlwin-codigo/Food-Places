package projects.nyinyihtunlwin.foodplaces.persistence;

import android.content.ContentResolver;
import android.content.ContentUris;
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
    public static final String PATH_PROMOTION_SHOP = "promotion_shop";
    public static final String PATH_TERMS_IN_PROMOTIONS = "terms_in_promotion";
    public static final String PATH_GUIDES = "guides";
    public static final String PATH_FEATURED = "featured";

    public static final class PromotionsEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PROMOTIONS).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROMOTIONS;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROMOTIONS;

        public static final String TABLE_NAME = PATH_PROMOTIONS;

        public static final String COLUMN_PROMOTION_ID = "promotion_id";
        public static final String COLUMN_PROMOTION_IMAGE = "promotion_image";
        public static final String COLUMN_PROMOTION_TITLE = "promotion_title";
        public static final String COLUMN_PROMOTION_UNTIL = "promotion_until";
        public static final String COLUMN_IS_EXCLUSIVE = "is_exclusive";

        public static Uri buildContentUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class PromotionShopsEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PROMOTION_SHOP).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROMOTION_SHOP;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROMOTION_SHOP;

        public static final String TABLE_NAME = PATH_PROMOTION_SHOP;

        public static final String COLUMN_SHOP_ID = "shop_id";
        public static final String COLUMN_SHOP_NAME = "shop_name";
        public static final String COLUMN_SHOP_AREA = "shop_area";
        public static final String COLUMN_PROMOTION_ID = "promotion_id";

        public static Uri buildContentUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class TermsInPromotionsEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TERMS_IN_PROMOTIONS).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TERMS_IN_PROMOTIONS;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TERMS_IN_PROMOTIONS;

        public static final String TABLE_NAME = PATH_TERMS_IN_PROMOTIONS;

        public static final String COLUMN_PROMOTION_ID = "promotion_id";
        public static final String COLUMN_PROMOTION_TERM = "promotion_term";

        public static Uri buildContentUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class GuidesEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_GUIDES).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GUIDES;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GUIDES;

        public static final String TABLE_NAME = PATH_GUIDES;

        public static final String COLUMN_GUIDE_ID = "guide_id";
        public static final String COLUMN_GUIDE_IMAGE = "guide_image";
        public static final String COLUMN_GUIDE_TITLE = "guide_title";
        public static final String COLUMN_GUIDE_DESC = "guide_desc";

        public static Uri buildContentUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class FeaturedEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FEATURED).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FEATURED;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FEATURED;

        public static final String TABLE_NAME = PATH_FEATURED;

        public static final String COLUMN_FEATURED_ID = "featured_id";
        public static final String COLUMN_FEATURED_IMAGE = "featured_image";
        public static final String COLUMN_FEATURED_TITLE = "featured_title";
        public static final String COLUMN_FEATURED_DESC = "featured_desc";
        public static final String COLUMN_FEATURED_TAG = "featured_tag";

        public static Uri buildContentUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

}
