package com.bawei.cinemademo.presenter;

import com.bawei.cinemademo.base.BasePresenter;
import com.bawei.cinemademo.model.CallBackT;
import com.bawei.cinemademo.model.IRequest;

import io.reactivex.Observable;

/**
 * @Auther: 高晨凯
 * @Date: 2019/11/7 19:43:02
 * @Description: 查询热门电影列表
 */
public class HotMovieListPresenter extends BasePresenter {
    public HotMovieListPresenter(CallBackT callBackT) {
        super(callBackT);
    }

    @Override
    protected Observable getModel(IRequest iRequest, Object... args) {
        return iRequest.findHotMovieList((int)args[0],(int)args[1]);
    }
}
