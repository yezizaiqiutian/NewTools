package com.gh.newtools.activity.string;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gh.newtools.R;
import com.gh.newtools.base.BaseActivity;
import com.gh.newtools.utils.StringUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: gh
 * time: 2017/6/28.
 * Github:
 * description:对StringUtils的应用
 */

public class StringActivity extends BaseActivity {

    @Bind(R.id.id_et_input)
    EditText id_et_input;
    @Bind(R.id.id_tv_output)
    TextView id_tv_output;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, StringActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_string);
        ButterKnife.bind(this);

    }

    @OnClick({
            R.id.id_btn_1,
            R.id.id_btn_2,
            R.id.id_btn_3,
            R.id.id_btn_4,
            R.id.id_btn_5,
            R.id.id_btn_6,
            R.id.id_btn_7,
            R.id.id_btn_8,
            R.id.id_btn_9,
            R.id.id_btn_10,
            R.id.id_btn_11,
            R.id.id_btn_12,
            R.id.id_btn_13
    })
    public void onViewClicked(View view) {

        String inPut = id_et_input.getText().toString().trim();

        String outPut = "";

        switch (view.getId()) {

            case R.id.id_btn_1:
                outPut = StringUtils.getChsAscii(inPut)+"";
                break;
            case R.id.id_btn_2:
                outPut = StringUtils.convert(inPut);
                break;
            case R.id.id_btn_3:
                outPut = StringUtils.getSelling(inPut);
                break;
            case R.id.id_btn_4:
                outPut = StringUtils.parseEmpty(inPut);
                break;
            case R.id.id_btn_5:
                outPut = StringUtils.isEmpty(inPut)+"";
                break;
            case R.id.id_btn_6:
                outPut = StringUtils.chineseLength(inPut)+"";
                break;
            case R.id.id_btn_7:
                outPut = StringUtils.strLength(inPut)+"";
                break;
            case R.id.id_btn_8:
                outPut = StringUtils.subStringLength(inPut,4)+"";
                break;
            case R.id.id_btn_9:
                outPut = StringUtils.isChinese(inPut)+"";
                break;
            case R.id.id_btn_10:
                outPut = StringUtils.isContainChinese(inPut)+"";
                break;
            case R.id.id_btn_11:
                outPut = StringUtils.strFormat2(inPut)+"";
                break;
            case R.id.id_btn_12:
                outPut = StringUtils.convert2Int(inPut,4)+"";
                break;
            case R.id.id_btn_13:
                outPut = StringUtils.decimalFormat(Double.valueOf(inPut),"0.0")+"";
                break;
        }

        id_tv_output.setText(outPut);

    }
}
