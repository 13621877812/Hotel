package com.example.roombox.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.roombox.R;
import com.example.roombox.base.BaseActivity;
import com.example.roombox.bean.HotelBean;
import com.example.roombox.bean.ServiceBean;
import com.example.roombox.utils.ACache;
import com.example.roombox.utils.Contans;
import com.example.roombox.utils.HttpUtil;
import com.example.roombox.utils.SimpleAdapter;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
    @BindView(R.id.bedListView)
    RecyclerView bedListView;
    @BindView(R.id.collectionBtn)
    TextView collectionBtn;
    @BindView(R.id.orderBtn)
    TextView orderBtn;
    private HotelBean bean;


    RecyclerView recyclerView;
    private SimpleAdapter serviceAdapter;
    private ArrayList<ServiceBean> keyList = new ArrayList<>();
    private List bedsList = new ArrayList();
    private SimpleAdapter bedsAdapter;
    String collectionId = "";

    @Override
    public int intiLayout() {
        return R.layout.act_hd;
    }

    @Override
    public void initView() {
        recyclerView = findViewById(R.id.listView);

        ButterKnife.bind(this);
        iint();

    }

    private void iint() {

        bedListView.setLayoutManager(new LinearLayoutManager(HotelDetialAct.this));

        bedsAdapter = new SimpleAdapter(R.layout.couch_layout, bedsList, new SimpleAdapter.ConVert<LinkedTreeMap>() {
            @Override
            public void convert(BaseViewHolder helper, LinkedTreeMap bean) {
                int index = bedsList.indexOf(bean);
                helper.setText(R.id.room, "卧室" + (index + 1));
                StringBuffer bedBuffer = new StringBuffer();
                Integer bed1 = ((Double) bean.get("bed1num")).intValue();
                Integer bed2 = ((Double) bean.get("bed2num")).intValue();
                Integer bed3 = ((Double) bean.get("bed3num")).intValue();
                if (bed1 > 0) {
                    bedBuffer.append(bed1 + "张" + getResources().getString(R.string.bed1) + "\n");
                }
                if (bed2 > 0) {
                    bedBuffer.append(bed2 + "张" + getResources().getString(R.string.bed2) + "\n");
                }
                if (bed3 > 0) {
                    bedBuffer.append(bed3 + "张" + getResources().getString(R.string.bed3) + "\n");
                }
                helper.setText(R.id.couch, bedBuffer.toString());

            }
        });

        bedListView.setAdapter(bedsAdapter);


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
        boolean isOrder = getIntent().getExtras().getBoolean("isOrder");
        String userType = ACache.get(this).getAsString("type");
        if (isOrder == false || "3".equals(userType)) {
          orderBtn.setVisibility(View.GONE);
        }
        getCollection();
        info.setText(bean.getName());
        String typeString = "0".equals(bean.getType()) ? "整套房子" : "合租房間";
        type.setText(typeString);
        ownerText.setText("房東:" + bean.getUserId());
        Gson gson = new Gson();
        bedsList = gson.fromJson(bean.getBeds(), ArrayList.class);
        bedsAdapter.setNewData(bedsList);
        bedsAdapter.notifyDataSetChanged();
        Integer bedNum = 0;
        for (int i = 0; i < bedsList.size(); i++) {
            LinkedTreeMap hashMap = (LinkedTreeMap) bedsList.get(i);
            Integer bed1 = ((Double) hashMap.get("bed1num")).intValue();
            Integer bed2 = ((Double) hashMap.get("bed2num")).intValue();
            Integer bed3 = ((Double) hashMap.get("bed3num")).intValue();
            bedNum = bed1 + bed2 + bed3 + bedNum;
        }

        String info = bean.getNum() + "套房-" +
                bean.getRoommax() + "間臥室-" +
                bedNum + "張床-" +
                bean.getBathnum() + "間沐浴";
        hotelInfo.setText(info);

        Glide.with(this).load(Contans.HEADIMGURL + bean.getImages()).into(hotelImage);
        location.setText("位置：" + bean.getArea() + bean.getPlace());
        priceText.setText("$" + bean.getPrice() + "/晚");


        //服務數據處理
        String[] serviceNames = this.getResources().getStringArray(R.array.serviceType);
        String[] selectServices = bean.getServices().split(";");
        for (int i = 0; i < serviceNames.length; i++) {
            String name = serviceNames[i];
            int image = getImageID("logo");
            ServiceBean bean = new ServiceBean();
            bean.setName(name);
            bean.setImageName(image);
            if (Arrays.asList(selectServices).contains(i + "")) {
                bean.setSelect(true);
            }
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


    @OnClick({R.id.criticalBtn, R.id.commentBtn, R.id.orderBtn, R.id.collectionBtn, R.id.back})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("hotel", bean);
        switch (view.getId()) {
            case R.id.criticalBtn:
                Intent intent = new Intent(this, CriticalActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.commentBtn:
                Log.i("TAG", "getData: " + bean.getHotel_id());
                Intent intent1 = new Intent(this, CommentActivity.class);
                intent1.putExtras(bundle);
                startActivity(intent1);
                break;
            case R.id.orderBtn:
                Intent intent2 = new Intent(this, AddOrderActivity.class);
                intent2.putExtras(bundle);
                startActivity(intent2);
                break;
            case R.id.collectionBtn:
                collection();
                break;
            case R.id.back:
                finish();
                break;

            default:
                break;
        }
    }

    //收藏
    private void collection() {
        String url = "collection/add";
        HashMap<String, Object> params = new HashMap<>();
        String hotel_id = bean.getHotel_id();
        String account = ACache.get(HotelDetialAct.this).getAsString("account");
        Double hotel_id1 = Double.parseDouble(hotel_id);
        params.put("hotel_id", hotel_id1.intValue());
        params.put("account", account);
        params.put("isCollection", 1);
        params.put("collectionId", "0");
        if (!TextUtils.isEmpty(collectionId)) {
            params.put("isCollection", 0);
            params.put("collectionId", collectionId);
        }
        HttpUtil.httpPost(url, params, HotelDetialAct.this, new HttpUtil.HttpCallBack() {
            @Override
            public void success(String data) {
                Contans.makeToast(data, HotelDetialAct.this);
                getCollection();
            }
        });
    }

    private void getCollection() {
        String account = ACache.get(HotelDetialAct.this).getAsString("account");
        final Double hotel_id = Double.parseDouble(bean.getHotel_id());
        String url = "collection/list?account=" + account + "&hotel_id=" + hotel_id.intValue();
        HttpUtil.httpGet(url, HotelDetialAct.this, new HttpUtil.HttpCallBack() {
            @Override
            public void success(String data) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(data);
                    if (jsonArray.length() != 0) {
                        JSONObject object = (JSONObject) jsonArray.get(0);
                        Double Id = (Double) object.get("collectionId");
                        collectionId = Id.intValue() + "";
                        collectionBtn.setText("取消收藏");
                    } else {
                        collectionId = "";
                        collectionBtn.setText("收藏");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
