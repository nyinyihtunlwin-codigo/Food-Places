package projects.nyinyihtunlwin.foodplaces.network;

import projects.nyinyihtunlwin.foodplaces.network.responses.GetFeaturedResponse;
import projects.nyinyihtunlwin.foodplaces.network.responses.GetGuidesResponse;
import projects.nyinyihtunlwin.foodplaces.network.responses.GetPromotionsResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Dell on 1/16/2018.
 */

public interface FoodPlacesAPI {

    @FormUrlEncoded
    @POST("v1/getPromotions.php")
    Call<GetPromotionsResponse> loadPromotions(
            @Field("page") int pageIndex,
            @Field("access_token") String accessToken);

    @FormUrlEncoded
    @POST("v1/getFeatured.php")
    Call<GetFeaturedResponse> loadFeatures(
            @Field("page") int pageIndex,
            @Field("access_token") String accessToken);

    @FormUrlEncoded
    @POST("v1/getGuides.php")
    Call<GetGuidesResponse> loadGuides(
            @Field("page") int pageIndex,
            @Field("access_token") String accessToken);

}
