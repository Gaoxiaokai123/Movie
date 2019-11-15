package com.bawei.cinemademo.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bawei.cinemademo.R;
import com.bawei.cinemademo.app.App;
import com.bawei.cinemademo.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @describe(描述)：MainActivity
 * @data（日期）: 2019/11/5
 * @time（时间）: 17:04
 * @author（作者）: 盖磊
 **/
public class MainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.main_pager)
    ViewPager mainPager;
    @BindView(R.id.main_radioGroup)
    RadioGroup mainRadioGroup;
    @BindView(R.id.main_btn)
    Button mainBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ///注意一进来 就要给sp存值
        SharedPreferences sp = getSharedPreferences("ydy", App.context.MODE_PRIVATE);
        //判断是否 使用过引导页
        if (sp.getBoolean("key", false)) {
            //跳转页面
            startActivity(new Intent(MainActivity.this,HomeActivity.class));
            //然后finish()
            //再然后return;
        }
        //如果不是
        sp.edit().putBoolean("key", true).commit();
        mainBtn.setOnClickListener(this);
        //一进来先让button隐藏
        mainBtn.setVisibility(View.GONE);
        int arr[] = {R.mipmap.lunbo1, R.mipmap.lunbo2, R.mipmap.lunbo3};
        final ArrayList<ImageView> list = new ArrayList<ImageView>();
        // 给Radiobutton加距离
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
//                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        // /设置间距
        params.leftMargin = 10;
        for (int i = 0; i < arr.length; i++) {
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageResource(arr[i]);
            list.add(imageView);
            // 设置小圆点
            RadioButton button = new RadioButton(MainActivity.this);
            // 设置选择器
//            button.setButtonDrawable(R.drawable.sel_r);
            // 把RadioButton和间距添加到radioGroup中
            mainRadioGroup.addView(button, params);
        }
// 选中第一个
        mainRadioGroup.check(mainRadioGroup.getChildAt(0).getId());
        mainPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                ImageView imageView1 = list.get(position);

                return imageView1;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//                super.destroyItem(container, position, object);

                container.removeView((View) object);
            }
        });

        //实现小圆点页面联动,
        mainPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mainRadioGroup.check(mainRadioGroup.getChildAt(i).getId());
                if (i == list.size()-1){
                    mainBtn.setVisibility(View.VISIBLE);
                }else {
                    mainBtn.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(MainActivity.this,HomeActivity.class));
    }


//
//    @BindView(R.id.main_pager)
//    ViewPager mainPager;
//    @BindView(R.id.main_radioGroup)
//    RadioGroup mainRadioGroup;
//    @BindView(R.id.main_btn)
//    Button mainBtn;
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_main;
//    }
//
//    @Override
//    protected void initView() {
//        ///注意一进来 就要给sp存值
//        SharedPreferences sp = getSharedPreferences("ydy", App.context.MODE_PRIVATE);
//        //判断是否 使用过引导页
//        if (sp.getBoolean("key", false)) {
//            //跳转页面
//            startActivity(new Intent(MainActivity.this,HomeActivity.class));
//            //然后finish()
//            //再然后return;
//        }
//        //如果不是
//        sp.edit().putBoolean("key", true).commit();
//        mainBtn.setOnClickListener(this);
//        //一进来先让button隐藏
//        mainBtn.setVisibility(View.GONE);
//        int arr[] = {R.mipmap.lunbo1, R.mipmap.lunbo2, R.mipmap.lunbo3};
//        final ArrayList<ImageView> list = new ArrayList<ImageView>();
//        // 给Radiobutton加距离
//        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
////                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//        // /设置间距
//        params.leftMargin = 10;
//        for (int i = 0; i < arr.length; i++) {
//            ImageView imageView = new ImageView(MainActivity.this);
//            imageView.setImageResource(arr[i]);
//            list.add(imageView);
//            // 设置小圆点
//            RadioButton button = new RadioButton(MainActivity.this);
//            // 设置选择器
////            button.setButtonDrawable(R.drawable.sel_r);
//            // 把RadioButton和间距添加到radioGroup中
//            mainRadioGroup.addView(button, params);
//        }
//// 选中第一个
//        mainRadioGroup.check(mainRadioGroup.getChildAt(0).getId());
//        mainPager.setAdapter(new PagerAdapter() {
//            @Override
//            public int getCount() {
//                return list.size();
//            }
//
//            @Override
//            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
//                return view == o;
//            }
//
//            @NonNull
//            @Override
//            public Object instantiateItem(@NonNull ViewGroup container, int position) {
//                ImageView imageView1 = list.get(position);
//
//                return imageView1;
//            }
//
//            @Override
//            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
////                super.destroyItem(container, position, object);
//
//                container.removeView((View) object);
//            }
//        });
//
//        //实现小圆点页面联动,
//        mainPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int i, float v, int i1) {
//
//            }
//
//            @Override
//            public void onPageSelected(int i) {
//                mainRadioGroup.check(mainRadioGroup.getChildAt(i).getId());
//                if (i == list.size()-1){
//                    mainBtn.setVisibility(View.VISIBLE);
//                }else {
//                    mainBtn.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int i) {
//
//            }
//        });
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        startActivity(new Intent(MainActivity.this,HomeActivity.class));
//    }


}
