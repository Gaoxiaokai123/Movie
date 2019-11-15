package com.bawei.cinemademo.frag.tabgengduo;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.cinemademo.R;
import com.bawei.cinemademo.adapter.gengduoAdapter.GengduoAdapter;
import com.bawei.cinemademo.adapter.HotMovieListAdapter;
import com.bawei.cinemademo.base.BaseFragment;
import com.bawei.cinemademo.bean.Data;
import com.bawei.cinemademo.bean.HotMovieListBean;
import com.bawei.cinemademo.model.CallBackT;
import com.bawei.cinemademo.presenter.HotMovieListPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Auther: 高晨凯
 * @Date: 2019/11/9 19:06:29
 * @Description: <!--查询热门电影列表-->
 */
public class TabGengduoHot extends BaseFragment {

    @BindView(R.id.frag_gengduo_hot_xrecy)
    XRecyclerView hotXrecy;
    int page = 1;

    Unbinder unbinder;
    private HotMovieListAdapter hotMovieListAdapter;
    private HotMovieListPresenter hotMovieListPresenter;
    private GengduoAdapter gengduoAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_gengduo_hot;
    }

    @Override
    protected void initView() {
        gengduoAdapter = new GengduoAdapter();
//        hotMovieListAdapter = new HotMovieListAdapter();
        hotMovieListPresenter = new HotMovieListPresenter(new hotrecyData());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        hotXrecy.setLayoutManager(manager);
        hotXrecy.setAdapter(gengduoAdapter);

        hotMovieListPresenter.getRequestData( 1, 8);
        shuaxin();


    }

    class hotrecyData implements CallBackT<List<HotMovieListBean>>{

        @Override
        public void onSuccess(List<HotMovieListBean> hotMovieListBeans) {
            gengduoAdapter.addAll(hotMovieListBeans);
            gengduoAdapter.notifyDataSetChanged();
        }

        @Override
        public void onError(Data data) {

        }
    }

    private void shuaxin() {
        hotXrecy.setPullRefreshEnabled(true);
        hotXrecy.setLoadingMoreEnabled(true);
        hotXrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                hotMovieListPresenter.getRequestData( page, 8);
                hotXrecy.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                hotMovieListPresenter.getRequestData( page, 8);
                hotXrecy.loadMoreComplete();
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
