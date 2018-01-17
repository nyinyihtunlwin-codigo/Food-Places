package projects.nyinyihtunlwin.foodplaces.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dell on 1/16/2018.
 */

public class FoodPlacesDBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "foodplaces.db";

    private static final String SQL_CREATE_PROMOTIONS = "CREATE TABLE " + FoodPlacesContract.PromotionsEntry.TABLE_NAME + " (" +
            FoodPlacesContract.PromotionsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FoodPlacesContract.PromotionsEntry.COLUMN_PROMOTION_ID + " VARCHAR(256), " +
            FoodPlacesContract.PromotionsEntry.COLUMN_PROMOTION_IMAGE + " TEXT, " +
            FoodPlacesContract.PromotionsEntry.COLUMN_PROMOTION_TITLE + " TEXT, " +
            FoodPlacesContract.PromotionsEntry.COLUMN_PROMOTION_UNTIL + " TEXT, " +
            FoodPlacesContract.PromotionsEntry.COLUMN_IS_EXCLUSIVE + " INTEGER DEFAULT 0, " +
            " UNIQUE (" + FoodPlacesContract.PromotionsEntry.COLUMN_PROMOTION_ID + ") ON CONFLICT REPLACE" +
            ");";

    private static final String SQL_CREATE_PROMOTION_SHOP = "CREATE TABLE " + FoodPlacesContract.PromotionShopsEntry.TABLE_NAME + " (" +
            FoodPlacesContract.PromotionShopsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FoodPlacesContract.PromotionShopsEntry.COLUMN_SHOP_ID + " VARCHAR(256), " +
            FoodPlacesContract.PromotionShopsEntry.COLUMN_SHOP_NAME + " TEXT, " +
            FoodPlacesContract.PromotionShopsEntry.COLUMN_SHOP_AREA + " TEXT, " +
            FoodPlacesContract.PromotionShopsEntry.COLUMN_PROMOTION_ID + " VARCHAR(256), " +
            " UNIQUE (" + FoodPlacesContract.PromotionShopsEntry.COLUMN_SHOP_ID + ") ON CONFLICT REPLACE" +
            ");";

    private static final String SQL_CREATE_TERMS_IN_PROMOTION = "CREATE TABLE " + FoodPlacesContract.TermsInPromotionsEntry.TABLE_NAME + " (" +
            FoodPlacesContract.TermsInPromotionsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FoodPlacesContract.TermsInPromotionsEntry.COLUMN_PROMOTION_ID + " VARCHAR(256), " +
            FoodPlacesContract.TermsInPromotionsEntry.COLUMN_PROMOTION_TERM + " TEXT, " +
            " UNIQUE (" + FoodPlacesContract.TermsInPromotionsEntry.COLUMN_PROMOTION_ID + ", " +
            FoodPlacesContract.TermsInPromotionsEntry.COLUMN_PROMOTION_TERM
            + ") ON CONFLICT REPLACE" +
            ");";

    private static final String SQL_CREATE_GUIDES = "CREATE TABLE " + FoodPlacesContract.GuidesEntry.TABLE_NAME + " (" +
            FoodPlacesContract.GuidesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FoodPlacesContract.GuidesEntry.COLUMN_GUIDE_ID + " VARCHAR(256), " +
            FoodPlacesContract.GuidesEntry.COLUMN_GUIDE_IMAGE + " TEXT, " +
            FoodPlacesContract.GuidesEntry.COLUMN_GUIDE_TITLE + " TEXT, " +
            FoodPlacesContract.GuidesEntry.COLUMN_GUIDE_DESC + " TEXT, " +
            " UNIQUE (" + FoodPlacesContract.GuidesEntry.COLUMN_GUIDE_ID + ") ON CONFLICT REPLACE" +
            ");";

    private static final String SQL_CREATE_FEATURED = "CREATE TABLE " + FoodPlacesContract.FeaturedEntry.TABLE_NAME + " (" +
            FoodPlacesContract.FeaturedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FoodPlacesContract.FeaturedEntry.COLUMN_FEATURED_ID + " VARCHAR(256), " +
            FoodPlacesContract.FeaturedEntry.COLUMN_FEATURED_IMAGE + " TEXT, " +
            FoodPlacesContract.FeaturedEntry.COLUMN_FEATURED_TITLE + " TEXT, " +
            FoodPlacesContract.FeaturedEntry.COLUMN_FEATURED_DESC + " TEXT, " +
            FoodPlacesContract.FeaturedEntry.COLUMN_FEATURED_TAG + " TEXT, " +
            " UNIQUE (" + FoodPlacesContract.FeaturedEntry.COLUMN_FEATURED_ID + ") ON CONFLICT REPLACE" +
            ");";

    public FoodPlacesDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(SQL_CREATE_GUIDES);
        sqLiteDatabase.execSQL(SQL_CREATE_FEATURED);

        sqLiteDatabase.execSQL(SQL_CREATE_PROMOTIONS);
        sqLiteDatabase.execSQL(SQL_CREATE_TERMS_IN_PROMOTION);
        sqLiteDatabase.execSQL(SQL_CREATE_PROMOTION_SHOP);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FoodPlacesContract.GuidesEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FoodPlacesContract.FeaturedEntry.TABLE_NAME);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FoodPlacesContract.PromotionsEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FoodPlacesContract.TermsInPromotionsEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FoodPlacesContract.PromotionShopsEntry.TABLE_NAME);


        onCreate(sqLiteDatabase);
    }
}
