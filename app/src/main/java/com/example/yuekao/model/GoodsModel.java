package com.example.yuekao.model;

import com.example.yuekao.http.RetrofitUtils;
import com.example.yuekao.model.bean.GoodsBean;
import com.example.yuekao.model.bean.MessageBean;
import com.example.yuekao.presenter.GoodsPresenter;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by 王有道 on 2018/4/7.
 */

public class GoodsModel implements IModel {
    private GoodsPresenter presenter;

    public GoodsModel(GoodsPresenter presenter){
        this.presenter = (GoodsPresenter) presenter;

    }
    @Override
    public void getData(String uid,String pid) {
        Flowable<MessageBean<List<GoodsBean>>> flowable = RetrofitUtils.getInstance().getApiService().getDatas(uid);
        presenter.getNews(flowable);

    }
}