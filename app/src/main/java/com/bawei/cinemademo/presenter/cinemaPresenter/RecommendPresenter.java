package com.bawei.cinemademo.presenter.cinemaPresenter;

import com.bawei.cinemademo.base.BasePresenter;
import com.bawei.cinemademo.model.CallBackT;
import com.bawei.cinemademo.model.IRequest;

import io.reactivex.Observable;

/**
 * @Auther: 高晨凯
 * @Date: 2019/11/8 19:07:28
 * @Description:  查询推荐影院信息
 */
public class RecommendPresenter extends BasePresenter {
    public RecommendPresenter(CallBackT callBackT) {
        super(callBackT);
    }

    @Override
    protected Observable getModel(IRequest iRequest, Object... args) {
        return iRequest.findRecommendCinemas((String)args[0],(String)args[1],(int)args[2],(int)args[3]);
    }
}
