package bwie.com.zhangshuaikang20180825.presenter;

import java.util.HashMap;

import bwie.com.zhangshuaikang20180825.bean.CartBean;
import bwie.com.zhangshuaikang20180825.model.Shopmodel;
import bwie.com.zhangshuaikang20180825.view.IshopView;

public class ShopPresneter {
    private Shopmodel shopmodel;
    private IshopView ishopView;

    public ShopPresneter(IshopView ishopView) {
        shopmodel = new Shopmodel();
        attachView(ishopView);
    }
    public void attachView(IshopView ishopView){
        this.ishopView = ishopView;
    }
    public void getShop(HashMap<String,String> params, String url){
        shopmodel.getShop(params, url, new Shopmodel.CCallBack() {
            @Override
            public void failure(String msg) {
                if(ishopView!=null){
                    ishopView.failure(msg);
                }
            }

            @Override
            public void success(CartBean cartBean) {
                if (ishopView!=null){
                    ishopView.success(cartBean);
                }
            }
        });

    }

    public void datachView(){
        this.ishopView = null;
    }
}
