package com.corill.stickyandexpandlistdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hhhhh on 2017/12/19.
 */

public class TestAdapter extends BaseStickyAndExpandListAdapter {
    public TestAdapter(Context mContext, List<BaseSection> mData, RecyclerView rv, View fakeStickyHeaderView) {
        super(mContext, mData, rv, fakeStickyHeaderView);
    }

    @Override
    public RecyclerView.ViewHolder createChildViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new ChildViewHolder(inflater.inflate(R.layout.item_child, parent, false));
    }

    @Override
    public RecyclerView.ViewHolder createParentViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new ParentViewHolder(inflater.inflate(R.layout.item_parent, parent, false));
    }

    @Override
    public void parentBindViewHolder(RecyclerView.ViewHolder holder, BaseSection baseSection) {
        ParentSection parentSection = (ParentSection) baseSection;
        ParentViewHolder parentViewHolder = (ParentViewHolder) holder;
        parentViewHolder.tvDes.setText(parentSection.title);
    }

    @Override
    public void childBindViewHolder(RecyclerView.ViewHolder holder, BaseSection baseSection) {
        ChildSection childSection = (ChildSection) baseSection;
        ChildViewHolder childViewHolder = (ChildViewHolder) holder;
        childViewHolder.tvTest.setText(childSection.des);
    }

    public static class ParentViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDes;
        public ImageView ivEx;
        public RelativeLayout rlHeader;

        public ParentViewHolder(View itemView) {
            super(itemView);
            tvDes = (TextView) itemView.findViewById(R.id.tv_des);
            ivEx = (ImageView) itemView.findViewById(R.id.iv_ex);
            rlHeader = (RelativeLayout) itemView.findViewById(R.id.rl_header);
        }
    }

    public static class ChildViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTest;

        public ChildViewHolder(View itemView) {
            super(itemView);
            tvTest = (TextView) itemView.findViewById(R.id.tv_test);
        }
    }
}
