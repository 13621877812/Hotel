package com.example.roombox.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.roombox.R;
import com.example.roombox.base.BaseActivity;
import com.example.roombox.bean.HotelBean;
import com.example.roombox.bean.ServiceBean;
import com.example.roombox.utils.Contans;
import com.example.roombox.utils.HttpUtil;
import com.example.roombox.utils.SimpleAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HotelDetialAct extends BaseActivity {

  @BindView(R.id.info)
  TextView info;
  @BindView(R.id.type)
  TextView type;
  @BindView(R.id.ownerText)
  TextView ownerText;
  @BindView(R.id.hotelInfo)
  TextView hotelInfo;
  @BindView(R.id.hotelImage)
  ImageView hotelImage;
  @BindView(R.id.location)
  TextView location;
  @BindView(R.id.priceText)
  TextView priceText;
  private HotelBean bean;



  RecyclerView recyclerView;
  private SimpleAdapter serviceAdapter;
  private ArrayList<ServiceBean> keyList = new ArrayList<>();

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

    serviceAdapter = new SimpleAdapter(R.layout.bed_item, keyList, new SimpleAdapter.ConVert<ServiceBean>() {
      @Override
      public void convert(BaseViewHolder helper, ServiceBean bean) {

        helper.setText(R.id.serviceName, bean.getName());
        helper.setImageResource(R.id.serviceImage, bean.getImageName());
        if (bean.isSelect()) {
          helper.setTextColor(R.id.serviceName, Color.BLACK);
          helper.setImageResource(R.id.serviceImage, bean.getImageName());
        } else {
          helper.setTextColor(R.id.serviceName, Color.GRAY);
          helper.setImageResource(R.id.serviceImage, bean.getImageName());
        }
      }
    });

    recyclerView.setAdapter(serviceAdapter);


  }

  @Override
  public void initData() {
    bean = (HotelBean) getIntent().getExtras().getSerializable("hotel");
    info.setText(bean.getName());
    String typeString = "0".equals(bean.getType()) ? "整套房子" : "合租房間";
    type.setText(typeString);
    ownerText.setText("房東:" + bean.getUserId());
    String info = bean.getNum() + "套房-" +
            bean.getRoommax() + "間臥室-" +
            bean.getBeds() + "張床-" +
            bean.getBathnum() + "間沐浴";
    hotelInfo.setText(info);

    Glide.with(this).load(Contans.HEADIMGURL + bean.getImages()).into(hotelImage);
    location.setText("位置：" + bean.getArea() + bean.getPlace());
    priceText.setText("$" + bean.getPrice() + "/晚");


    //服務數據處理
    String[] serviceNames = this.getResources().getStringArray(R.array.serviceType);
    for (int i = 0; i < serviceNames.length; i++) {
      String name = serviceNames[i];
      int image = getImageID("logo");
      ServiceBean bean = new ServiceBean();
      bean.setName(name);
      bean.setImageName(image);
      keyList.add(bean);
    }
    serviceAdapter.notifyDataSetChanged();


  }

  public int getImageID(String name) {

    int id = -1;
    try {
      Field field = R.mipmap.class.getDeclaredField(name);
      String str = field.get(null).toString();
      id = Integer.parseInt(str);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  @Override
  protected void onResume() {
    super.onResume();

//        refreshData();


  }

  private void setViewData() {

  }


  @OnClick({R.id.criticalBtn,R.id.commentBtn,R.id.orderBtn,R.id.collectionBtn})
  public void onViewClicked(View view) {
    switch (view.getId()){
      case R.id.criticalBtn:
        startActivity(new Intent(this,CommentActivity.class));
        break;
      case R.id.commentBtn:
        startActivity(new Intent(this,CriticalActivity.class));
        break;
      case R.id.orderBtn:
        orderHotel();
        break;
      case R.id.collectionBtn:
        collection();
        break;

      default:
        break;
    }
  }
  //收藏
  private void collection(){

  }
  //預定
  private void orderHotel(){

  }
}
