package com.bawei.cinemademo.frag.tabgengduo;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.cinemademo.R;
import com.bawei.cinemademo.adapter.gengduoAdapter.GengduoReleaseAdapter;
import com.bawei.cinemademo.base.BaseFragment;
import com.bawei.cinemademo.bean.Data;
import com.bawei.cinemademo.bean.ReleaseMovieListBean;
import com.bawei.cinemademo.model.CallBackT;
import com.bawei.cinemademo.presenter.ReleaseMovieListPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Auther: 高晨凯
 * @Date: 2019/11/11 16:28:18
 * @Description:
 */
public class TabGengduoRelease extends BaseFragment {
    @BindView(R.id.frag_gengduo_release_xrecy)
    XRecyclerView releaseXrecy;
    Unbinder unbinder;
    private GengduoReleaseAdapter gengduoReleaseAdapter;
    private ReleaseMovieListPresenter releaseMovieListPresenter;
    int page = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_gengduo_release;
    }

    @Override
    protected void initView() {
        gengduoReleaseAdapter = new GengduoReleaseAdapter();
        releaseMovieListPresenter = new ReleaseMovieListPresenter(new releaseData());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        releaseXrecy.setLayoutManager(manager);
        releaseXrecy.setAdapter(gengduoReleaseAdapter);

        releaseMovieListPresenter.getRequestData( 1, 6);
        shuaxin();

    }

    class releaseData implements CallBackT<List<ReleaseMovieListBean>>{

        @Override
        public void onSuccess(List<ReleaseMovieListBean> releaseMovieListBeans) {
            gengduoReleaseAdapter.addAll(releaseMovieListBeans);
            gengduoReleaseAdapter.notifyDataSetChanged();
        }

        @Override
        public void onError(Data data) {

        }
    }

    private void shuaxin() {
        releaseXrecy.setPullRefreshEnabled(true);
        releaseXrecy.setLoadingMoreEnabled(true);
        releaseXrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                releaseMovieListPresenter.getRequestData( page, 6);
                releaseXrecy.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                releaseMovieListPresenter.getRequestData( page, 6);
                releaseXrecy.loadMoreComplete();
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
