package com.bawei.cinemademo.presenter.cinemaPresenter;

import com.bawei.cinemademo.base.BasePresenter;
import com.bawei.cinemademo.model.CallBackT;
import com.bawei.cinemademo.model.IRequest;

import io.reactivex.Observable;

/**
 * @Auther: 高晨凯
 * @Date: 2019/11/8 20:04:44
 * @Description: 查询附近影院
 */
public class NearbyCinemasPresenter extends BasePresenter {
    public NearbyCinemasPresenter(CallBackT callBackT) {
        super(callBackT);
    }

    @Override
    protected Observable getModel(IRequest iRequest, Object... args) {
        return iRequest.findNearbyCinemas((String)args[0],(String)args[1],(String)args[2],(String)args[3],(int)args[4],(int)args[5]);
    }
}
