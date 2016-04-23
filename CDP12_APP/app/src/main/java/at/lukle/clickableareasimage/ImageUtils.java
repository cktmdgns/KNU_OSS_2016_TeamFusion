package at.lukle.clickableareasimage;

import android.util.Log;

/**
 * Created by Lukas on 10/22/2015.
 */
public class ImageUtils {
    public static PixelPosition getPixelPosition(float percentX, float percentY, int absW, int absH){
        int absX = Math.round(absW * percentX);
        int absY = Math.round(absH * percentY);
        Log.i("ImageUtils"," x / y : test / : x : " + absX +  "  y : " + absY);
        return new PixelPosition(absX,absY);
    }
}
