package projects.nyinyihtunlwin.foodplaces.data.vo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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
        return promotionTerms;
    }
}
