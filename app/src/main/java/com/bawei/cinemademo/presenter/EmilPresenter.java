package com.bawei.cinemademo.presenter;

import com.bawei.cinemademo.base.BasePresenter;
import com.bawei.cinemademo.model.CallBackT;
import com.bawei.cinemademo.model.IRequest;

import io.reactivex.Observable;

/**
 * @Auther: 高晨凯
 * @Date: 2019/11/6 21:42:23
 * @Description:
 */
public class EmilPresenter extends BasePresenter {
    public EmilPresenter(CallBackT callBackT) {
        super(callBackT);
    }

    @Override
    protected Observable getModel(IRequest iRequest, Object... args) {
        return iRequest.sendOutEmailCode((String)args[0]);
    }
}
