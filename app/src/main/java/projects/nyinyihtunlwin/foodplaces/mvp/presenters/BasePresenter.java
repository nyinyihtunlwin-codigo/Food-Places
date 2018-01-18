package projects.nyinyihtunlwin.foodplaces.mvp.presenters;

/**
 * Created by Dell on 1/19/2018.
 */

public abstract class BasePresenter<T> {

    T mView;

    public void onCreate(T mView) {
        this.mView = mView;
    }

    public abstract void onStart();

    public void onResume() {

    }

    public void onPause() {

    }

    public abstract void onStop();

    public void onDestroy() {

    }
}
