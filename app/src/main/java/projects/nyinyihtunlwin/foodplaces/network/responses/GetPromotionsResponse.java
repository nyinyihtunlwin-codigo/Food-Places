package projects.nyinyihtunlwin.foodplaces.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import projects.nyinyihtunlwin.foodplaces.data.vo.PromotionVO;

/**
 * Created by Dell on 1/15/2018.
 */

public class GetPromotionsResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("apiVersion")
    private String apiVersion;

    @SerializedName("page")
    private String page;

    @SerializedName("promotions")
    private List<PromotionVO> promotions;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getPage() {
        return page;
    }

    public List<PromotionVO> getPromotions() {
        return promotions;
    }
}
