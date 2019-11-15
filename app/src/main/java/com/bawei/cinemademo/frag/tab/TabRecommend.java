package com.bawei.cinemademo.frag.tab;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.cinemademo.R;
import com.bawei.cinemademo.adapter.cinemaAdapter.RecommendAdapter;
import com.bawei.cinemademo.base.BaseFragment;
import com.bawei.cinemademo.bean.Data;
import com.bawei.cinemademo.bean.cinemaBean.RecommendBean;
import com.bawei.cinemademo.model.CallBackT;
import com.bawei.cinemademo.presenter.cinemaPresenter.RecommendPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Auther: 高晨凯
 * @Date: 2019/11/8 16:34:49
 * @Description: 推荐影院
 */
public class TabRecommend extends BaseFragment {

    @BindView(R.id.tab_recommend_xrecy)
    XRecyclerView tabXrecy;
    Unbinder unbinder;
    private RecommendAdapter recommendAdapter;
    int page = 1;
    private RecommendPresenter recommendPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.tab_recommend;
    }

    @Override
    protected void initView() {

        recommendPresenter = new RecommendPresenter(new tuiJian());
        recommendAdapter = new RecommendAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        tabXrecy.setLayoutManager(manager);
        tabXrecy.setAdapter(recommendAdapter);
        recommendPresenter.getRequestData("0","0",page,10);
        shuaxin();
    }

    class tuiJian implements CallBackT<List<RecommendBean>>{

        @Override
        public void onSuccess(List<RecommendBean> recommendBeans) {
            recommendAdapter.addAll(recommendBeans);
            recommendAdapter.notifyDataSetChanged();
        }

        @Override
        public void onError(Data data) {

        }
    }

    private void shuaxin() {
        tabXrecy.setPullRefreshEnabled(true);
        tabXrecy.setLoadingMoreEnabled(true);
        tabXrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                recommendPresenter.getRequestData("0", "0", page, 10);
                tabXrecy.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                recommendPresenter.getRequestData("0", "0", page, 10);
                tabXrecy.loadMoreComplete();
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
