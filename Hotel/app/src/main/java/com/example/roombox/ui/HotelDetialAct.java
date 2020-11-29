package com.example.roombox.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.roombox.R;
import com.example.roombox.base.BaseActivity;
import com.example.roombox.bean.CommentBean;
import com.example.roombox.bean.HotelBean;
import com.example.roombox.utils.Contans;
import com.example.roombox.utils.SimpleAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HotelDetialAct extends BaseActivity {

    @BindView(R.id.info)
    TextView info;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.ownerText)
    TextView ownerText;
    @BindView(R.id.hotelImage)
    ImageView hotelImage;
    private HotelBean bean;

    private ImageView iv;
    private TextView nameText;
    private TextView priceText;
    private TextView typeText;
    private TextView placeText;
    private TextView areaText;
    private TextView telText;
    private TextView gradeText;

    ImageView banner;


    RecyclerView recyclerView;
    private SimpleAdapter adapter;
    private ArrayList<CommentBean> keyList = new ArrayList<>();

    @Override
    public int intiLayout() {
        return R.layout.act_hd;
    }

    @Override
    public void initView() {
        recyclerView = findViewById(R.id.listView);
        iint();
        ButterKnife.bind(this);
    }

    private void iint() {

        recyclerView.setLayoutManager(new LinearLayoutManager(HotelDetialAct.this));

        adapter = new SimpleAdapter(R.layout.common_item, keyList, new SimpleAdapter.ConVert<CommentBean>() {
            @Override
            public void convert(BaseViewHolder helper, CommentBean o) {


            }
        });

        recyclerView.setAdapter(adapter);


    }

    @Override
    public void initData() {
        bean = (HotelBean) getIntent().getExtras().getSerializable("hotel");
        info.setText(bean.getIntro());
        String typeString = "0".equals(bean.getType()) ? "整套房子" : "合租房间";
        type.setText(typeString);
        ownerText.setText("房东:" + bean.getUserId());


        Glide.with(this).load(Contans.HEADIMGURL+bean.getImages()).into(hotelImage);


    }

    @Override
    protected void onResume() {
        super.onResume();

//        refreshData();


    }

    private void setViewData() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
