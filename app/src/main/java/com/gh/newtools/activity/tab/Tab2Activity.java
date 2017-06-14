package com.gh.newtools.activity.tab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

import com.gh.newtools.R;
import com.gh.newtools.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author: gh
 * time: 2017/6/13.
 * description:FragmentTabHost+ViewPager+Fragment
 * http://blog.csdn.net/carson_ho/article/details/51533543
 */

public class Tab2Activity extends BaseActivity implements ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener {

    @Bind(android.R.id.tabhost)
    FragmentTabHost mTabHost;
    @Bind(R.id.id_viewpager)
    ViewPager id_viewpager;

    //定义一个布局
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

    private List<Fragment> mFragmentList = new ArrayList<Fragment>();

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, Tab2Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab2);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {

        layoutInflater = LayoutInflater.from(this);

        /**
         * 设置TabHost
         */
        //实例化FragmentTabHost对象并进行绑定
        mTabHost.setup(this, getSupportFragmentManager(), R.id.id_viewpager);//绑定viewpager

        mTabHost.setOnTabChangedListener(this);

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
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.white);//设置Tab被选中的时候颜色改变
//            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);//设置Tab被选中的时候颜色改变
        }

        /**
         * 设置ViewPager
         */
        /*实现OnPageChangeListener接口,目的是监听Tab选项卡的变化，然后通知ViewPager适配器切换界面*/
        /*简单来说,是为了让ViewPager滑动的时候能够带着底部菜单联动*/
        id_viewpager.addOnPageChangeListener(this);//设置页面切换时的监听器

        MainFragment1 fragment1 = new MainFragment1();
        MainFragment2 fragment2 = new MainFragment2();
        MainFragment3 fragment3 = new MainFragment3();
        MainFragment4 fragment4 = new MainFragment4();

        mFragmentList.add(fragment1);
        mFragmentList.add(fragment2);
        mFragmentList.add(fragment3);
        mFragmentList.add(fragment4);

        //绑定Fragment适配器
        id_viewpager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), mFragmentList));

    }

    /**
     * 给Tab按钮设置图标和文字
     *
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

    /**
     * @param state state ==1的时候表示正在滑动，state==2的时候表示滑动完毕了，state==0的时候表示什么都没做，就是停在那。
     */
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 表示在前一个页面滑动到后一个页面的时候，在前一个页面滑动前调用的方法
     *
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * @param position 表示你当前选中的页面位置Postion，这事件是在你页面跳转完毕的时候调用的。
     */
    @Override
    public void onPageSelected(int position) {//
        TabWidget widget = mTabHost.getTabWidget();
        int oldFocusability = widget.getDescendantFocusability();
        widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);//设置View覆盖子类控件而直接获得焦点
        mTabHost.setCurrentTab(position);//根据位置Postion设置当前的Tab
        widget.setDescendantFocusability(oldFocusability);//设置取消分割线

    }

    @Override
    public void onTabChanged(String tabId) {//Tab改变的时候调用
        int position = mTabHost.getCurrentTab();
        id_viewpager.setCurrentItem(position);//把选中的Tab的位置赋给适配器，让它控制页面切换
    }
}
