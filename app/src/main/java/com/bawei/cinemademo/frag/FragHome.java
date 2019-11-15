package com.bawei.cinemademo.frag;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.cinemademo.R;
import com.bawei.cinemademo.adapter.ComingSoonMovieListAdapter;
import com.bawei.cinemademo.adapter.HotMovieListAdapter;
import com.bawei.cinemademo.adapter.ReleaseMovieListAdapter;
import com.bawei.cinemademo.adapter.TopAdapter;
import com.bawei.cinemademo.app.App;
import com.bawei.cinemademo.base.BaseFragment;
import com.bawei.cinemademo.bean.Banner;
import com.bawei.cinemademo.bean.ComingSoonMovieListBean;
import com.bawei.cinemademo.bean.Data;
import com.bawei.cinemademo.bean.HotMovieListBean;
import com.bawei.cinemademo.bean.ReleaseMovieListBean;
import com.bawei.cinemademo.model.CallBackT;
import com.bawei.cinemademo.presenter.BannerPresenter;
import com.bawei.cinemademo.presenter.ComingSoonMovieListPresenter;
import com.bawei.cinemademo.presenter.HotMovieListPresenter;
import com.bawei.cinemademo.presenter.ReleaseMovieListPresenter;
import com.bawei.cinemademo.utils.FrescoUtils;
import com.bawei.cinemademo.view.activity.GengDuoActivity;
import com.bawei.cinemademo.view.activity.MoiveDetailActivity;
import com.bawei.cinemademo.view.activity.RegisterActivity;
import com.bawei.cinemademo.view.activity.SuosouActivity;
import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @Auther: 高晨凯
 * @Date: 2019/11/7 17:28:17
 * @Description:
 */
public class FragHome extends BaseFragment {


    @BindView(R.id.home_banner)
//    com.youth.banner.Banner homeBanner;
            XBanner homeBanner;
    @BindView(R.id.home_img2)
    ImageView homeImg2;
    @BindView(R.id.frag_home_recy1)
    RecyclerView fragRecy1;
    @BindView(R.id.home_img3)
    ImageView homeImg3;
    @BindView(R.id.frag_home_recy2)
    RecyclerView fragRecy2;
    @BindView(R.id.home_img)
    ImageView homeImg;
    @BindView(R.id.frag_home_recy3)
    RecyclerView fragRecy3;
    Unbinder unbinder;
    @BindView(R.id.frag_home_text)
    TextView fragHomeText;
    Unbinder unbinder1;

    Unbinder unbinder2;
    @BindView(R.id.home_hotimg)
    ImageView homeHotimg;
    @BindView(R.id.home_gegnduo1)
    TextView homeGegnduo1;
    @BindView(R.id.home_gegnduo2)
    TextView homeGegnduo2;
    @BindView(R.id.home_gegnduo3)
    TextView homeGegnduo3;
    @BindView(R.id.head_dingwei)
    ImageView headDingwei;
    @BindView(R.id.head_text)
    TextView headText;
    @BindView(R.id.head_suosou)
    ImageView headSuosou;
    @BindView(R.id.home_hotname)
    TextView homeHotname;
    @BindView(R.id.home_hotpingfen)
    TextView homeHotpingfen;
    @BindView(R.id.home_hotgoupiao)
    Button homeHotgoupiao;
    private List<String> list = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();

    private HotMovieListAdapter hotMovieListAdapter;
    private ReleaseMovieListAdapter releaseMovieListAdapter;
    private ComingSoonMovieListAdapter comingSoonMovieListAdapter;
    private ReleaseMovieListPresenter releaseMovieListPresenter;
    private ReleaseMovieListPresenter releaseMovieListPresenter1;
    private ComingSoonMovieListPresenter comingSoonMovieListPresenter;
    private HotMovieListPresenter hotMovieListPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_home;
    }

    @Override
    protected void initView() {

        //banner展示
        BannerPresenter bannerPresenter = new BannerPresenter(new bannerData());
        bannerPresenter.getRequestData();

        //recrview展示
        //查询正在上映电影列表

        releaseMovieListPresenter1 = new ReleaseMovieListPresenter(new releaseMovieList());
        releaseMovieListAdapter = new ReleaseMovieListAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        fragRecy1.setLayoutManager(layoutManager);
        fragRecy1.setAdapter(releaseMovieListAdapter);
        releaseMovieListPresenter1.getRequestData(1, 5);

        //即将上映
        comingSoonMovieListAdapter = new ComingSoonMovieListAdapter();
        comingSoonMovieListPresenter = new ComingSoonMovieListPresenter(new commingsonnMoviwList());
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        fragRecy2.setLayoutManager(layoutManager2);
        fragRecy2.setAdapter(comingSoonMovieListAdapter);
        comingSoonMovieListPresenter.getRequestData(1, 5);

//        查询热门电影列表
        hotMovieListPresenter = new HotMovieListPresenter(new hotMovieList());
        hotMovieListAdapter = new HotMovieListAdapter();
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        fragRecy3.setLayoutManager(layoutManager3);
        fragRecy3.setAdapter(hotMovieListAdapter);
        hotMovieListPresenter.getRequestData(1, 5);

        /*
        详情页跳转
         */
        hotMovieListAdapter.setOnClickTopItemListener(new HotMovieListAdapter.OnClickTopItemListener() {
            @Override
            public void onClick(int movieId) {
                Intent intent = new Intent(getContext(), MoiveDetailActivity.class);
                intent.putExtra("movieId",movieId);
                Log.e("aa", "接口传值: "+ movieId);
                startActivity(intent);
            }
        });

        releaseMovieListAdapter.setOnClickTopItemListener(new HotMovieListAdapter.OnClickTopItemListener() {
            @Override
            public void onClick(int movieId) {
                Intent intent = new Intent(getContext(), MoiveDetailActivity.class);
                intent.putExtra("movieId",movieId);
                Log.e("aa", "接口传值: "+ movieId);
                startActivity(intent);
            }
        });

        comingSoonMovieListAdapter.setOnClickTopItemListener(new HotMovieListAdapter.OnClickTopItemListener() {
            @Override
            public void onClick(int movieId) {
                Intent intent = new Intent(getContext(), MoiveDetailActivity.class);
                intent.putExtra("movieId",movieId);
                Log.e("aa", "接口传值: "+ movieId);
                startActivity(intent);
            }
        });

        //点击更多跳转更多电影列表页
        homeGegnduo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getContext(), GengDuoActivity.class));
