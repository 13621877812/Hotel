package com.example.roombox.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.roombox.R;
import com.example.roombox.adapters.CollectionAdapter;
import com.example.roombox.bean.HotelBean;
import com.example.roombox.utils.ACache;
import com.example.roombox.utils.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends AppCompatActivity {

    @BindView(R.id.listView)
    RecyclerView listView;
    private CollectionAdapter orderAdapter;
    ArrayList<HotelBean> datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        initView();
        initData();

    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        listView.setLayoutManager(manager);
        orderAdapter = new CollectionAdapter(this);
        listView.setAdapter(orderAdapter);
        orderAdapter.setListener(new CollectionAdapter.ItemClickListener() {
            @Override
            public void itemClick(HotelBean bean) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("hotel",bean);
                bundle.putBoolean("isOrder",false);
                Intent intent = new Intent(OrderActivity.this, HotelDetialAct.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
    //獲取房源數據
    private void initData(){
        String account = ACache.get(this).getAsString("account");
        String url = "order/list?account="+account;
        HttpUtil.httpGet(url, this, new HttpUtil.HttpCallBack() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<HotelBean>>() {
                }.getType();
                datas = gson.fromJson(data, type);
                orderAdapter.setDatas(datas);
                orderAdapter.notifyDataSetChanged();
            }
        });
    }


}
