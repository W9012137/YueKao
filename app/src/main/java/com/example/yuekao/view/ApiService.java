package com.example.yuekao.view;

import com.example.yuekao.model.bean.GoodsBean;
import com.example.yuekao.model.bean.MessageBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 王有道 on 2018/4/7.
 */

public interface ApiService {
    @GET("product/getCarts")
    Flowable<MessageBean<List<GoodsBean>>> getDatas(@Query("uid") String uid);
    @GET("product/deleteCart")
    Flowable<MessageBean> deleteData(@Query("uid") String uid, @Query("pid") String pid);

}
