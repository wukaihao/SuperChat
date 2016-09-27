package chat.tox.antox.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import chat.tox.antox.R;

public class PublishActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText fb_content;
    private Button fb_button;
    private View contentview;
    private PopupWindow popupWindow=null;
    private ImageView fb_image,fb_image1,fb_image2,fb_image3,fb_image4,fb_image5,fb_image6,fb_image7,fb_image8;

    private static final int PHONE_PHOTO=0;//相册
    private static final int TAKE_PHOTO=1;//相机
    private static final int RESULT_PHOTO=2;
    private int a=0;
    List<File> list =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        fb_content=(EditText)findViewById(R.id.fb_content);
        fb_button=(Button)findViewById(R.id.fb_button);
        fb_image=(ImageView)findViewById(R.id.fb_image);
        fb_image1=(ImageView)findViewById(R.id.fb_image1);
        fb_image2=(ImageView)findViewById(R.id.fb_image2);
        fb_image3=(ImageView)findViewById(R.id.fb_image3);
        fb_image4=(ImageView)findViewById(R.id.fb_image4);
        fb_image5=(ImageView)findViewById(R.id.fb_image5);
        fb_image6=(ImageView)findViewById(R.id.fb_image6);
        fb_image7=(ImageView)findViewById(R.id.fb_image7);
        fb_image8=(ImageView)findViewById(R.id.fb_image8);
        fb_button.setOnClickListener(this);
        fb_image.setOnClickListener(this);
        fb_image1.setOnClickListener(this);
        fb_image1.setVisibility(View.GONE);
        fb_image2.setOnClickListener(this);
        fb_image2.setVisibility(View.GONE);
        fb_image3.setOnClickListener(this);
        fb_image3.setVisibility(View.GONE);
        fb_image4.setOnClickListener(this);
        fb_image4.setVisibility(View.GONE);
        fb_image5.setOnClickListener(this);
        fb_image5.setVisibility(View.GONE);
        fb_image6.setOnClickListener(this);
        fb_image6.setVisibility(View.GONE);
        fb_image7.setOnClickListener(this);
        fb_image7.setVisibility(View.GONE);
        fb_image8.setOnClickListener(this);
        fb_image8.setVisibility(View.GONE);

        list = new ArrayList<File>();
        contentview=this.getLayoutInflater().inflate(R.layout.userinfo_popwindow, null);
        contentview.findViewById(R.id.button).setOnClickListener(this);
        contentview.findViewById(R.id.button2).setOnClickListener(this);
        contentview.findViewById(R.id.button3).setOnClickListener(this);
    }
    public void fasong(){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String userkey=pref.getString("tox_id", "");
        userkey=userkey.substring(0,64);
        OkHttpClient mOkHttpClient = new OkHttpClient();
        String url="http://icoral.cn:1199/twitter/";
//        List<RequestBody> list1=new ArrayList<>();
//        for(File file : list) {
//            if(file.exists()) {
//                RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
//                list1.add(fileBody);
//            }
//        }
        Request request=null;            //请求
        Log.d("userKey===",userkey);
        RequestBody formBody=new FormEncodingBuilder()
                .add("toxid",userkey)
                .add("content",fb_content.getText().toString())
                .build();

        if(list.size()>0) {
            MultipartBuilder multipartBuilder = new MultipartBuilder().type(MultipartBuilder.FORM);
            multipartBuilder.addFormDataPart("toxid",userkey);
            multipartBuilder.addFormDataPart("content",fb_content.getText().toString());
            for (File file : list) {
                if (file.exists()) {
                    multipartBuilder.addFormDataPart("media", file.getName(), RequestBody.create(MediaType.parse("image/jpg"), file));
                }
            }
            request= new Request.Builder()
                    .url(url)
                    .post(multipartBuilder.build())
                    .build();
        }else {
            request= new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();
        }
//        RequestBody formBody = new MultipartBuilder().type(MultipartBuilder.FORM)
//                .addPart(Headers.of(
//                                "Content-Disposition",
//                                "form-data; name=\"toxid\""),
//                        RequestBody.create(null, userkey))
//                .addPart(Headers.of(
//                        "Content-Disposition",
//                        "form-data; name=\"content\""),  RequestBody.create(null, fb_content.getText().toString()))
//                .build();

//        FormEncodingBuilder builder1 = new FormEncodingBuilder();
//        builder1.add("content", fb_content.getText().toString());



        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(getApplicationContext(), "网络异常....", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String s = response.body().string();
                Log.d("s===", s);
                if (s.equals("[]")) {
//                    Toast.makeText(getApplicationContext(),"发表失败",Toast.LENGTH_SHORT).show();
                } else {
//                    Gson gson = new Gson();
//                    Friendster data = gson.fromJson(s, new TypeToken<Friendster>() {
//                    }.getType());
//                    if (data != null && data.getId() > 0) {
//                        Toast.makeText(getApplicationContext(), "发表成功", Toast.LENGTH_SHORT).show();
//                        //数据是使用Intent返回
//                        Intent intent = new Intent();
//                        //把返回数据存入Intent
//                        intent.putExtra("result", "ok");
//                        //设置返回数据
//                        PublishActivity.this.setResult(RESULT_OK, intent);
                    finish();
//                    }
                }

            }
        });
    }
    public PopupWindow getPopwindow(View view){
        PopupWindow popupWindow=new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT
                ,LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        WindowManager.LayoutParams layoutParams=getWindow().getAttributes();
        layoutParams.alpha=0.6f;
        getWindow().setAttributes(layoutParams);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(findViewById(R.id.fb_image), Gravity.BOTTOM, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                layoutParams.alpha = 1f;
                getWindow().setAttributes(layoutParams);
            }
        });
        return popupWindow;
    }
    private String capturePath="";
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fb_button:
                fasong();
                break;
            case R.id.fb_image:
                popupWindow=getPopwindow(contentview);
                break;
            case R.id.fb_image1:
                popupWindow=getPopwindow(contentview);
                break;
            case R.id.fb_image2:
                popupWindow=getPopwindow(contentview);
                break;
            case R.id.fb_image3:
                popupWindow=getPopwindow(contentview);
                break;
            case R.id.fb_image4:
                popupWindow=getPopwindow(contentview);
                break;
            case R.id.fb_image5:
                popupWindow=getPopwindow(contentview);
                break;
            case R.id.fb_image6:
                popupWindow=getPopwindow(contentview);
                break;
            case R.id.fb_image7:
                popupWindow=getPopwindow(contentview);
                break;
            case R.id.fb_image8:
                popupWindow=getPopwindow(contentview);
                break;
            case R.id.button:
                String state= Environment.getExternalStorageState();
                if(state.equals(Environment.MEDIA_MOUNTED)){
                    Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
                    File root = Environment.getExternalStorageDirectory();
                    File myDir = new File(root, getPackageName()+"/image");
                    if (!myDir.exists()) {
                        myDir.mkdir();
                    }
                    capturePath=myDir.getPath()+File.separatorChar+System.currentTimeMillis()+".jpg";
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(capturePath)));
                    intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);
                    startActivityForResult(intent,TAKE_PHOTO);
                }else {
                    Toast.makeText(getApplicationContext(),"SD卡不可用",Toast.LENGTH_LONG).show();
                }
                popupWindow.dismiss();
                break;
            case R.id.button2:
                Intent intent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,PHONE_PHOTO);
                popupWindow.dismiss();
                break;
            case R.id.button3:
                popupWindow.dismiss();
                break;
            default:

                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!= Activity.RESULT_OK){
            return;
        }
        switch (requestCode){
            case PHONE_PHOTO:
                Cursor cursor=this.getContentResolver().query(data.getData(), new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                cursor.moveToFirst();

                String capturePath1=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                Log.d("capturepath1======",capturePath1);
                cursor.close();
                list.add(new File(capturePath1));
                startPhoneZoom(Uri.fromFile(new File(capturePath1)));
                break;
            case  TAKE_PHOTO:
                list.add(new File(capturePath));
                startPhoneZoom(Uri.fromFile(new File(capturePath)));
                break;
            case RESULT_PHOTO:
                Bundle bundle=data.getExtras();
                if(bundle!=null){
                    Bitmap bitmap=bundle.getParcelable("data");
                    switch (a){
                        case 0:
                            fb_image.setImageBitmap(bitmap);
                            fb_image1.setVisibility(View.VISIBLE);
                            a=1;
                            break;
                        case 1:
                            fb_image1.setImageBitmap(bitmap);
                            fb_image2.setVisibility(View.VISIBLE);
                            a=2;
                            break;
                        case 2:
                            fb_image2.setImageBitmap(bitmap);
                            fb_image3.setVisibility(View.VISIBLE);
                            a=3;
                            break;
                        case 3:
                            fb_image3.setImageBitmap(bitmap);
                            fb_image4.setVisibility(View.VISIBLE);
                            a=4;
                            break;
                        case 4:
                            fb_image4.setImageBitmap(bitmap);
                            fb_image5.setVisibility(View.VISIBLE);
                            a=5;
                            break;
                        case 5:
                            fb_image5.setImageBitmap(bitmap);
                            fb_image6.setVisibility(View.VISIBLE);
                            a=6;
                            break;
                        case 6:
                            fb_image6.setImageBitmap(bitmap);
                            fb_image7.setVisibility(View.VISIBLE);
                            a=7;
                            break;
                        case 7:
                            fb_image7.setImageBitmap(bitmap);
                            fb_image8.setVisibility(View.VISIBLE);
                            a=8;
                            break;
                        case 8:
                            fb_image8.setImageBitmap(bitmap);
                            break;
                    }
//                    if(a==0) {
//                        fb_image1.setVisibility(View.VISIBLE);
//                        a=1;
//                    }else {
//                        fb_image1.setImageBitmap(bitmap);
//                    }
//                    alterDialogshow(bitmap);
//                    Notification.Builder nob=new Notification.Builder(getBaseContext()).setContentTitle("通知")
//                            .setContentText("下载中").setSmallIcon(R.drawable.logo)
//                            //.setContentIntent(new PendingIntent(MainActivity_grxx.this, Main2Activity.class))
//                            .setProgress(100, 0, false);
//                    Notification nf=nob.build();
//                    NotificationManager nfm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//                    nfm.notify(1,nf);
                }
                break;
            default:
                break;
        }
    }
    public void startPhoneZoom(Uri uri){
        Intent intent=new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri,"image/*");
        //设置裁剪
        intent.putExtra("crop","true");
        //设置宽度，高度比例
        intent.putExtra("aspectX",1);
        intent.putExtra("aspectY",1);
        intent.putExtra("outputX",300);
        intent.putExtra("outputY",300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, RESULT_PHOTO);
    }
}
