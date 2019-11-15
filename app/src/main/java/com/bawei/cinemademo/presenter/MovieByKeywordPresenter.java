package com.bawei.cinemademo.presenter;

import com.bawei.cinemademo.base.BasePresenter;
import com.bawei.cinemademo.model.CallBackT;
import com.bawei.cinemademo.model.IRequest;

import io.reactivex.Observable;

/**
 * @Auther: 高晨凯
 * @Date: 2019/11/12 09:59:02
 * @Description:  根据关键字查询电影信息
 */
public class MovieByKeywordPresenter extends BasePresenter {
    public MovieByKeywordPresenter(CallBackT callBackT) {
        super(callBackT);
    }

    @Override
    protected Observable getModel(IRequest iRequest, Object... args) {
        return iRequest.findMovieByKeyword((String)args[0],(int)args[1],(int)args[2]);
    }
}
