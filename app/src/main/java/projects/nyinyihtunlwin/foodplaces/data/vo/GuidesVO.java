package projects.nyinyihtunlwin.foodplaces.data.vo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import projects.nyinyihtunlwin.foodplaces.persistence.FoodPlacesContract;

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

    public ContentValues parseToContentValues() {

        ContentValues contentValues = new ContentValues();

        contentValues.put(FoodPlacesContract.GuidesEntry.COLUMN_GUIDE_ID, guideId);
        contentValues.put(FoodPlacesContract.GuidesEntry.COLUMN_GUIDE_IMAGE, guideImage);
        contentValues.put(FoodPlacesContract.GuidesEntry.COLUMN_GUIDE_TITLE, guideTitle);
        contentValues.put(FoodPlacesContract.GuidesEntry.COLUMN_GUIDE_DESC, guideDesc);

        return contentValues;
    }

    public static GuidesVO parseFromCursor(Context context, Cursor cursor) {

        GuidesVO guide = new GuidesVO();

        guide.guideId = cursor.getString(cursor.getColumnIndex(FoodPlacesContract.GuidesEntry.COLUMN_GUIDE_ID));
        guide.guideImage = cursor.getString(cursor.getColumnIndex(FoodPlacesContract.GuidesEntry.COLUMN_GUIDE_IMAGE));
        guide.guideTitle = cursor.getString(cursor.getColumnIndex(FoodPlacesContract.GuidesEntry.COLUMN_GUIDE_TITLE));
        guide.guideDesc = cursor.getString(cursor.getColumnIndex(FoodPlacesContract.GuidesEntry.COLUMN_GUIDE_DESC));

        return guide;
    }
}
