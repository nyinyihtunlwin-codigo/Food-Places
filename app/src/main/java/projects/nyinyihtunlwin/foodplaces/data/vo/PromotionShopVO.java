package projects.nyinyihtunlwin.foodplaces.data.vo;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import projects.nyinyihtunlwin.foodplaces.persistence.FoodPlacesContract;

/**
 * Created by Dell on 1/15/2018.
 */

public class PromotionShopVO {

    @SerializedName("burpple-shop-id")
    private String shopId;

    @SerializedName("burpple-shop-name")
    private String shopName;

    @SerializedName("burpple-shop-area")
    private String shopArea;

    public String getShopId() {
        return shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public String getShopArea() {
        return shopArea;
    }

    public ContentValues parseToContentValues() {

        ContentValues contentValues = new ContentValues();

        contentValues.put(FoodPlacesContract.PromotionShopsEntry.COLUMN_SHOP_ID, shopId);
        contentValues.put(FoodPlacesContract.PromotionShopsEntry.COLUMN_SHOP_NAME, shopName);
        contentValues.put(FoodPlacesContract.PromotionShopsEntry.COLUMN_SHOP_AREA, shopArea);

        return contentValues;
    }

    public static PromotionShopVO parseFromCursor(Cursor cursor) {
        PromotionShopVO promotionShop = new PromotionShopVO();

        promotionShop.shopId = cursor.getString(cursor.getColumnIndex(FoodPlacesContract.PromotionShopsEntry.COLUMN_SHOP_ID));
        promotionShop.shopName = cursor.getString(cursor.getColumnIndex(FoodPlacesContract.PromotionShopsEntry.COLUMN_SHOP_NAME));
        promotionShop.shopArea = cursor.getString(cursor.getColumnIndex(FoodPlacesContract.PromotionShopsEntry.COLUMN_SHOP_AREA));

        return promotionShop;
    }
}
