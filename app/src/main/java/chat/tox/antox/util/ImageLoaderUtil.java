package chat.tox.antox.util;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import chat.tox.antox.R;

/**
 * Created by Administrator on 2015/10/9.
 */
public class ImageLoaderUtil {
    private static DisplayImageOptions optionsBig=new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.default_ic_account).showImageOnFail(R.drawable.default_ic_account)
            .showImageForEmptyUri(R.drawable.default_ic_account).cacheInMemory(true)
            .cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565)
            .resetViewBeforeLoading(true).imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
            .displayer(new FadeInBitmapDisplayer(200)).build();
    public static void display(String uri,ImageView imageview){
        ImageLoader.getInstance().displayImage(uri,imageview,optionsBig);
    }
}
