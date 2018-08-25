package bwie.com.zhangshuaikang20180825.model;

import android.os.Handler;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import bwie.com.zhangshuaikang20180825.bean.CartBean;
import bwie.com.zhangshuaikang20180825.utils.OkHttpUtils;
import bwie.com.zhangshuaikang20180825.utils.ShopCallBack;
import okhttp3.Call;
import okhttp3.Response;

public class Shopmodel {
    private Handler handler = new Handler();

    public void getShop(HashMap<String,String> params, String url, final CCallBack cCallBack){
        OkHttpUtils.getInstance().postData(url, params, new ShopCallBack() {
            @Override
            public void failure(Call call, IOException e) {
                if(cCallBack!=null){
                    cCallBack.failure("网络异常，请稍后重试");
                }
            }

            @Override
            public void Response(Call call, Response response) {
                try {
                    String jsonResult = response.body().string();
                    if (!TextUtils.isEmpty(jsonResult)){
                        Parsejson(jsonResult,cCallBack);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void Parsejson(String jsonResult, final CCallBack cCallBack) {
        final CartBean cartBean = new Gson().fromJson(jsonResult, CartBean.class);
        if(jsonResult!=null&&cartBean!=null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    cCallBack.success(cartBean);
                }
            });
        }
    }

    public interface CCallBack{
        void failure(String msg);
        void success(CartBean cartBean);
    }
}
