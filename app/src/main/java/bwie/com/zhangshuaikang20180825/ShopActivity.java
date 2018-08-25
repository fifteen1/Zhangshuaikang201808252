package bwie.com.zhangshuaikang20180825;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bwie.com.zhangshuaikang20180825.adapter.AllCheckListener;
import bwie.com.zhangshuaikang20180825.adapter.ShopAdapter;
import bwie.com.zhangshuaikang20180825.bean.CartBean;
import bwie.com.zhangshuaikang20180825.common.Api;
import bwie.com.zhangshuaikang20180825.presenter.ShopPresneter;
import bwie.com.zhangshuaikang20180825.view.IshopView;

public class ShopActivity extends AppCompatActivity implements IshopView,AllCheckListener{

    private XRecyclerView xRecyclerView;
    private List<CartBean.DataBean> list;
    private ShopPresneter shopPresneter;
    private CheckBox allChe;
    private ShopAdapter shopAdapter;
    private TextView numprice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        initView();
        initData();
    }
    private void initView() {
        list = new ArrayList<>();
        xRecyclerView = findViewById(R.id.shop_XCV);
        numprice = findViewById(R.id.numprice);
        allChe = findViewById(R.id.all_check);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        allChe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(allChe.isChecked()){
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setSelectd(true);
                        for (int i1 = 0; i1 < list.get(i).getList().size(); i1++) {
                            list.get(i).getList().get(i1).setSelectd(true);
                        }
                    }
                }else{
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setSelectd(false);
                        for (int i1 = 0; i1 < list.get(i).getList().size(); i1++) {
                            list.get(i).getList().get(i1).setSelectd(false);
                        }
                    }
                }
                shopAdapter.notifyDataSetChanged();
                totalPrice();

            }
        });

    }

    private void initData() {
        loadData();
    }

    private void loadData() {
        HashMap<String,String> params = new HashMap<>();
        params.put("uid","71");
        shopPresneter = new ShopPresneter(this);
        shopPresneter.getShop(params, Api.CARTS);
    }


    @Override
    public void failure(String msg) {
        Toast.makeText(this,msg , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(CartBean cartBean) {
        if(cartBean!=null&&cartBean.getData()!=null){
            list = cartBean.getData();
            shopAdapter = new ShopAdapter(this, list);
            xRecyclerView.setAdapter(shopAdapter);
        }
        shopAdapter.setAllCheckListener(this);
    }

    @Override
    public void notifyAllCheckListener() {
        StringBuilder stringBuilder = new StringBuilder();
        if(shopAdapter!=null){
            for (int i = 0; i < shopAdapter.getCartList().size(); i++) {

                stringBuilder.append(shopAdapter.getCartList().get(i).isSelectd());
                for (int i1 = 0; i1 < shopAdapter.getCartList().get(i).getList().size(); i1++) {
                    stringBuilder.append(shopAdapter.getCartList().get(i).getList().get(i1).isSelectd());
                }
            }
        }
        if((stringBuilder).toString().contains("false")){
            allChe.setChecked(false);
        }else{
            allChe.setChecked(true);
        }
        totalPrice();
    }
    private void totalPrice(){
        double totalprice = 0;
        for (int i = 0; i < shopAdapter.getCartList().size(); i++) {
            for (int i1 = 0; i1 < shopAdapter.getCartList().get(i).getList().size(); i1++) {
                if (shopAdapter.getCartList().get(i).getList().get(i1).isSelectd()){
                    CartBean.DataBean.ListBean listBean = shopAdapter.getCartList().get(i).getList().get(i1);
                    totalprice = listBean.getBargainPrice()*listBean.getTotalNum();
                }
            }
        }
        numprice.setText("总价"+totalprice);
    }
}
