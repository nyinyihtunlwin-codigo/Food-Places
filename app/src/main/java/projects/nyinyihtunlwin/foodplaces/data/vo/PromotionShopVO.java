package projects.nyinyihtunlwin.foodplaces.data.vo;

import android.content.ContentValues;

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
}
