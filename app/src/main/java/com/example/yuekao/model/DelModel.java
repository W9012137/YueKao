package com.example.yuekao.model;

import com.example.yuekao.http.RetrofitUtils;
import com.example.yuekao.model.bean.MessageBean;
import com.example.yuekao.presenter.DelPresenter;

import io.reactivex.Flowable;

/**
 * Created by 王有道 on 2018/4/7.
 */

public class DelModel implements IModel {
    private DelPresenter presenter;

    public DelModel(DelPresenter presenter){
        this.presenter =  presenter;

    }
    @Override
    public void getData(String uid,String pid) {

        Flowable<MessageBean> delFlowable = RetrofitUtils.getInstance().getApiService().deleteData(uid,pid);
        presenter.delData(delFlowable);
    }
}