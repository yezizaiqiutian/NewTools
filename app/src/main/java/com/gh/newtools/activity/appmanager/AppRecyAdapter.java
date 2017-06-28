package com.gh.newtools.activity.appmanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gh.newtools.R;
import com.gh.newtools.utils.AppUtils;
import com.gh.newtools.utils.L;

import java.util.List;

/**
 * author: gh
 * time: 2017/6/27.
 * Github:
 * description:
 */

public class AppRecyAdapter extends RecyclerView.Adapter<AppRecyAdapter.AppViewHolder> {
    private Context mContext;
    private List<AppBean> mDatas;
    private LayoutInflater mLayoutInflater;

    public AppRecyAdapter(Context context, List<AppBean> list) {
        this.mContext = context;
        this.mDatas = list;
        this.mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AppViewHolder(mLayoutInflater.inflate(R.layout.item_applist, parent, false));
    }

    @Override
    public int getItemCount() {
        return mDatas.size() != 0 ? mDatas.size() : 0;
    }

    @Override
    public void onBindViewHolder(AppViewHolder holder, int position) {
        holder.tv_name.setText(mDatas.get(position).getAppName());
        holder.iv_icon.setImageDrawable(mDatas.get(position).getAppIcon());
    }

    class AppViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        ImageView iv_icon;

        public AppViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.recy_name);
            iv_icon = (ImageView) itemView.findViewById(R.id.recy_icon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    L.i(mDatas.get(getAdapterPosition()).getAppPackage(), ""+getAdapterPosition());
                    //Toast.makeText(mContext, "点击了Item" + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    AppUtils.runApp(mContext, mDatas.get(getAdapterPosition()).getAppPackage());
                }
            });
        }
    }
}
