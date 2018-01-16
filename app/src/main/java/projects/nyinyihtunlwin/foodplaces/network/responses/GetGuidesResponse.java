package projects.nyinyihtunlwin.foodplaces.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import projects.nyinyihtunlwin.foodplaces.data.vo.GuidesVO;

/**
 * Created by Dell on 1/15/2018.
 */

public class GetGuidesResponse extends BaseResponse {

    @SerializedName("featured")
    private List<GuidesVO> guides;

    public List<GuidesVO> getGuides() {
        if (guides == null) {
            guides = new ArrayList<>();
        }
        return guides;
    }
}
