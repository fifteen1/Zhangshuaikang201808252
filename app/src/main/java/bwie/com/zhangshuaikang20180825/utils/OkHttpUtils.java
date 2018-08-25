package bwie.com.zhangshuaikang20180825.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtils {
    private static OkHttpUtils okHttpUtils;
    private final OkHttpClient okHttpClient;

    public OkHttpUtils(){
        okHttpClient = new OkHttpClient.Builder()
                .build();
    }
    public static OkHttpUtils getInstance(){
        if(okHttpUtils==null){
            synchronized (OkHttpUtils.class){
                if (okHttpUtils==null){
                    okHttpUtils = new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }

    public void getData(String url, HashMap<String,String> params,final ShopCallBack shopCallBack){
        StringBuilder stringBuilder = new StringBuilder();
        String allUrl = "";

        for (Map.Entry<String, String> stringStringEntry : params.entrySet()) {
            stringBuilder.append("?").append(stringStringEntry.getValue()).append("=").append(stringStringEntry.getKey()).append("&");
        }
        allUrl = url+stringBuilder.toString().substring(0,stringBuilder.length()-1);

        Request request = new Request.Builder()
                .url(allUrl)
                .get()
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(shopCallBack!=null){
                    shopCallBack.failure(call, e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(shopCallBack!=null){
                    shopCallBack.Response(call, response);
                }
            }
        });
    }

    public void postData(String url,HashMap<String,String> params,final ShopCallBack shopCallBack){
        FormBody.Builder formBodyBuilder = new FormBody.Builder();

        if(params!=null&&params.size()>0){
            for (Map.Entry<String, String> stringStringEntry : params.entrySet()) {
                formBodyBuilder.add(stringStringEntry.getKey(),stringStringEntry.getValue());
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .post(formBodyBuilder.build())
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(shopCallBack!=null){
                    shopCallBack.failure(call, e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(shopCallBack!=null){
                    shopCallBack.Response(call, response);
                }
            }
        });
    }
}
