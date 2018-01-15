package projects.nyinyihtunlwin.foodplaces.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import projects.nyinyihtunlwin.foodplaces.data.vo.FeaturedVO;

/**
 * Created by Dell on 1/15/2018.
 */

public class GetFeaturedResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("apiVersion")
    private String apiVersion;

    @SerializedName("page")
    private String page;

    @SerializedName("featured")
    private List<FeaturedVO> featuredVOs;

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

    public List<FeaturedVO> getFeaturedVOs() {
        return featuredVOs;
    }

}
