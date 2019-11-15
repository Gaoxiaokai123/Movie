package com.bawei.cinemademo.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bawei.cinemademo.R;
import com.bawei.cinemademo.base.BaseActivity;
import com.bawei.cinemademo.frag.FragCinema;
import com.bawei.cinemademo.frag.FragHome;
import com.bawei.cinemademo.frag.FragMy;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends FragmentActivity {

    @BindView(R.id.home_frame)
    FrameLayout homeFrame;
    @BindView(R.id.home_btn1)
    RadioButton homeBtn1;
    @BindView(R.id.home_btn2)
    RadioButton homeBtn2;
    @BindView(R.id.home_btn3)
    RadioButton homeBtn3;
    @BindView(R.id.home_radiogroup)
    RadioGroup homeRadiogroup;
    private FragmentManager manager;
    private FragHome fragHome;
    private FragCinema fragCinema;
    private FragMy fragMy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        manager = getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        fragHome = new FragHome();
        fragCinema = new FragCinema();
        fragMy = new FragMy();
        transaction.add(R.id.home_frame, fragHome);
        transaction.add(R.id.home_frame, fragCinema);
        transaction.add(R.id.home_frame, fragMy);
        //默认展示fragHome 首页fragment
        transaction.show(fragHome).hide(fragCinema).hide(fragMy);
        transaction.commit(); //提交

        //第一个按钮默认选中
        homeRadiogroup.check(homeRadiogroup.getChildAt(0).getId());
        homeRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //创建新事物
                FragmentTransaction transaction1 = manager.beginTransaction();
                switch (checkedId){
                    case R.id.home_btn1:
                        transaction1.show(fragHome).hide(fragCinema).hide(fragMy);
                        break;
                    case R.id.home_btn2:
                        transaction1.show(fragCinema).hide(fragHome).hide(fragMy);

                        break;
                    case R.id.home_btn3:
                        transaction1.show(fragMy).hide(fragCinema).hide(fragHome);
                }
                //提交事务
                transaction1.commit();

            }
        });

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onAppExit();
            return true;
        }
        return false;
    }

    private long firstClick;
    public void onAppExit() {
        if (System.currentTimeMillis() - this.firstClick > 2000L) {
            this.firstClick = System.currentTimeMillis();
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_LONG).show();
            return;
        }
        finish();
    }



}
