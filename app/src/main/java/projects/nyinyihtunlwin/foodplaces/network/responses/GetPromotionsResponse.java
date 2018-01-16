package projects.nyinyihtunlwin.foodplaces.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import projects.nyinyihtunlwin.foodplaces.data.vo.PromotionVO;

/**
 * Created by Dell on 1/15/2018.
 */

public class GetPromotionsResponse extends BaseResponse {

    @SerializedName("promotions")
    private List<PromotionVO> promotions;

    public List<PromotionVO> getPromotions() {
        if (promotions == null) {
            promotions = new ArrayList<>();
        }
        return promotions;
    }
}
