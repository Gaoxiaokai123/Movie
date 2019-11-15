package com.bawei.cinemademo.presenter.cinemaPresenter;

import com.bawei.cinemademo.base.BasePresenter;
import com.bawei.cinemademo.model.CallBackT;
import com.bawei.cinemademo.model.IRequest;

import io.reactivex.Observable;

/**
 * @Auther: 高晨凯
 * @Date: 2019/11/8 21:13:19
 * @Description:
 */
public class CinemaByPresenter extends BasePresenter {
    public CinemaByPresenter(CallBackT callBackT) {
        super(callBackT);
    }

    @Override
    protected Observable getModel(IRequest iRequest, Object... args) {
        return iRequest.findCinemaByRegion((int)args[0]);
    }
}
