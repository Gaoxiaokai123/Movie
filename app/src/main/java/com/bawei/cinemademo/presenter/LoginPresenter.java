package com.bawei.cinemademo.presenter;

import com.bawei.cinemademo.base.BasePresenter;
import com.bawei.cinemademo.model.CallBackT;
import com.bawei.cinemademo.model.IRequest;

import io.reactivex.Observable;

/**
 * @Auther: 高晨凯
 * @Date: 2019/11/11 15:33:44
 * @Description:  登录
 */
public class LoginPresenter extends BasePresenter {
    public LoginPresenter(CallBackT callBackT) {
        super(callBackT);
    }

    @Override
    protected Observable getModel(IRequest iRequest, Object... args) {
        return iRequest.login((String)args[0],(String)args[1]);
    }
}
