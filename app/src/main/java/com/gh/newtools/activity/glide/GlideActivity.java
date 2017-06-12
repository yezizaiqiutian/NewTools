package com.gh.newtools.activity.glide;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gh.newtools.R;
import com.gh.newtools.base.BaseActivity;
import com.gh.newtools.widget.glide.transform.GlideCircleTransform;
import com.gh.newtools.widget.glide.transform.GlideRoundTransform;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author: gh
 * time: 2017/6/8.
 * description:
 */

public class GlideActivity extends BaseActivity {

    @Bind(R.id.id_iv_circle)
    ImageView id_iv_circle;
    @Bind(R.id.id_iv_round)
    ImageView id_iv_round;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, GlideActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {

        Glide.with(mActivity).load(R.mipmap.test_img1).transform(new GlideCircleTransform(this)).into(id_iv_circle);
        Glide.with(mActivity).load(R.mipmap.test_img1).transform(new GlideRoundTransform(this)).into(id_iv_round);

    }
}
