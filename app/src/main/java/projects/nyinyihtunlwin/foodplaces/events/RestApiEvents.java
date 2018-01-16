package projects.nyinyihtunlwin.foodplaces.events;

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

        public PromotionsDataLoadedEvent(int loadedPageIndex, List<PromotionVO> loadedPromotions) {
            this.loadedPageIndex = loadedPageIndex;
            this.mLoadedPromotions = loadedPromotions;
        }

        public int getLoadedPageIndex() {
            return loadedPageIndex;
        }

        public List<PromotionVO> getLoadedPromotions() {
            return mLoadedPromotions;
        }
    }
    public static class GuidesDataLoadedEvent {

        private int loadedPageIndex;
        private List<GuidesVO> mloadedGuides;

        public GuidesDataLoadedEvent(int loadedPageIndex, List<GuidesVO> loadedGuides) {
            this.loadedPageIndex = loadedPageIndex;
            this.mloadedGuides = loadedGuides;
        }

        public int getLoadedPageIndex() {
            return loadedPageIndex;
        }

        public List<GuidesVO> getLoadedGuides() {
            return mloadedGuides;
        }
    }
    public static class FeaturedDataLoadedEvent {

        private int loadedPageIndex;
        private List<FeaturedVO> mloadedFeatures;

        public FeaturedDataLoadedEvent(int loadedPageIndex, List<FeaturedVO> loadedFeatures) {
            this.loadedPageIndex = loadedPageIndex;
            this.mloadedFeatures = loadedFeatures;
        }

        public int getLoadedPageIndex() {
            return loadedPageIndex;
        }

        public List<FeaturedVO> getLoadedFeatures() {
            return mloadedFeatures;
        }
    }
}
