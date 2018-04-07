package com.example.yuekao.presenter;

import com.example.yuekao.model.bean.GoodsBean;
import com.example.yuekao.model.bean.MessageBean;
import com.example.yuekao.model.GoodsModel;
import com.example.yuekao.view.Iview;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by 王有道 on 2018/4/7.
 */

public class GoodsPresenter implements IPresenter {
    private Iview iv;
    private DisposableSubscriber subscriber1;
//    private DisposableSubscriber subscriber2;

    public void attachView(Iview iv) {
        this.iv = iv;
    }

    public void detachView() {
        if (iv != null) {
            iv = null;
        }
        if (!subscriber1.isDisposed()){
            subscriber1.dispose();
        }
//        if (!subscriber2.isDisposed()){
//            subscriber2.dispose();
//        }
    }

    @Override
    public void getData(String uid,String pid) {
        GoodsModel model = new GoodsModel(this);
        model.getData(uid,pid);
    }

    public void getNews(Flowable<MessageBean<List<GoodsBean>>> flowable) {
        subscriber1 = flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<MessageBean<List<GoodsBean>>>() {
                    @Override
                    public void onNext(MessageBean<List<GoodsBean>> listMessageBean) {
                        if (listMessageBean != null) {
                            List<GoodsBean> list = listMessageBean.getData();
                            if (list != null) {
                                iv.onSuccess(list);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        iv.onFailed((Exception) t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
