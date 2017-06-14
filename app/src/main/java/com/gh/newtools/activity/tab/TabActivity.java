package com.gh.newtools.activity.tab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.gh.newtools.R;
import com.gh.newtools.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author: gh
 * time: 2017/6/13.
 * description:Fragment+FragmentTabHost
 * http://blog.csdn.net/yangyu20121224/article/details/9016223
 */

public class TabActivity extends BaseActivity {

    @Bind(android.R.id.tabhost)
    FragmentTabHost mTabHost;

    private LayoutInflater layoutInflater;

    //定义数组来存放Fragment界面
    private Class mFragmentArray[] = {
            MainFragment1.class,
            MainFragment2.class,
            MainFragment3.class,
            MainFragment4.class,};

    //定义数组来存放按钮图片
    private int mImageViewArray[] = {
            R.drawable.tab_home_btn,
            R.drawable.tab_message_btn,
            R.drawable.tab_more_btn,
            R.drawable.tab_setting_btn};

    //Tab选项卡的文字
    private String mTextviewArray[] = {
            "首页",
            "消息",
            "更多",
            "设置"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tab);
        ButterKnife.bind(this);

    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, TabActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {

        layoutInflater = LayoutInflater.from(this);

        mTabHost.setup(this, getSupportFragmentManager(), R.id.id_fl_content);

        //TabHost去分割线
        mTabHost.getTabWidget().setDividerDrawable(null);

        //得到fragment的个数
        int count = mFragmentArray.length;

        //新建Tabspec选项卡并设置Tab菜单栏的内容和绑定对应的Fragment
        for (int i = 0; i < count; i++) {
            //为每一个Tab按钮设置图标、文字和内容
            TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中，并绑定Fragment
            mTabHost.addTab(tabSpec, mFragmentArray[i], null);
            mTabHost.setTag(i);
            //设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.white);
//            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);
        }

    }

    /**
     * 给Tab按钮设置图标和文字
     * @param index
     * @return
     */
    private View getTabItemView(int index) {
        //将xml布局转换为view对象
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);
        //利用view对象，找到布局中的组件,并设置内容，然后返回视图
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextviewArray[index]);
        return view;
    }

}
