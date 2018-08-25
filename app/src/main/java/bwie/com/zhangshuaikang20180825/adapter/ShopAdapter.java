package bwie.com.zhangshuaikang20180825.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import bwie.com.zhangshuaikang20180825.R;
import bwie.com.zhangshuaikang20180825.bean.CartBean;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> implements ShopCheckListener{
    private Context mcontext;
    private List<CartBean.DataBean> cartList;
    private AllCheckListener allCheckListener;

    public void setAllCheckListener(AllCheckListener allCheckListener) {
        this.allCheckListener = allCheckListener;
    }

    public ShopAdapter(Context context, List<CartBean.DataBean> list) {
        mcontext = context;
        cartList = list;
    }

    @NonNull
    @Override
    public ShopAdapter.ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mcontext).inflate(R.layout.shop_itemlayout, parent, false);
        ShopViewHolder shopViewHolder = new ShopViewHolder(itemView);

        return shopViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ShopAdapter.ShopViewHolder holder, int position) {
        final CartBean.DataBean dataBean = cartList.get(position);
        holder.shaoptitle.setText(dataBean.getSellerName());
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mcontext));
        GoodsAdapter goodsAdapter = new GoodsAdapter(mcontext, dataBean.getList());
        holder.recyclerView.setAdapter(goodsAdapter);
        goodsAdapter.setShopCheckListener(this);
        for (int i = 0; i < dataBean.getList().size(); i++) {

            if(dataBean.getList().get(i).isSelectd()){
                holder.shopChe.setChecked(true);
            }else{
                holder.shopChe.setChecked(false);
            }
        }
        holder.shopChe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.shopChe.isChecked()){
                    dataBean.setSelectd(true);
                    for (int i = 0; i < dataBean.getList().size(); i++) {
                        dataBean.getList().get(i).setSelectd(true);
                    }
                }else{
                    dataBean.setSelectd(false);
                    for (int i = 0; i < dataBean.getList().size(); i++) {
                        dataBean.getList().get(i).setSelectd(false);
                    }
                }
                notifyDataSetChanged();
                if(allCheckListener!=null){
                    allCheckListener.notifyAllCheckListener();
                }
            }
        });

    }

    public List<CartBean.DataBean> getCartList(){

        return cartList;
    }


    @Override
    public int getItemCount() {
        return cartList.size()==0?0:cartList.size();
    }

    @Override
    public void notifyParpen() {
        notifyDataSetChanged();
        if(allCheckListener!=null){
            allCheckListener.notifyAllCheckListener();
        }
    }

    public class ShopViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerView;
        private CheckBox shopChe;
        private TextView shaoptitle;
        public ShopViewHolder(View itemView) {
            super(itemView);
            shaoptitle = itemView.findViewById(R.id.title_Tv);
            recyclerView = itemView.findViewById(R.id.recyclerView);
            shopChe = itemView.findViewById(R.id.shop_check);
        }

    }
}
