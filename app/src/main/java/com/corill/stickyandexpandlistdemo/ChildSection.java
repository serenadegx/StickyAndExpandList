package com.corill.stickyandexpandlistdemo;

/**
 * Created by hhhhh on 2017/12/19.
 */

public class ChildSection extends ParentSection {
    public String des;


    public ChildSection(boolean isParent, int type, String title, String des) {
        super(isParent, type, title);
        this.des = des;
    }
}
