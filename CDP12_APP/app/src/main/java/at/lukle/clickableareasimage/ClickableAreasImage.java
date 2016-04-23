package at.lukle.clickableareasimage;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Lukas on 10/22/2015.
 */
public class ClickableAreasImage implements PhotoViewAttacher.OnPhotoTapListener{

    private PhotoViewAttacher attacher;
    private OnClickableAreaClickedListener listener;

    private List<ClickableArea> clickableAreas;

    private int imageWidthInPx;
    private int imageHeightInPx;

    //
    private int pixel_x;
    private int pixel_y;


    public ClickableAreasImage(PhotoViewAttacher attacher, OnClickableAreaClickedListener listener){
        this.attacher = attacher;
        init(listener);
    }

    private void init(OnClickableAreaClickedListener listener) {
        this.listener = listener;
        getImageDimensions(attacher.getImageView());
        attacher.setOnPhotoTapListener(this);
    }


    private void getImageDimensions(ImageView imageView){
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        imageWidthInPx = (int) (drawable.getBitmap().getWidth() / Resources.getSystem().getDisplayMetrics().density);
        imageHeightInPx = (int) (drawable.getBitmap().getHeight() / Resources.getSystem().getDisplayMetrics().density);
    }

    @Override
    public void onPhotoTap(View view, float x, float y) {
        PixelPosition pixel = ImageUtils.getPixelPosition(x, y, imageWidthInPx, imageHeightInPx);
        List<ClickableArea> clickableAreas = getClickAbleAreas(pixel.getX(), pixel.getY());
        for(ClickableArea ca : clickableAreas){
            listener.onClickableAreaTouched(ca.getItem());
        }

        pixel_x = pixel.getX();
        pixel_y = pixel.getY();
        Log.i("ClickableAreasImage"," X, Y :" + pixel_x + "  " + pixel_y);

    }

    private List<ClickableArea> getClickAbleAreas(int x, int y){
        List<ClickableArea> clickableAreas= new ArrayList<>();
        for(ClickableArea ca : getClickableAreas()){
            if(isBetween(ca.getX(),(ca.getX()+ca.getW()),x)){
                if(isBetween(ca.getY(),(ca.getY()+ca.getH()),y)){
                    clickableAreas.add(ca);
                }
            }
        }
        return clickableAreas;
    }

    private boolean isBetween(int start, int end, int actual){
        return (start <= actual && actual <= end);
    }

    public void setClickableAreas(List<ClickableArea> clickableAreas) {
        this.clickableAreas = clickableAreas;
    }

    public List<ClickableArea> getClickableAreas() {
        return clickableAreas;
    }

   //----------------------------------------------------------------------------   차승훈 추가
    public void onOutsidePhotoTap() {

    }

    public ArrayList<String> getPixel() {

        ArrayList<String> arr = new ArrayList();

        arr.add(String.valueOf(pixel_x));
        arr.add(String.valueOf(pixel_y));

        return arr;
    }


}
