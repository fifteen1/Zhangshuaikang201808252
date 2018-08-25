package bwie.com.zhangshuaikang20180825.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import bwie.com.zhangshuaikang20180825.R;
import bwie.com.zhangshuaikang20180825.bean.CartBean;
import bwie.com.zhangshuaikang20180825.myView.My_jiajian;

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ShopViewHolder>{
    private Context ncontext;
    private List<CartBean.DataBean.ListBean> goodsList;
    private ShopCheckListener shopCheckListener;
    private AllCheckListener allCheckListener;

    public void setShopCheckListener(ShopCheckListener shopCheckListener) {
        this.shopCheckListener = shopCheckListener;
    }

    public void setAllCheckListener(AllCheckListener allCheckListener) {
        this.allCheckListener = allCheckListener;
    }

    public GoodsAdapter(Context context, List<CartBean.DataBean.ListBean> list) {
        ncontext = context;
        goodsList = list;
    }

    @NonNull
    @Override
    public GoodsAdapter.ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(ncontext).inflate(R.layout.goods_item_layout, parent, false);
        ShopViewHolder shopViewHolder = new ShopViewHolder(itemView);

        return shopViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final GoodsAdapter.ShopViewHolder holder, int position) {
        final CartBean.DataBean.ListBean listBean = goodsList.get(position);
        holder.nameTv.setText(listBean.getTitle());
        holder.priceTv.setText("优惠价"+listBean.getBargainPrice());
        String[] imgs = listBean.getImages().split("\\|");
        if(imgs!=null&&imgs.length>0){
            Glide.with(ncontext).load(imgs[0]).into(holder.img);
        }
        holder.goodsCk.setChecked(listBean.isSelectd());
        holder.goodsCk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.goodsCk.isChecked()){
                    listBean.setSelectd(true);
                }else{
                    listBean.setSelectd(false);
                }
                if(shopCheckListener!=null){
                    shopCheckListener.notifyParpen();
                }
            }
        });
        holder.my_jiajian.setNumEt(listBean.getTotalNum());

        holder.my_jiajian.setJiajianLintener(new My_jiajian.JiajianLintener() {
            @Override
            public void getNum(int num) {
                listBean.setTotalNum(num);
                if(shopCheckListener!=null){
                    shopCheckListener.notifyParpen();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return goodsList.size()==0?0:goodsList.size();
    }

    public class ShopViewHolder extends RecyclerView.ViewHolder {
        private CheckBox goodsCk;
        private TextView nameTv,priceTv;
        private ImageView img;
        private My_jiajian my_jiajian;
        public ShopViewHolder(View itemView) {
            super(itemView);
            goodsCk = itemView.findViewById(R.id.good_CK);
            nameTv = itemView.findViewById(R.id.gname_Tv);
            priceTv = itemView.findViewById(R.id.price_tv);
            img = itemView.findViewById(R.id.img);
            my_jiajian = itemView.findViewById(R.id.jiajianqi);

        }

    }
}
