package com.bawei.cinemademo.frag.tab;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.cinemademo.R;
import com.bawei.cinemademo.adapter.cinemaAdapter.NearbyCinemasAdapter;
import com.bawei.cinemademo.base.BaseFragment;
import com.bawei.cinemademo.bean.Data;
import com.bawei.cinemademo.bean.cinemaBean.NearbyCinemasBean;
import com.bawei.cinemademo.bean.cinemaBean.RecommendBean;
import com.bawei.cinemademo.model.CallBackT;
import com.bawei.cinemademo.presenter.cinemaPresenter.NearbyCinemasPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Auther: 高晨凯
 * @Date: 2019/11/8 16:37:12
 * @Description: 查询附近影院
 */
public class TabNearby extends BaseFragment {
    @BindView(R.id.tab_nearby_xrecy)
    XRecyclerView tabXrecy;
    Unbinder unbinder;
    private NearbyCinemasAdapter nearbyCinemasAdapter;
    int page = 1;
    private NearbyCinemasPresenter nearbyCinemasPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.tab_nearby;
    }

    @Override
    protected void initView() {
        nearbyCinemasAdapter = new NearbyCinemasAdapter();
        nearbyCinemasPresenter = new NearbyCinemasPresenter(new fujin());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        tabXrecy.setLayoutManager(manager);
        tabXrecy.setAdapter(nearbyCinemasAdapter);
        nearbyCinemasPresenter.getRequestData("0","0","116.30551391385724","40.04571807462411",page,10);

        shuaxin();
    }

    private void shuaxin() {
        tabXrecy.setPullRefreshEnabled(true);
        tabXrecy.setLoadingMoreEnabled(true);
        tabXrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                nearbyCinemasPresenter.getRequestData("0","0","116.30551391385724","40.04571807462411",page,10);
                tabXrecy.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page ++;
                nearbyCinemasPresenter.getRequestData("0","0","116.30551391385724","40.04571807462411",page,10);
                tabXrecy.loadMoreComplete();
            }
        });



    }

    class fujin implements CallBackT<List<NearbyCinemasBean>> {

        @Override
        public void onSuccess(List<NearbyCinemasBean> nearbyCinemasBeans) {
            nearbyCinemasAdapter.addAll(nearbyCinemasBeans);
            nearbyCinemasAdapter.notifyDataSetChanged();
        }

        @Override
        public void onError(Data data) {

        }
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
