package projects.nyinyihtunlwin.foodplaces.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import projects.nyinyihtunlwin.foodplaces.data.vo.FeaturedVO;

/**
 * Created by Dell on 1/15/2018.
 */

public class GetFeaturedResponse extends BaseResponse {

    @SerializedName("featured")
    private List<FeaturedVO> featuredVOs;

    public List<FeaturedVO> getFeaturedVOs() {
        if (featuredVOs == null) {
            featuredVOs = new ArrayList<>();
        }
        return featuredVOs;
    }

}
