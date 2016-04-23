package mobilelecture.cdp12_app;

/**
 * Created by user on 2016-04-23.
 */
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

public class MergeImageView extends ImageView {

    private SparseArray<Bitmap> bitmaps = new SparseArray<>();
    private Picasso picasso;
    private final int DEFAULT_IMAGE_SIZE = 50;
    private int MIN_IMAGE_SIZE = DEFAULT_IMAGE_SIZE;
    private int MAX_WIDTH = DEFAULT_IMAGE_SIZE * 2, MAX_HEIGHT = DEFAULT_IMAGE_SIZE * 2;
    private String picassoRequestTag = null;

    public MergeImageView(Context context) {
        super(context);
    }

    public MergeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MergeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MergeImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean isInEditMode() {
        return true;
    }

    public void clearResources() {
        if (bitmaps != null) {
            for (int i = 0; i < bitmaps.size(); i++)
                bitmaps.get(i).recycle();
            bitmaps.clear();
        }
        // cancel picasso requests
        //if (picasso != null && AppUtils.ifNotNullEmpty(picassoRequestTag))
        if (picasso != null )
            picasso.cancelTag(picassoRequestTag);
        picasso = null;
        bitmaps = null;
    }

    public void createMergedBitmap(Context context, List<String> imageUrls, String picassoTag) {
        picasso = Picasso.with(context);
        int count = imageUrls.size();
        picassoRequestTag = picassoTag;

        boolean isEven = count % 2 == 0;
        // if url size are not even make MIN_IMAGE_SIZE even
        MIN_IMAGE_SIZE = DEFAULT_IMAGE_SIZE + (isEven ? count / 2 : (count / 2) + 1);
        // set MAX_WIDTH and MAX_HEIGHT to twice of MIN_IMAGE_SIZE
        MAX_WIDTH = MAX_HEIGHT = MIN_IMAGE_SIZE * 2;
        // in case of odd urls increase MAX_HEIGHT
        if (!isEven) MAX_HEIGHT = MAX_WIDTH + MIN_IMAGE_SIZE;

        // create default bitmap
        Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.android_con),
                MIN_IMAGE_SIZE, MIN_IMAGE_SIZE, false);

        // change default height (wrap_content) to MAX_HEIGHT
        //int height = Math.round(AppUtils.convertDpToPixel(MAX_HEIGHT, context));
        int height = 366;
        setMinimumHeight(height * 2);

        // start AsyncTask
        for (int index = 0; index < count; index++) {
            // put default bitmap as a place holder
            bitmaps.put(index, bitmap);
            new PicassoLoadImage(index, imageUrls.get(index)).execute();
            // if you want parallel execution use
            // new PicassoLoadImage(index, imageUrls.get(index)).(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    private class PicassoLoadImage extends AsyncTask<String, Void, Bitmap> {

        private int index = 0;
        private String url;

        PicassoLoadImage(int index, String url) {
            this.index = index;
            this.url = url;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                // synchronous picasso call
                return picasso.load(url).resize(MIN_IMAGE_SIZE, MIN_IMAGE_SIZE).tag(picassoRequestTag).get();
            } catch (IOException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap output) {
            super.onPostExecute(output);
            if (output != null)
                bitmaps.put(index, output);

            // create canvas
            Bitmap.Config conf = Bitmap.Config.RGB_565;
            Bitmap canvasBitmap = Bitmap.createBitmap(MAX_WIDTH, MAX_HEIGHT, conf);
            Canvas canvas = new Canvas(canvasBitmap);
            canvas.drawColor(Color.WHITE);

            // if height and width are equal we have even images
            boolean isEven = MAX_HEIGHT == MAX_WIDTH;
            int imageSize = bitmaps.size();
            int count = imageSize;

            // we have odd images
            if (!isEven) count = imageSize - 1;
            for (int i = 0; i < count; i++) {
                Bitmap bitmap = bitmaps.get(i);
                canvas.drawBitmap(bitmap, bitmap.getWidth() * (i % 2), bitmap.getHeight() * (i / 2), null);
            }
            // if images are not even set last image width to MAX_WIDTH
            if (!isEven) {
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmaps.get(count), MAX_WIDTH, MIN_IMAGE_SIZE, false);
                canvas.drawBitmap(scaledBitmap, scaledBitmap.getWidth() * (count % 2), scaledBitmap.getHeight() * (count / 2), null);
            }
            // set bitmap
            setImageBitmap(canvasBitmap);
        }
    }
}