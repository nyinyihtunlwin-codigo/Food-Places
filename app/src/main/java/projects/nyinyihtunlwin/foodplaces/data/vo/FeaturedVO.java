package projects.nyinyihtunlwin.foodplaces.data.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dell on 1/15/2018.
 */

public class FeaturedVO {

    @SerializedName("burpple-featured-id")
    private String featuredId;

    @SerializedName("burpple-featured-image")
    private String featuredImage;

    @SerializedName("burpple-featured-title")
    private String featuredTitle;

    @SerializedName("burpple-featured-desc")
    private String featuredDesc;

    @SerializedName("burpple-featured-tag")
    private String featuredTag;

    public String getFeaturedId() {
        return featuredId;
    }

    public String getFeaturedImage() {
        return featuredImage;
    }

    public String getFeaturedTitle() {
        return featuredTitle;
    }

    public String getFeaturedDesc() {
        return featuredDesc;
    }

    public String getFeaturedTag() {
        return featuredTag;
    }
}
