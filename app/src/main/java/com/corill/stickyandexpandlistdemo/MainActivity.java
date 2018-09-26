package com.corill.stickyandexpandlistdemo;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        RelativeLayout rlFake = (RelativeLayout) findViewById(R.id.rl_header);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecorationCustom(this, DividerItemDecorationCustom.VERTICAL_LIST));

        List<BaseSection> sections = new ArrayList<>();
        createData(sections);

        TestAdapter adapter = new TestAdapter(this, sections, rv, rlFake);
        adapter.setFakeListener(new BaseStickyAndExpandListAdapter.FakeViewBindDataListener() {
            @Override
            public void onFakeViewChangeListener(View fakeView, Object obj) {
                ParentSection section = (ParentSection) obj;
                ((TextView)fakeView.findViewById(R.id.tv_des)).setText(section.title);
            }
        });
        adapter.setAnimationListener(new BaseStickyAndExpandListAdapter.OpenOrCloseAnimationListener() {
            @Override
            public void onAnimationListener(View itemView, boolean isShow) {
                ImageView imageView = (ImageView) itemView.findViewById(R.id.iv_ex);
                ObjectAnimator rotateAnim;
                if (isShow) {
                    rotateAnim = ObjectAnimator.ofFloat(imageView, "rotation", 180, 0);
//                rotateAnim.setRepeatMode(ObjectAnimator.REVERSE);
                }else {
                    rotateAnim = ObjectAnimator.ofFloat(imageView, "rotation", 0, 180);
                }
                rotateAnim.setDuration(500);
                rotateAnim.start();
            }
        });
        rv.setAdapter(adapter);
    }

    private void createData(List<BaseSection> sections) {
        sections.add(new ParentSection(true, 1, "2017-12-18 共4场比赛可投注1"));
        sections.add(new ChildSection(false, 1, "2017-12-18 共4场比赛可投注1", "1"));
        sections.add(new ChildSection(false, 1, "2017-12-18 共4场比赛可投注1", "2"));
        sections.add(new ChildSection(false, 1, "2017-12-18 共4场比赛可投注1", "3"));
        sections.add(new ChildSection(false, 1, "2017-12-18 共4场比赛可投注1", "4"));
        sections.add(new ChildSection(false, 1, "2017-12-18 共4场比赛可投注1", "5"));
        sections.add(new ChildSection(false, 1, "2017-12-18 共4场比赛可投注1", "6"));
        sections.add(new ChildSection(false, 1, "2017-12-18 共4场比赛可投注1", "7"));
        sections.add(new ParentSection(true, 2, "2017-12-18 共4场比赛可投注2"));
        sections.add(new ChildSection(false, 2, "2017-12-18 共4场比赛可投注2", "1"));
        sections.add(new ChildSection(false, 2, "2017-12-18 共4场比赛可投注2", "2"));
        sections.add(new ChildSection(false, 2, "2017-12-18 共4场比赛可投注2", "3"));
        sections.add(new ChildSection(false, 2, "2017-12-18 共4场比赛可投注2", "4"));
        sections.add(new ChildSection(false, 2, "2017-12-18 共4场比赛可投注2", "5"));
        sections.add(new ChildSection(false, 2, "2017-12-18 共4场比赛可投注2", "6"));
        sections.add(new ParentSection(true, 3, "2017-12-18 共4场比赛可投注3"));
        sections.add(new ChildSection(false, 3, "2017-12-18 共4场比赛可投注3", "1"));
        sections.add(new ChildSection(false, 3, "2017-12-18 共4场比赛可投注3", "2"));
        sections.add(new ChildSection(false, 3, "2017-12-18 共4场比赛可投注3", "3"));
        sections.add(new ChildSection(false, 3, "2017-12-18 共4场比赛可投注3", "4"));
        sections.add(new ChildSection(false, 3, "2017-12-18 共4场比赛可投注3", "5"));
        sections.add(new ChildSection(false, 3, "2017-12-18 共4场比赛可投注3", "6"));
        sections.add(new ParentSection(true, 4, "2017-12-18 共4场比赛可投注4"));
        sections.add(new ChildSection(false, 4, "2017-12-18 共4场比赛可投注4", "1"));
        sections.add(new ChildSection(false, 4, "2017-12-18 共4场比赛可投注4", "2"));
        sections.add(new ChildSection(false, 4, "2017-12-18 共4场比赛可投注4", "3"));
        sections.add(new ChildSection(false, 4, "2017-12-18 共4场比赛可投注4", "4"));
        sections.add(new ChildSection(false, 4, "2017-12-18 共4场比赛可投注4", "5"));
        sections.add(new ChildSection(false, 4, "2017-12-18 共4场比赛可投注4", "6"));
        sections.add(new ParentSection(true, 5, "2017-12-18 共4场比赛可投注5"));
        sections.add(new ChildSection(false, 5, "2017-12-18 共4场比赛可投注5", "1"));
        sections.add(new ChildSection(false, 5, "2017-12-18 共4场比赛可投注5", "2"));
        sections.add(new ChildSection(false, 5, "2017-12-18 共4场比赛可投注5", "3"));
        sections.add(new ChildSection(false, 5, "2017-12-18 共4场比赛可投注5", "4"));
        sections.add(new ChildSection(false, 5, "2017-12-18 共4场比赛可投注5", "5"));
        sections.add(new ChildSection(false, 5, "2017-12-18 共4场比赛可投注5", "6"));
    }
}
