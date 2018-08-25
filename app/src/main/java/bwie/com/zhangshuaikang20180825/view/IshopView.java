package bwie.com.zhangshuaikang20180825.view;

import bwie.com.zhangshuaikang20180825.bean.CartBean;

public interface IshopView {
    void failure(String msg);
    void success(CartBean cartBean);
}
