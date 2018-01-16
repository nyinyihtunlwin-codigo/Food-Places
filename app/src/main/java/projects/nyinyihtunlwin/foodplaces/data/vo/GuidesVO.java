package projects.nyinyihtunlwin.foodplaces.data.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dell on 1/6/2018.
 */

public class GuidesVO {

    @SerializedName("burpple-guide-id")
    private String guideId;

    @SerializedName("burpple-guide-image")
    private String guideImage;

    @SerializedName("burpple-guide-title")
    private String guideTitle;

    @SerializedName("burpple-guide-desc")
    private String guideDesc;

    public String getGuideId() {
        return guideId;
    }

    public String getGuideImage() {
        return guideImage;
    }

    public String getGuideTitle() {
        return guideTitle;
    }

    public String getGuideDesc() {
        return guideDesc;
    }
}
