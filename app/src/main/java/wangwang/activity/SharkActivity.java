package wangwang.activity;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;


import java.io.IOException;
import java.util.HashMap;

import chat.tox.antox.R;
import wangwang.entity.ShakeListener;

public class SharkActivity extends AppCompatActivity {
    private final int DURATION_TIME = 600;
    private ShakeListener mShakeListener = null;
    private Vibrator mVibrator;
    private RelativeLayout mImgUp;
    private RelativeLayout mImgDn;
    private SoundPool sndPool;
    private HashMap<Integer, Integer> soundPoolMap = new HashMap<Integer, Integer>();
    private android.support.v7.app.ActionBar sab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shark_main);
        sab = getSupportActionBar();
        sab.setTitle("摇一摇");
        sab.setDisplayShowHomeEnabled(true);
        sab.setDisplayHomeAsUpEnabled(true);//返回箭头
        sab.setDisplayShowTitleEnabled(true);//显示程序标题
        initView();
        mVibrator = (Vibrator) getApplication().getSystemService(
                VIBRATOR_SERVICE);
// 检查设备是否有震动装置
// mVibrator.hasVibrator();
        loadSound();
        mShakeListener = new ShakeListener(this);
// 监听到手机摇动
        mShakeListener.setOnShakeListener(new ShakeListener.OnShakeListener() {
            public void onShake() {
                startAnim();
            }
        });
    }
    /****
     * 初始化控件
     */
    private void initView() {
// TODO Auto-generated method stub
        mImgUp = (RelativeLayout) findViewById(R.id.shakeImgUp);
        mImgDn = (RelativeLayout) findViewById(R.id.shakeImgDown);
    }
    /****
     * 获取音效
     */
    private void loadSound() {
        sndPool = new SoundPool(2, AudioManager.STREAM_SYSTEM, 5);
        new Thread() {
            public void run() {
                try {
                    soundPoolMap.put(
                            0,
                            sndPool.load(
                                    getAssets().openFd(
                                            "sound/shake_sound_male.mp3"), 1));
                    soundPoolMap.put(1, sndPool.load(
                            getAssets().openFd("sound/shake_match.mp3"), 1));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    public void startAnim() {
        AnimationSet animup = new AnimationSet(true);
        TranslateAnimation mytranslateanimup0 = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
                -0.5f);
        mytranslateanimup0.setDuration(DURATION_TIME);
        TranslateAnimation mytranslateanimup1 = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
                +0.5f);
        mytranslateanimup1.setDuration(DURATION_TIME);
        mytranslateanimup1.setStartOffset(DURATION_TIME);
        animup.addAnimation(mytranslateanimup0);
        animup.addAnimation(mytranslateanimup1);
        mImgUp.startAnimation(animup);
        AnimationSet animdn = new AnimationSet(true);
        TranslateAnimation mytranslateanimdn0 = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
                +0.5f);
        mytranslateanimdn0.setDuration(DURATION_TIME);
        TranslateAnimation mytranslateanimdn1 = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
                -0.5f);
        mytranslateanimdn1.setDuration(DURATION_TIME);
        mytranslateanimdn1.setStartOffset(DURATION_TIME);
        animdn.addAnimation(mytranslateanimdn0);
        animdn.addAnimation(mytranslateanimdn1);
        mImgDn.startAnimation(animdn);
// 动画监听，开始时显示加载状态，
        mytranslateanimdn0.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mShakeListener.stop();
//                sndPool.play(soundPoolMap.get(0), (float) 0.2, (float) 0.2, 0,
//                        0, (float) 0.6);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(getBaseContext(), "摇一摇结束", Toast.LENGTH_SHORT)
                        .show();
                mShakeListener.start();
            }
        });
    }
    @Override
    protected void onDestroy() {
// TODO Auto-generated method stub
        super.onDestroy();
        if (mShakeListener != null) {
            mShakeListener.stop();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_shark_main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
