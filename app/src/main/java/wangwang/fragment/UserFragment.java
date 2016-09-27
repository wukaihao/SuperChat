package wangwang.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import chat.tox.antox.R;
import chat.tox.antox.activities.SettingsActivity1;
import chat.tox.antox.activities.UserSettingsActivity;
import chat.tox.antox.data.State;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {
    private LinearLayout my_ll_01,my_ll_06;
    private LinearLayout my_ll_07;
    private LinearLayout my_ll_08;

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view=getView();
        my_ll_01=(LinearLayout)view.findViewById(R.id.my_ll_01);
        my_ll_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserSettingsActivity.class);
                startActivity(intent);
            }
        });
        my_ll_06=(LinearLayout)view.findViewById(R.id.my_ll_06);
        my_ll_06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingsActivity1.class);
                startActivity(intent);
            }
        });
        my_ll_07 = (LinearLayout)view.findViewById(R.id.my_ll_07);
        my_ll_07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"it is coming",Toast.LENGTH_SHORT).show();
            }
        });
        my_ll_08 = (LinearLayout)view.findViewById(R.id.my_ll_08);
        my_ll_08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建退出提示框
                AlertDialog isExit = new AlertDialog.Builder(getActivity()).create();
                //设置图片
                isExit.setIcon(R.drawable.logo);
                //设置对话框标题
                isExit.setTitle("系统提示");
                //设置对话框提示
                isExit.setMessage("确定退出？");
                //设置两个监听按钮
                isExit.setButton(AlertDialog.BUTTON_POSITIVE, "确定", listener);
                isExit.setButton(AlertDialog.BUTTON_NEGATIVE, "取消", listener);
                isExit.show();
            }
            //监听对话框里的Button按钮
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
                            State.logout(getActivity());
                            getActivity().finish();
                            break;
                        case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
                            break;
                        default:
                            break;
                    }
                }
            };
        });
    }

}
