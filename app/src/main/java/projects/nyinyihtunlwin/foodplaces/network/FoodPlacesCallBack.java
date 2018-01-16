package projects.nyinyihtunlwin.foodplaces.network;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import projects.nyinyihtunlwin.foodplaces.FoodPlacesApp;
import projects.nyinyihtunlwin.foodplaces.events.RestApiEvents;
import projects.nyinyihtunlwin.foodplaces.network.responses.BaseResponse;
import projects.nyinyihtunlwin.foodplaces.network.responses.GetFeaturedResponse;
import projects.nyinyihtunlwin.foodplaces.network.responses.GetGuidesResponse;
import projects.nyinyihtunlwin.foodplaces.network.responses.GetPromotionsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dell on 1/16/2018.
 */

public class FoodPlacesCallBack<T extends BaseResponse> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        T body = response.body();
        if (body == null) {
            String errorMsg;
            if (body instanceof GetPromotionsResponse) {
                errorMsg = "No promotions could be loaded for now.Please try again later.";
            } else if (body instanceof GetGuidesResponse) {
                errorMsg = "No guides could be loaded for now.Please try again later.";
            } else if (body instanceof GetFeaturedResponse) {
                errorMsg = "No features could be loaded for now.Please try again later.";
            } else {
                errorMsg = "No data could be loaded for now.Please try again later.";
            }
            RestApiEvents.ErrorInvokingAPIEvent errorEvent
                    = new RestApiEvents.ErrorInvokingAPIEvent(errorMsg);
            EventBus.getDefault().post(errorEvent);
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }
}
