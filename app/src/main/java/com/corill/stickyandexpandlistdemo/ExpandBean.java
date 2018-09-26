package com.corill.stickyandexpandlistdemo;

import java.util.List;

/**
 * Created by hhhhh on 2017/12/19.
 */

public class ExpandBean {
    public boolean isShow = true;
    public List<BaseSection> mData;

    public ExpandBean() {
    }

    public ExpandBean(List<BaseSection> mData) {
        this.mData = mData;
    }

    public ExpandBean(boolean isShow, List<BaseSection> mData) {
        this.isShow = isShow;
        this.mData = mData;
    }
}
