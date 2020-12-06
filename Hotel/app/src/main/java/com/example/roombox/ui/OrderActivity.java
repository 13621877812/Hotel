package com.example.roombox.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.roombox.R;
import com.example.roombox.adapters.CollectionAdapter;
import com.example.roombox.bean.HotelBean;
import com.example.roombox.utils.ACache;
import com.example.roombox.utils.Contans;
import com.example.roombox.utils.HttpUtil;
import com.example.roombox.utils.SimpleAdapter;
import com.example.roombox.utils.TimeUtil;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends AppCompatActivity {

    @BindView(R.id.listView)
    RecyclerView listView;
    private SimpleAdapter orderAdapter;
    ArrayList<HashMap> datas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        initView();
        initData();

    }

    private void initView() {
        listView.setLayoutManager(new LinearLayoutManager(this));

        orderAdapter = new SimpleAdapter(R.layout.order_item, datas, new SimpleAdapter.ConVert<HashMap>() {
            @Override
            public void convert(BaseViewHolder helper, HashMap bean) {
                String desInfo = bean.get("area") + "/" + bean.get("max") + "人大套房/" + bean.get("price");
                helper.setText(R.id.name, (String)bean.get("name"));
                helper.setText(R.id.desc, desInfo);
                helper.setText(R.id.price, "$"+bean.get("price")+"/每晚");
                Glide.with(OrderActivity.this).load(Contans.HEADIMGURL+bean.get("images")).into((ImageView) helper.getView(R.id.topImage));
                helper.setText(R.id.ownerText,"房東：" + bean.get("userId"));
                helper.setText(R.id.orderText,"預定者：" + bean.get("account"));
                helper.setText(R.id.startTimeText,"始時：" + TimeUtil.date2TimeStamp(bean.get("startTime")+"","yyyy/MM/dd HH:mm"));
                helper.setText(R.id.endTimeText,"結束:" + TimeUtil.date2TimeStamp(bean.get("endTime")+"","yyyy/MM/dd HH:mm"));
                helper.setText(R.id.priceText,"总金额：$" + bean.get("orderPrice"));
            }
        });

        listView.setAdapter(orderAdapter);





//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        listView.setLayoutManager(manager);
//        orderAdapter = new CollectionAdapter(this);
//        listView.setAdapter(orderAdapter);
//        orderAdapter.setListener(new CollectionAdapter.ItemClickListener() {
//            @Override
//            public void itemClick(HotelBean bean) {
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("hotel",bean);
//                bundle.putBoolean("isOrder",false);
//                Intent intent = new Intent(OrderActivity.this, HotelDetialAct.class);
//                intent.putExtras(bundle);
//                startActivity(intent);
//            }
//        });

    }
    //獲取房源數據
    private void initData(){
        String account = ACache.get(this).getAsString("account");
        String url = "order/list?account="+account;
        HttpUtil.httpGet(url, this, new HttpUtil.HttpCallBack() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<HashMap>>() {
                }.getType();
                datas = gson.fromJson(data, type);
                orderAdapter.setNewData(datas);
                orderAdapter.notifyDataSetChanged();
            }
        });
    }


}
