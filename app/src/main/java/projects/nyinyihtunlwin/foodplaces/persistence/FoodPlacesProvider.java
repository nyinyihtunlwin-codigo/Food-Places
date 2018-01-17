package projects.nyinyihtunlwin.foodplaces.persistence;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Dell on 1/16/2018.
 */

public class FoodPlacesProvider extends ContentProvider {

    public static final int GUIDES = 100;
    public static final int FEATURED = 200;
    public static final int PROMOTIONS = 300;
    public static final int TERMS_IN_PROMOTION = 400;
    public static final int PROMOTION_SHOP = 500;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private FoodPlacesDBHelper mDBHelper;

    private static UriMatcher buildUriMatcher() {

        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(FoodPlacesContract.CONTENT_AUTHORITY, FoodPlacesContract.PATH_GUIDES, GUIDES);
        uriMatcher.addURI(FoodPlacesContract.CONTENT_AUTHORITY, FoodPlacesContract.PATH_FEATURED, FEATURED);
        uriMatcher.addURI(FoodPlacesContract.CONTENT_AUTHORITY, FoodPlacesContract.PATH_PROMOTIONS, PROMOTIONS);
        uriMatcher.addURI(FoodPlacesContract.CONTENT_AUTHORITY, FoodPlacesContract.PATH_TERMS_IN_PROMOTIONS, TERMS_IN_PROMOTION);
        uriMatcher.addURI(FoodPlacesContract.CONTENT_AUTHORITY, FoodPlacesContract.PATH_PROMOTION_SHOP, PROMOTION_SHOP);


        return uriMatcher;
    }

    private String getTableName(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case GUIDES:
                return FoodPlacesContract.GuidesEntry.TABLE_NAME;
            case FEATURED:
                return FoodPlacesContract.FeaturedEntry.TABLE_NAME;
            case PROMOTIONS:
                return FoodPlacesContract.PromotionsEntry.TABLE_NAME;
            case TERMS_IN_PROMOTION:
                return FoodPlacesContract.TermsInPromotionsEntry.TABLE_NAME;
            case PROMOTION_SHOP:
                return FoodPlacesContract.PromotionShopsEntry.TABLE_NAME;
        }
        return null;
    }

    private Uri getContentUri(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case GUIDES:
                return FoodPlacesContract.GuidesEntry.CONTENT_URI;
            case FEATURED:
                return FoodPlacesContract.FeaturedEntry.CONTENT_URI;
            case PROMOTIONS:
                return FoodPlacesContract.PromotionsEntry.CONTENT_URI;
            case TERMS_IN_PROMOTION:
                return FoodPlacesContract.TermsInPromotionsEntry.CONTENT_URI;
            case PROMOTION_SHOP:
                return FoodPlacesContract.PromotionShopsEntry.CONTENT_URI;
        }
        return null;
    }

    @Override
    public boolean onCreate() {
        mDBHelper = new FoodPlacesDBHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int matchUri = sUriMatcher.match(uri);
        switch (matchUri) {
            case GUIDES:
                return FoodPlacesContract.GuidesEntry.DIR_TYPE;
            case FEATURED:
                return FoodPlacesContract.FeaturedEntry.DIR_TYPE;
            case PROMOTIONS:
                return FoodPlacesContract.PromotionsEntry.DIR_TYPE;
            case TERMS_IN_PROMOTION:
                return FoodPlacesContract.TermsInPromotionsEntry.DIR_TYPE;
            case PROMOTION_SHOP:
                return FoodPlacesContract.PromotionShopsEntry.DIR_TYPE;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        long _id = db.insert(tableName, null, contentValues);
        if (_id > 0) {
            Uri tableContentUri = getContentUri(uri);
            Uri insertedUri = ContentUris.withAppendedId(tableContentUri, _id);
            if (getContext() != null) {
                getContext().getContentResolver().notifyChange(uri, null);
            }
            return insertedUri;
        }
        return null;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        int insertedCount = 0;

        try {
            db.beginTransaction();
            for (ContentValues cv : values) {
                long _id = db.insert(tableName, null, cv);
                if (_id > 0) {
                    insertedCount++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }

        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }

        return insertedCount;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int rowDeleted;
        String tableName = getTableName(uri);
        rowDeleted = db.delete(tableName, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowDeleted > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int rowUpdated;
        String tableName = getTableName(uri);

        rowUpdated = db.update(tableName, contentValues, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowUpdated > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowUpdated;
    }
}
