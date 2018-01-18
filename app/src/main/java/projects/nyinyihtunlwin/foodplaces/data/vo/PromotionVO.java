package projects.nyinyihtunlwin.foodplaces.data.vo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import projects.nyinyihtunlwin.foodplaces.persistence.FoodPlacesContract;

/**
 * Created by Dell on 1/6/2018.
 */

public class PromotionVO {

    @SerializedName("burpple-promotion-id")
    private String promotionId;

    @SerializedName("burpple-promotion-image")
    private String promotionImage;

    @SerializedName("burpple-promotion-title")
    private String promotionTitle;

    @SerializedName("burpple-promotion-until")
    private String promotionUntil;

    @SerializedName("burpple-promotion-shop")
    private PromotionShopVO promotionShop;

    @SerializedName("is-burpple-exclusive")
    private boolean exclusive;

    @SerializedName("burpple-promotion-terms")
    private List<String> promotionTerms;

    public String getPromotionId() {
        return promotionId;
    }

    public String getPromotionImage() {
        return promotionImage;
    }

    public String getPromotionTitle() {
        return promotionTitle;
    }

    public String getPromotionUntil() {
        return promotionUntil;
    }

    public PromotionShopVO getPromotionShop() {
        return promotionShop;
    }

    public boolean isExclusive() {
        return exclusive;
    }

    public List<String> getPromotionTerms() {
        if (promotionTerms == null) {
            promotionTerms = new ArrayList<>();
        }
        return promotionTerms;
    }

    public ContentValues parseToContentValues() {

        ContentValues contentValues = new ContentValues();

        contentValues.put(FoodPlacesContract.PromotionsEntry.COLUMN_PROMOTION_ID, promotionId);
        contentValues.put(FoodPlacesContract.PromotionsEntry.COLUMN_PROMOTION_IMAGE, promotionImage);
        contentValues.put(FoodPlacesContract.PromotionsEntry.COLUMN_PROMOTION_TITLE, promotionTitle);
        contentValues.put(FoodPlacesContract.PromotionsEntry.COLUMN_PROMOTION_UNTIL, promotionUntil);
        contentValues.put(FoodPlacesContract.PromotionsEntry.COLUMN_IS_EXCLUSIVE, exclusive);
        contentValues.put(FoodPlacesContract.PromotionsEntry.COLUMN_SHOP_ID, promotionShop.getShopId());


        return contentValues;
    }

    public static PromotionVO parseFromCursor(Context context, Cursor cursor) {
        PromotionVO promotion = new PromotionVO();
        promotion.promotionId = cursor.getString(cursor.getColumnIndex(FoodPlacesContract.PromotionsEntry.COLUMN_PROMOTION_ID));
        promotion.promotionImage = cursor.getString(cursor.getColumnIndex(FoodPlacesContract.PromotionsEntry.COLUMN_PROMOTION_IMAGE));
        promotion.promotionTitle = cursor.getString(cursor.getColumnIndex(FoodPlacesContract.PromotionsEntry.COLUMN_PROMOTION_TITLE));
        promotion.promotionUntil = cursor.getString(cursor.getColumnIndex(FoodPlacesContract.PromotionsEntry.COLUMN_PROMOTION_UNTIL));
        promotion.exclusive = cursor.getInt(cursor.getColumnIndex(FoodPlacesContract.PromotionsEntry.COLUMN_IS_EXCLUSIVE)) > 0;
        promotion.promotionShop = PromotionShopVO.parseFromCursor(cursor);
        promotion.promotionTerms = loadTermsInPromotion(context, promotion.promotionId);
        return promotion;
    }

    private static List<String> loadTermsInPromotion(Context context, String promotionId) {
        Cursor termsInPromotionCursor = context.getContentResolver().query(FoodPlacesContract.TermsInPromotionsEntry.CONTENT_URI,
                null,
                FoodPlacesContract.TermsInPromotionsEntry.COLUMN_PROMOTION_ID + " = ?", new String[]{promotionId},
                null);

        if (termsInPromotionCursor != null && termsInPromotionCursor.moveToFirst()) {
            List<String> termsInPromotion = new ArrayList<>();
            do {
                termsInPromotion.add(
                        termsInPromotionCursor.getString(
                                termsInPromotionCursor.getColumnIndex(FoodPlacesContract.TermsInPromotionsEntry.COLUMN_PROMOTION_TERM)
                        )
                );
            } while (termsInPromotionCursor.moveToNext());
            termsInPromotionCursor.close();
            return termsInPromotion;
        }
        return null;
    }
}
