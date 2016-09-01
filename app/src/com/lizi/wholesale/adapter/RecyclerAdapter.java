package com.lizi.wholesale.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lizi.wholesale.R;
import com.lizi.wholesale.adapter.holder.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 * Time on 2016/1/15.
 * Description
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<String> mDataSource;
    private int mHeaderCount = 1;//头部View个数
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;

    public RecyclerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mDataSource = new ArrayList<>();
    }

    //判断当前item类型
    @Override
    public int getItemViewType(int position) {
        if (mHeaderCount != 0 && position < mHeaderCount) {
            //头部View
            return ITEM_TYPE_HEADER;
        } else {
            //内容View
            return ITEM_TYPE_CONTENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_HEADER) {
            View view = mLayoutInflater.inflate(R.layout.pull_to_refresh_header, parent, false);
            return new HeaderViewHolder(view);
        } else {
            View view = mLayoutInflater.inflate(R.layout.activity_recycle_refresh_item, parent, false);
            return new ContentViewHolder(view);
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {

        } else if (holder instanceof ContentViewHolder) {
            ((ContentViewHolder) holder).text.setText(mDataSource.get(position-mHeaderCount));

        }

    }


    //内容 ViewHolder
    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);

        }
    }

    //内容 ViewHolder
    public static class ContentViewHolder extends RecyclerView.ViewHolder {
        private TextView text;

        public ContentViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }

    @Override
    public int getItemCount() {
        return mDataSource.size() + mHeaderCount;
    }

    public void resetList(List<String> list) {
        mDataSource.clear();
        mDataSource.addAll(list);
        notifyDataSetChanged();
    }

    public void addList(List<String> list) {
        mDataSource.addAll(list);
        notifyDataSetChanged();
    }
}
