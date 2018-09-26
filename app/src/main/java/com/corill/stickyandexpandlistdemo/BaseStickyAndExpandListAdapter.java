package com.corill.stickyandexpandlistdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hhhhh on 2017/12/19.
 */

public abstract class BaseStickyAndExpandListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int PARENT_PART = 1;//父
    public static final int CHILD_PART = 2;//子
    private Context mContext;
    private List<BaseSection> mData;
    private RecyclerView rv;
    private View stickyHeaderView;
    /**
     * key:类型，value:存放一种类型的集合
     */
    private HashMap<Integer, ExpandBean> map;

    public BaseStickyAndExpandListAdapter(Context mContext, List<BaseSection> mData, RecyclerView rv
            , View fakeStickyHeaderView) {
        this.mContext = mContext;
        this.mData = mData;
        this.rv = rv;
        this.stickyHeaderView = fakeStickyHeaderView;
        map = new HashMap<>();
        initData();
        initListener();
    }

    private void initListener() {
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View stickyInfoView = recyclerView.findChildViewUnder(
                        stickyHeaderView.getMeasuredWidth() / 2, 5);
                if (stickyInfoView != null && stickyInfoView.getTag() != null) {
                    if (fakeListener != null) {
                        fakeListener.onFakeViewChangeListener(stickyHeaderView, stickyInfoView.getTag());
//                        tvDes.setText(String.valueOf(stickyInfoView.getContentDescription()));
                    }
                }

                View transInfoView = recyclerView.findChildViewUnder(
                        stickyHeaderView.getMeasuredWidth() / 2, stickyHeaderView.getMeasuredHeight() + 1);

                if (transInfoView != null && transInfoView.getTag() != null) {
//                    int transViewStatus = (int) transInfoView.getTag();
                    boolean isParent = ((BaseSection) transInfoView.getTag()).isParent;
                    int dealtY = transInfoView.getTop() - stickyHeaderView.getMeasuredHeight();

                    if (isParent) {
                        if (transInfoView.getTop() > 0) {
                            stickyHeaderView.setTranslationY(dealtY);
                        } else {
                            stickyHeaderView.setTranslationY(0);
                        }
                    } else {
                        stickyHeaderView.setTranslationY(0);
                    }
                }
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == PARENT_PART) {
            viewHolder = createParentViewHolder(LayoutInflater.from(mContext), parent);
        } else if (viewType == CHILD_PART) {
            viewHolder = createChildViewHolder(LayoutInflater.from(mContext), parent);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final BaseSection baseSection = mData.get(position);
        if (getItemViewType(position) == PARENT_PART) {
            parentBindViewHolder(holder, baseSection);
            holder.itemView.setTag(baseSection);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ExpandBean expandBean = map.get(baseSection.type);
                    if (expandBean != null) {
                        if (expandBean.isShow) {//当前为展开，执行收起
                            mData.removeAll(expandBean.mData);
                        } else {//当前为收起，执行展开
                            mData.addAll(position + 1, expandBean.mData);
                        }
                        expandBean.isShow = !expandBean.isShow;
                        notifyDataSetChanged();
                        if (animationListener != null) {
                            if (position == 0) {
                                animationListener.onAnimationListener(stickyHeaderView, expandBean.isShow);
                            } else {

                                animationListener.onAnimationListener(holder.itemView, expandBean.isShow);
                            }
                        }
                    }
                }
            });
        } else if (getItemViewType(position) == CHILD_PART) {
            childBindViewHolder(holder, baseSection);
            holder.itemView.setTag(baseSection);
        }
        holder.itemView.setContentDescription(baseSection.content);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).isParent ? PARENT_PART : CHILD_PART;
    }

    private void initData() {
        int index = -1;
        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).isParent) {
                index = mData.get(i).type;
                map.put(index, new ExpandBean(new ArrayList<BaseSection>()));
            } else {
                if (mData.get(i).type == index)
                    map.get(index).mData.add(mData.get(i));
            }
        }
    }

    public abstract RecyclerView.ViewHolder createChildViewHolder(LayoutInflater inflater, ViewGroup parent);

    public abstract RecyclerView.ViewHolder createParentViewHolder(LayoutInflater inflater, ViewGroup parent);

    public abstract void parentBindViewHolder(RecyclerView.ViewHolder holder, BaseSection baseSection);

    public abstract void childBindViewHolder(RecyclerView.ViewHolder holder, BaseSection baseSection);

    private FakeViewBindDataListener fakeListener;

    public void setFakeListener(FakeViewBindDataListener fakeListener) {
        this.fakeListener = fakeListener;
    }

    public interface FakeViewBindDataListener {
        void onFakeViewChangeListener(View fakeView, Object obj);
    }

    private OpenOrCloseAnimationListener animationListener;

    public void setAnimationListener(OpenOrCloseAnimationListener animationListener) {
        this.animationListener = animationListener;
    }

    public interface OpenOrCloseAnimationListener {
        void onAnimationListener(View itemView, boolean isShow);
    }
}
