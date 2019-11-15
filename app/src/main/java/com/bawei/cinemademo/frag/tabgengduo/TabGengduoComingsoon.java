package com.bawei.cinemademo.frag.tabgengduo;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.cinemademo.R;
import com.bawei.cinemademo.adapter.gengduoAdapter.GegnduoComingAdapter;
import com.bawei.cinemademo.base.BaseFragment;
import com.bawei.cinemademo.bean.ComingSoonMovieListBean;
import com.bawei.cinemademo.bean.Data;
import com.bawei.cinemademo.model.CallBackT;
import com.bawei.cinemademo.presenter.ComingSoonMovieListPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Auther: 高晨凯
 * @Date: 2019/11/11 16:27:10
 * @Description: <!--查询即将上映电影列表-->
 */
public class TabGengduoComingsoon extends BaseFragment {
    @BindView(R.id.frag_gengduo_coming_xrecy)
    XRecyclerView comingXrecy;
    Unbinder unbinder;
    private GegnduoComingAdapter gegnduoComingAdapter;
    private ComingSoonMovieListPresenter comingSoonMovieListPresenter;
    int page = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_gengduo_comingsoon;
    }

    @Override
    protected void initView() {
        gegnduoComingAdapter = new GegnduoComingAdapter();
        comingSoonMovieListPresenter = new ComingSoonMovieListPresenter(new comingData());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        comingXrecy.setLayoutManager(manager);
        comingXrecy.setAdapter(gegnduoComingAdapter);

        comingSoonMovieListPresenter.getRequestData( 1, 8);
        shuaxin();

    }

    class comingData implements CallBackT<List<ComingSoonMovieListBean>>{

        @Override
        public void onSuccess(List<ComingSoonMovieListBean> comingSoonMovieListBeans) {
            gegnduoComingAdapter.addAll(comingSoonMovieListBeans);
            gegnduoComingAdapter.notifyDataSetChanged();
        }

        @Override
        public void onError(Data data) {

        }
    }

    private void shuaxin() {
        comingXrecy.setPullRefreshEnabled(true);
        comingXrecy.setLoadingMoreEnabled(true);
        comingXrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                comingSoonMovieListPresenter.getRequestData( page, 8);
                comingXrecy.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                comingSoonMovieListPresenter.getRequestData( page, 8);
                comingXrecy.loadMoreComplete();
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
