package com.corill.stickyandexpandlistdemo;

/**
 * Created by hhhhh on 2017/12/19.
 */
public class BaseSection {
    public boolean isParent;
    public  int type;
    public String content;


    public BaseSection(boolean isParent, int type) {
        this.isParent = isParent;
        this.type = type;
    }

}
