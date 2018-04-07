package com.example.yuekao.view;

import com.example.yuekao.model.bean.MessageBean;

/**
 * Created by 王有道 on 2018/4/7.
 */

public interface Iview {
    void onSuccess(Object o);
    void onFailed(Exception e);

    void delSuccess(MessageBean listMessageBean);


}
