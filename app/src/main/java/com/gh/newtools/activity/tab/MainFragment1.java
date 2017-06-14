package com.gh.newtools.activity.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.gh.newtools.R;
import com.gh.newtools.base.BaseFragment;

/**
 * author: gh
 * time: 2017/6/7.
 * description:
 */

public class MainFragment1 extends BaseFragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view= inflater.inflate(R.layout.fragment_main1, container, false);

        //对View中控件的操作方法
        Button btn = (Button)view.findViewById(R.id.fragment1_btn);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "点击了第一个fragment的BTN", Toast.LENGTH_SHORT).show();
//                MainActivity.actionStart(getContext());
            }
        });
        return view;
    }

}
