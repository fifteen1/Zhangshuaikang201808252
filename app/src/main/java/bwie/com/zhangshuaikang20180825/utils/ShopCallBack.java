package bwie.com.zhangshuaikang20180825.utils;



import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public interface ShopCallBack {
    void failure(Call call, IOException e);
    void Response(Call call, Response response);
}
