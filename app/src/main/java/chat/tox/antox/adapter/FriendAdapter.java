package chat.tox.antox.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import chat.tox.antox.R;
import chat.tox.antox.applaction.MyApplaction;
import chat.tox.antox.entity.Friendreview;
import chat.tox.antox.entity.Friendster;
import chat.tox.antox.wrapper.FriendInfo;

/**
 * Created by wqw on 2016/3/3.
 */
public class FriendAdapter extends BaseAdapter {
    private Context context;
    private List<Friendster> myData;
    ViewHolder holder = null;
    private String parisename=null;
    private PopupWindow popupWindow;
    private String textpraise="";
    private String textpinglun="";
//    private int praiserid=0;
    ArrayList<String> urls=null;
    private NoScrollGridAdapter noScrollGridAdapter;
    public FriendAdapter(){

    }
    public FriendAdapter(Context context, List<Friendster> myData) {
        this.context = context;
        this.myData = myData;
    }

    @Override
    public int getCount() {
        return myData.size();
    }

    @Override
    public Object getItem(int position) {
        return myData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.circle_item, null);
            holder = new ViewHolder();
            holder.item_image=(ImageView)convertView.findViewById(R.id.item_image);
            holder.name=(TextView)convertView.findViewById(R.id.item_text_name);
            holder.content=(TextView)convertView.findViewById(R.id.item_text_content);
            holder.gridView=(NoScrollGridView)convertView.findViewById(R.id.item_image_gridView);
            holder.linear=(LinearLayout)convertView.findViewById(R.id.item_linear);
            holder.time=(TextView)convertView.findViewById(R.id.itrm_publish_time);
            holder.parise=(ImageButton)convertView.findViewById(R.id.item_image_parise);
            holder.penglun=(ImageButton)convertView.findViewById(R.id.item_image_penglun);
            holder.tparise=(TextView)convertView.findViewById(R.id.item_text_parise);
            holder.tpenglun=(TextView)convertView.findViewById(R.id.item_list_penglun);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        final Friendster friendster=myData.get(position);
        textpraise="觉得很赞";
        textpinglun="";
        holder.name.setText(toxname(friendster.getToxid()));
        holder.content.setText(friendster.getContent());
        holder.time.setText(friendster.getTime());
        wwimage(friendster.getToxid(), holder.item_image);
        Log.d("time==", friendster.getTime());
        holder.time.setText(mistiming(friendster.getTime()));
        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        urls=new ArrayList<>();
        Log.d("qqqqq",friendster.toString());
        Log.d("ssss",""+friendster.getImages());
        if(friendster.getImages()!=null){
            for(int i=0;i<friendster.getImages().size();i++){
                urls.add(friendster.getImages().get(i).getImage());
            }
            noScrollGridAdapter=new NoScrollGridAdapter(context,urls);
            holder.gridView.setAdapter(noScrollGridAdapter);
        }else {
            holder.gridView.setVisibility(View.GONE);
        }
        holder.parise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userkey=pref.getString("tox_id", "");
                userkey=userkey.substring(0,64);
                OkHttpClient mOkHttpClient = new OkHttpClient();
                String url="http://icoral.cn:1199/review/";
                RequestBody formBody = new FormEncodingBuilder()
                        .add("toxid",userkey)
                        .add("relatedid", friendster.getId() + "")
                        .add("praise", "1")
                        .build();
                Request request = new Request.Builder()
                        .url(url)
                        .post(formBody)
                        .build();
                mOkHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        Toast.makeText(context, "网络异常....", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        String s = response.body().string();
                        Log.d("s===", s);
                        if (s.equals("[]")) {
                            //Toast.makeText(context,"点赞失败",Toast.LENGTH_SHORT).show();
                        } else {

                        }

                    }
                });
                Friendreview fw=new Friendreview(null,0,friendster.getId(),1,null,userkey);
                friendster.getReviews().add(fw);
                notifyDataSetChanged();
            }
        });
        holder.penglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int id = friendster.getId();
                getPopupWindow(friendster);
                popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
            }
        });
        if(friendster.getReviews()!=null){
            Log.d("review====",friendster.getReviews()+"");
            List<Friendreview> data=friendster.getReviews();
            String text1="";
            String text2="";
            for(int i=data.size()-1;i>=0;i--){
                Friendreview fs=data.get(i);
                if(fs.getPraise()==1){
                    if(fs.getId()!=0){
                        if(textpraise.equals("觉得很赞")){
                            textpraise=toxname(fs.getToxid())+textpraise;
                        }else {
                            textpraise=toxname(fs.getToxid())+"、"+textpraise;
                        }
                    }else {
                        text1=toxname(fs.getToxid());
                    }

                }else {
                    if(fs.getId()!=0){
                        textpinglun=textpinglun+toxname(fs.getToxid())+": "+fs.getContent()+"\n";
                    }else {
                        text2=toxname(fs.getToxid())+": "+fs.getContent()+"\n";
                    }
                }
                String tid=pref.getString("tox_id", "");
                tid=tid.substring(0,64);
                if(tid.equals(fs.getToxid())){
                    holder.parise.setEnabled(false);
                }
            }
            if(textpraise.equals("觉得很赞")&&textpinglun.equals("")) {
                holder.linear.setVisibility(View.GONE);
            }else {
                holder.linear.setVisibility(View.VISIBLE);
            }
            if(!text1.equals("")) {
                if (textpraise.equals("觉得很赞")) {
                    textpraise = text1 + textpraise;
                } else {
                    holder.tparise.setVisibility(View.VISIBLE);
                    textpraise = text1 + "、" + textpraise;
                }
            }
            holder.tparise.setText(textpraise);
            if(textpraise.equals("觉得很赞")){
                holder.tparise.setVisibility(View.GONE);
            }
            if(!text2.equals("")){
                holder.tpenglun.setVisibility(View.VISIBLE);
                textpinglun=textpinglun+text2;
            }
            holder.tpenglun.setText(textpinglun);
            if(textpinglun.equals("")){
                holder.tpenglun.setVisibility(View.GONE);
            }
        }

        return convertView;
    }

    public class ViewHolder{
        public ImageView item_image;
        public TextView name;
        public TextView content;
        public NoScrollGridView gridView;
        public LinearLayout linear;
        public TextView time;
        public ImageButton parise;
        public ImageButton penglun;
        public TextView tparise;
        public TextView tpenglun;
    }
    protected void initPopuptWindow(final Friendster fw) {
        // TODO Auto-generated method stub
        View popupWindow_view = LayoutInflater.from(context).inflate(R.layout.pinglun, null,
                false);

        popupWindow = new PopupWindow(popupWindow_view, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, true);
        final EditText editText=(EditText)popupWindow_view.findViewById(R.id.item_edit_content);
        Button button=(Button)popupWindow_view.findViewById(R.id.item_bt_fs);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String content1 = editText.getText().toString();
                Log.d("content===",content1);
                if (content1.equals("") || content1 == null) {
                    Toast.makeText(context, "评论不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
                    String userkey = pref.getString("tox_id", "");
                    userkey = userkey.substring(0, 64);
                    OkHttpClient mOkHttpClient = new OkHttpClient();
                    String url = "http://icoral.cn:1199/review/";
                    RequestBody formBody = new FormEncodingBuilder()
                            .add("toxid", userkey)
                            .add("content", content1)
                            .add("relatedid", fw.getId() + "")
                            .build();
                    Request request = new Request.Builder()
                            .url(url)
                            .post(formBody)
                            .build();
                    mOkHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            Toast.makeText(context, "网络异常....", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(Response response) throws IOException {
                            String s = response.body().string();
                            Log.d("SPL===", s);
                        }
                    });
                    Friendreview fe=new Friendreview(content1,0,fw.getId(),0,null,userkey);
                    fw.getReviews().add(fe);
                    notifyDataSetChanged();
                }
            }
        });

        popupWindow_view.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }
                return false;
            }
        });
    }


    private void getPopupWindow(Friendster fw) {
        if (null != popupWindow) {
            popupWindow.dismiss();
            return;
        } else {
            initPopuptWindow(fw);
        }
    }
    public String toxname(String toxid){
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
        String tid=preferences.getString("tox_id", "");
        tid=tid.substring(0,64);
        if(toxid.equals(tid)){
            String nickname=preferences.getString("nickname", "");
            Log.d(nickname, nickname);
            return nickname;
        }else {
            List<FriendInfo> list= MyApplaction.getIntance().getFriendInfoList();
            for(int i=0;i<list.size();i++){
                String b=list.get(i).key().toString();
//                Log.d("name",);
                if(toxid.equals(b)){
                    return list.get(i).getDisplayName();
                }
            }
        }
        return null;
    }
    public void wwimage(String toxid,ImageView imageView){
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
        String tid=preferences.getString("tox_id", "");
        tid=tid.substring(0,64);
        if(toxid.equals(tid)){
//            String nickname=preferences.getString("nickname", "");
//            Log.d(nickname, nickname);
            String avatar1=preferences.getString("avatar", "");
            Log.d(avatar1,avatar1);
            File avatar = new File(avatar1);
            if (avatar.exists()) {
                imageView.setImageURI(Uri.fromFile(avatar));
            } else {
                imageView.setImageResource(R.drawable.default_ic_account);
            }
        }else {
//            List<FriendInfo> list= MyApplaction.getIntance().getFriendInfoList();
//            for(int i=0;i<list.size();i++){
//                String b=list.get(i).key().toString();
//                if(toxid.equals(b)){
//                    File av=list.get(i).avatar().get();
//                    if (av.exists()) {
//                        imageView.setImageURI(Uri.fromFile(av));
//                    } else {
//                        imageView.setImageResource(R.drawable.ic_action_contact);
//                    }
//                }
//            }
        }
    }

    public String mistiming(String time){
        String texttime="";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date result_date;
        long result_time = 0;
        if (null == time)
            result_time = System.currentTimeMillis();
        else {
            sdf.setTimeZone(TimeZone.getTimeZone("GMT00:00"));
            try {
                result_date = sdf.parse(time);
                result_time = result_date.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
                result_time = System.currentTimeMillis();
            }
        }
            Date now = null;
//        Date date=null;
        try {
            now = df.parse(df.format(new Date()));
//            date=df.parse(df.format(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long l=now.getTime()-result_time;
        long day=l/(24*60*60*1000);
        long hour=(l/(60*60*1000)-day*24);
        long min=((l/(60*1000))-day*24*60-hour*60);
        if(day>365){
            int a= (int) (day%365);
            texttime=a+"年以前";
        }else if(day>30){
            int a=(int)(day%30);
            texttime=a+"个月以前";
        }else if(day>0){
            int a=(int)day;
            texttime=a+"天以前";
        }else {
            if(hour>0){
                int a=(int)hour;
                texttime=a+"小时以前";
            }else {
                if(min>0) {
                    int a = (int) min;
                    texttime = a + "分钟以前";
                }else {
                    texttime = "刚刚";
                }
            }
        }
        return texttime;
    }

}