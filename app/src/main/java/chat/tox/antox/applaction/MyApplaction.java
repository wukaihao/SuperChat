package chat.tox.antox.applaction;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.util.List;

import chat.tox.antox.wrapper.FriendInfo;

/**
 * Created by wqw on 2016/3/3.
 */
public class MyApplaction extends Application {
    private static MyApplaction myApplaction;
    private List<FriendInfo> friendInfoList;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplaction=this;
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(this).denyCacheImageMultipleSizesInMemory()
                .threadPriority(Thread.NORM_PRIORITY - 2).memoryCacheSize((int) Runtime.getRuntime().maxMemory() / 8)
                .diskCacheSize(50 * 1024 * 1024).diskCacheFileNameGenerator(new Md5FileNameGenerator())
//                .discCache(new UnlimitedDiskCache())向SD卡那个位置写缓存
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()).imageDownloader(new BaseImageDownloader(this,60*1000,60*1000))
                .build();
        ImageLoader.getInstance().init(configuration);
    }
    public static MyApplaction getIntance(){
        return myApplaction;
    }

    public List<FriendInfo> getFriendInfoList() {
        return friendInfoList;
    }

    public void setFriendInfoList(List<FriendInfo> friendInfoList) {
        this.friendInfoList = friendInfoList;
    }
}