//                startActivity(new Intent(getContext(), GengDuoActivity.class));
                Intent intent = new Intent(getActivity(), GengDuoActivity.class);
                intent.putExtra("id", 1);
                startActivity(intent);
                Log.e("fragmnt", "FragHome1跳转");
            }
        });
        homeGegnduo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getContext(), TabGengduoRelease.class));
                Intent intent = new Intent(getActivity(), GengDuoActivity.class);
                intent.putExtra("id", 2);
                startActivity(intent);
                Log.e("fragmnt", "FragHome2跳转");
            }
        });


        homeGegnduo3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getContext(), TabGengduoComingsoon.class));
                Intent intent = new Intent(getActivity(), GengDuoActivity.class);
                intent.putExtra("id", 3);
                startActivity(intent);
                Log.e("fragmnt", "FragHome3跳转");
            }

        });

    }


    //定位 跳转注册页面
    @OnClick(R.id.head_dingwei)
    public void register() {
        startActivity(new Intent(getContext(), RegisterActivity.class));
    }

    //搜索
    @OnClick(R.id.head_suosou)
    public void suosou(){
        startActivity(new Intent(getContext(), SuosouActivity.class));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder2 = ButterKnife.bind(this, rootView);
        return rootView;
    }


    //banner展示
    public class bannerData implements CallBackT<List<Banner>> {

        @Override
        public void onSuccess(final List<Banner> banners) {

            homeBanner.setData(banners, null);//第二个参数为提示文字资源集合
            // XBanner适配数据
//            pointsVisible
            homeBanner.setPointsIsVisible(false);
            homeBanner.setmAdapter(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner xBanner, Object o, View view, int i) {
                    Glide.with(App.context).load(banners.get(i).imageUrl).into((ImageView) view);
                    fragHomeText.setText(banners.get(i).rank + "/5");
                }
            });
            homeBanner.setPageTransformer(Transformer.Cube);    //立体旋转
            homeBanner.setPageTransformer(Transformer.Depth);  //本页左移，下页从后面出来
            homeBanner.setPageTransformer(Transformer.Zoom);  //本页刚左移，下页就在后面
            // 设置XBanner页面切换的时间，即动画时长
            homeBanner.setPageChangeDuration(1000);
        }

        @Override
        public void onError(Data data) {

        }
    }

    //查询正在上映电影列表
    public class releaseMovieList implements CallBackT<List<ReleaseMovieListBean>> {

        @Override
        public void onSuccess(List<ReleaseMovieListBean> releaseMovieListBeans) {
            releaseMovieListAdapter.addAll(releaseMovieListBeans);
            releaseMovieListAdapter.notifyDataSetChanged();
        }

        @Override
        public void onError(Data data) {

        }
    }

    //即将上映
    public class commingsonnMoviwList implements CallBackT<List<ComingSoonMovieListBean>> {

        @Override
        public void onSuccess(List<ComingSoonMovieListBean> comingSoonMovieListBeans) {
            comingSoonMovieListAdapter.addAll(comingSoonMovieListBeans);
            comingSoonMovieListAdapter.notifyDataSetChanged();
        }

        @Override
        public void onError(Data data) {

        }
    }

    //查询热门电影列表
    public class hotMovieList implements CallBackT<List<HotMovieListBean>> {

        @Override
        public void onSuccess(List<HotMovieListBean> hotMovieListBeans) {
            hotMovieListAdapter.addAll(hotMovieListBeans);
            hotMovieListAdapter.notifyDataSetChanged();
            Glide.with(App.context).load(hotMovieListBeans.get(0).horizontalImage)
                    .into(homeHotimg);

        }

        @Override
        public void onError(Data data) {

        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder != null){
            unbinder.unbind();
        }

        releaseMovieListPresenter1.deleteData();
        comingSoonMovieListPresenter.deleteData();
        hotMovieListPresenter.deleteData();

    }
}
