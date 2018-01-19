package projects.nyinyihtunlwin.foodplaces.network;

import android.content.Context;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import projects.nyinyihtunlwin.foodplaces.events.RestApiEvents;
import projects.nyinyihtunlwin.foodplaces.network.responses.GetFeaturedResponse;
import projects.nyinyihtunlwin.foodplaces.network.responses.GetGuidesResponse;
import projects.nyinyihtunlwin.foodplaces.network.responses.GetPromotionsResponse;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dell on 1/15/2018.
 */

public class FoodPlacesDataAgentImpl implements FoodPlacesDataAgent {

    private FoodPlacesAPI mFoodPlacesAPI;

    public FoodPlacesDataAgentImpl() {
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://padcmyanmar.com/padc-3/burpple-food-places/apis/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(okHttpClient)
                .build();

        mFoodPlacesAPI = retrofit.create(FoodPlacesAPI.class);
    }

    @Override
    public void loadPromotions(String accessToken, final int page, final Context context) {
        Call<GetPromotionsResponse> loadPromotionsCall = mFoodPlacesAPI.loadPromotions(page, accessToken);
        loadPromotionsCall.enqueue(new FoodPlacesCallBack<GetPromotionsResponse>() {
            @Override
            public void onResponse(Call<GetPromotionsResponse> call, Response<GetPromotionsResponse> response) {
                super.onResponse(call, response);
                GetPromotionsResponse getPromotionsResponse = response.body();
                if (getPromotionsResponse != null
                        && getPromotionsResponse.getPromotions().size() > 0) {
                    RestApiEvents.PromotionsDataLoadedEvent promotionsDataLoadedEvent
                            = new RestApiEvents.PromotionsDataLoadedEvent(getPromotionsResponse.getPage(), getPromotionsResponse.getPromotions(), context);
                    EventBus.getDefault().post(promotionsDataLoadedEvent);
                }
            }
        });
    }

    @Override
    public void loadGuides(String accessToken, int page, final Context context) {
        Call<GetGuidesResponse> loadGuidesCall = mFoodPlacesAPI.loadGuides(page, accessToken);
        loadGuidesCall.enqueue(new FoodPlacesCallBack<GetGuidesResponse>() {
            @Override
            public void onResponse(Call<GetGuidesResponse> call, Response<GetGuidesResponse> response) {
                super.onResponse(call, response);
                GetGuidesResponse getGuidesResponse = response.body();
                if (getGuidesResponse != null
                        && getGuidesResponse.getGuides().size() > 0) {
                    RestApiEvents.GuidesDataLoadedEvent guidesDataLoadedEvent
                            = new RestApiEvents.GuidesDataLoadedEvent(getGuidesResponse.getPage(), getGuidesResponse.getGuides(), context);
                    EventBus.getDefault().post(guidesDataLoadedEvent);
                }
            }
        });
    }

    @Override
    public void loadFeatured(String accessToken, int page, final Context context) {
        Call<GetFeaturedResponse> loadFeatureCall = mFoodPlacesAPI.loadFeatures(page, accessToken);
        loadFeatureCall.enqueue(new FoodPlacesCallBack<GetFeaturedResponse>() {
            @Override
            public void onResponse(Call<GetFeaturedResponse> call, Response<GetFeaturedResponse> response) {
                super.onResponse(call, response);
                GetFeaturedResponse getFeaturedResponse = response.body();
                if (getFeaturedResponse != null
                        && getFeaturedResponse.getFeaturedVOs().size() > 0) {
                    RestApiEvents.FeaturedDataLoadedEvent featuredDataLoadedEvent
                            = new RestApiEvents.FeaturedDataLoadedEvent(getFeaturedResponse.getPage(), getFeaturedResponse.getFeaturedVOs(), context);
                    EventBus.getDefault().post(featuredDataLoadedEvent);
                }
            }
        });
    }
}
