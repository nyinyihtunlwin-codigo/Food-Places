package projects.nyinyihtunlwin.foodplaces.data.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dell on 1/15/2018.
 */

class PromotionShopVO {

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
}
