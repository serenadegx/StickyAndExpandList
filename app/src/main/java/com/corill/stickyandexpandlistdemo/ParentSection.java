package com.corill.stickyandexpandlistdemo;

/**
 * Created by hhhhh on 2017/12/19.
 */

public class ParentSection extends BaseSection {
    public String title;

    public ParentSection(boolean isParent, int type, String title) {
        super(isParent, type);
        this.title = title;
    }
}
