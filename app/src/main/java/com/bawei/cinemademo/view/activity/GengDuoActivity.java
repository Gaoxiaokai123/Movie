package com.bawei.cinemademo.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.cinemademo.R;
import com.bawei.cinemademo.adapter.FragAdapter;
import com.bawei.cinemademo.app.App;
import com.bawei.cinemademo.base.BaseActivity;
import com.bawei.cinemademo.frag.tabgengduo.TabGengduoComingsoon;
import com.bawei.cinemademo.frag.tabgengduo.TabGengduoHot;
import com.bawei.cinemademo.frag.tabgengduo.TabGengduoRelease;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GengDuoActivity extends BaseActivity {

    @BindView(R.id.tab_gengduo_tab)
    TabLayout tabGdTab;
    @BindView(R.id.tab_gengduo_frag)
    ViewPager tabGdPager;
    @BindView(R.id.head_dingwei)
    ImageView headDingwei;
    @BindView(R.id.head_text)
    TextView headText;
    @BindView(R.id.head_suosou)
    ImageView headSuosou;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_geng_duo;
    }

    @Override
    protected void initView() {
//        String trim = headText.getText().toString().trim();
//                int [] str = {R.mipmap.suosounull};
//
//        if (trim == null){
////            Glide.with(GengDuoActivity.this).load(R.mipmap.suosounull).into(gengduoImg);
//            Glide.with(App.context).load(str[0]).into(gengduoImg);
//
//        }else {
//
//        }

        List<Fragment> frag = new ArrayList<>();

        frag.add(new TabGengduoHot());
        frag.add(new TabGengduoRelease());
        frag.add(new TabGengduoComingsoon());
        FragAdapter fragAdapter = new FragAdapter(getSupportFragmentManager(), frag);
        tabGdPager.setAdapter(fragAdapter);
        for (int i = 0; i < 3; i++) {
            tabGdTab.addTab(tabGdTab.newTab());
        }

        tabGdTab.setupWithViewPager(tabGdPager);
        tabGdTab.getTabAt(0).setText("正在上映");
        tabGdTab.getTabAt(1).setText("即将上映");
        tabGdTab.getTabAt(2).setText("热门电影");


        //点击更多之后跳转
        int id = getIntent().getIntExtra("id", 0);
        if (id == 1) {
            tabGdTab.getTabAt(0).select();

        } else if (id == 2) {
            tabGdTab.getTabAt(1).select();

        } else if (id == 3) {
            tabGdTab.getTabAt(2).select();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
