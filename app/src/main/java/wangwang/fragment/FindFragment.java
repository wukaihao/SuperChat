package wangwang.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import chat.tox.antox.R;
import chat.tox.antox.activities.AddActivity1;
import chat.tox.antox.activities.Main4Activity;
import chat.tox.antox.activities.PublishActivity;
import wangwang.activity.SharkActivity;
import wangwang.zxing.Scanning.CaptureActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends Fragment {

    private LinearLayout find_bt_02;
    private LinearLayout find_bt_03;
    private LinearLayout find_bt_07;
    public FindFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_find, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view=getView();
        find_bt_02=(LinearLayout)view.findViewById(R.id.find_bt_02);
        find_bt_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Main4Activity.class);
                startActivity(intent);
            }
        });
        find_bt_03=(LinearLayout)view.findViewById(R.id.find_bt_03);
        find_bt_03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SharkActivity.class);
                startActivity(intent);
            }
        });
        find_bt_07=(LinearLayout)view.findViewById(R.id.find_bt_07);
        find_bt_07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), AddActivity1.class);
                startActivity(intent);
//                Intent intent=new Intent(getActivity(), PublishActivity.class);
//                startActivity(intent);
            }
        });
    }
}
