package projects.nyinyihtunlwin.foodplaces.events;

import android.content.Context;

import java.util.List;

import projects.nyinyihtunlwin.foodplaces.data.vo.FeaturedVO;
import projects.nyinyihtunlwin.foodplaces.data.vo.GuidesVO;
import projects.nyinyihtunlwin.foodplaces.data.vo.PromotionVO;

/**
 * Created by Dell on 1/16/2018.
 */

public class RestApiEvents {

    public static class ErrorInvokingAPIEvent {
        private String errorMsg;

        public ErrorInvokingAPIEvent(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String getErrorMsg() {
            return errorMsg;
        }
    }

    public static class PromotionsDataLoadedEvent {

        private int loadedPageIndex;
        private List<PromotionVO> mLoadedPromotions;
        private Context context;

        public PromotionsDataLoadedEvent(int loadedPageIndex, List<PromotionVO> loadedPromotions, Context context) {
            this.loadedPageIndex = loadedPageIndex;
            this.mLoadedPromotions = loadedPromotions;
            this.context = context;
        }

        public int getLoadedPageIndex() {
            return loadedPageIndex;
        }

        public List<PromotionVO> getLoadedPromotions() {
            return mLoadedPromotions;
        }

        public Context getContext() {
            return context;
        }
    }

    public static class GuidesDataLoadedEvent {

        private int loadedPageIndex;
        private List<GuidesVO> mloadedGuides;
        private Context context;

        public GuidesDataLoadedEvent(int loadedPageIndex, List<GuidesVO> loadedGuides,Context context) {
            this.loadedPageIndex = loadedPageIndex;
            this.mloadedGuides = loadedGuides;
            this.context=context;
        }

        public int getLoadedPageIndex() {
            return loadedPageIndex;
        }

        public List<GuidesVO> getLoadedGuides() {
            return mloadedGuides;
        }

        public Context getContext() {
            return context;
        }
    }

    public static class FeaturedDataLoadedEvent {

        private int loadedPageIndex;
        private List<FeaturedVO> mloadedFeatures;
        private Context context;

        public FeaturedDataLoadedEvent(int loadedPageIndex, List<FeaturedVO> loadedFeatures,Context context) {
            this.loadedPageIndex = loadedPageIndex;
            this.mloadedFeatures = loadedFeatures;
            this.context=context;
        }

        public int getLoadedPageIndex() {
            return loadedPageIndex;
        }

        public List<FeaturedVO> getLoadedFeatures() {
            return mloadedFeatures;
        }

        public Context getContext() {
            return context;
        }
    }
}
