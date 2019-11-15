package com.bawei.cinemademo.frag.tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.cinemademo.R;
import com.bawei.cinemademo.adapter.cinemaAdapter.CinemaByAdapter;
import com.bawei.cinemademo.adapter.cinemaAdapter.RegionAdapter;
import com.bawei.cinemademo.base.BaseFragment;
import com.bawei.cinemademo.bean.Data;
import com.bawei.cinemademo.bean.cinemaBean.CinemaByBean;
import com.bawei.cinemademo.bean.cinemaBean.RegionBean;
import com.bawei.cinemademo.model.CallBackT;
import com.bawei.cinemademo.presenter.cinemaPresenter.CinemaByPresenter;
import com.bawei.cinemademo.presenter.cinemaPresenter.RegionPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Auther: 高晨凯
 * @Date: 2019/11/8 16:37:45
 * @Description:
 */
public class TabSelect extends BaseFragment {
    @BindView(R.id.tab_select_text)
    TextView tabText;
    @BindView(R.id.tab_select_recy)
    RecyclerView tabRecy;
    @BindView(R.id.tab_select_text2)
    TextView tabText2;
    @BindView(R.id.tab_select_recy2)
    RecyclerView tabRecy2;
    Unbinder unbinder;
    private RegionAdapter regionAdapter;
    private CinemaByAdapter cinemaByAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.tab_select;
    }

    @Override
    protected void initView() {


        //左侧列表
        regionAdapter = new RegionAdapter();
        RegionPresenter regionPresenter = new RegionPresenter(new quyuData());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        tabRecy.setLayoutManager(manager);
        tabRecy.setAdapter(regionAdapter);
        regionPresenter.getRequestData();





        //右侧
        cinemaByAdapter = new CinemaByAdapter();
        CinemaByPresenter cinemaByPresenter = new CinemaByPresenter(new cahxunData());
        LinearLayoutManager manager2 = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        tabRecy2.setLayoutManager(manager2);
        cinemaByPresenter.getRequestData(1);




    }

    //查询区域列表
    class quyuData implements CallBackT<List<RegionBean>>{

        private List<RegionBean> regionBeans;


        @Override
        public void onSuccess(List<RegionBean> regionBeans) {
            regionAdapter.addAll(regionBeans);
            regionAdapter.notifyDataSetChanged();

            int a = 0;
            for (int i = 0; i < regionBeans.size(); i++) {
                final int b = regionBeans.get(i).regionId;
                tabRecy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent = new Intent();
//                        intent.putExtra()
                    }
                });

            }
        }

        @Override
        public void onError(Data data) {

        }
    }


//    根据区域查询影院
    class cahxunData implements CallBackT<List<CinemaByBean>>{

    @Override
    public void onSuccess(List<CinemaByBean> cinemaByBeans) {
        cinemaByAdapter.addAll(cinemaByBeans);
        cinemaByAdapter.notifyDataSetChanged();

        Intent intent = new Intent();
        int aa = intent.getIntExtra("aa", 0);


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
