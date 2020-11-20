package com.fn.hotel.ui;

import android.os.Bundle;


import com.fn.hotel.R;
import com.fn.hotel.base.BaseActivity;


public class MapAct extends BaseActivity {
//    private MapView mapview;
//    private BaiduMap mBaiduMap;
//    private boolean isFirstLocation=true;
//    private LocationClient mLocationClient;
//
    @Override
    public int intiLayout() {
        return R.layout.act_map;
    }

    @Override
    public void initView() {
//        mapview = (MapView) findViewById(R.id.mapview);
//        mBaiduMap = mapview.getMap();


//        mBaiduMap.setMyLocationEnabled(true);

//        initLoaction();
    }

    @Override
    public void initData() {
//        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
//            //marker被点击时回调的方法
//            //若响应点击事件，返回true，否则返回false
//            //默认返回false
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//                Bundle extraInfo = marker.getExtraInfo();
//                if (extraInfo!=null){
//
//                    jumpAct(HotelDetialAct.class,null);
//                }
//                return true;
//            }
//        });

    }


//    private void initLoaction() {
//        mLocationClient = new LocationClient(this);
//
////通过LocationClientOption设置LocationClient相关参数
//        LocationClientOption option = new LocationClientOption();
//        option.setOpenGps(true); // 打开gps
//        option.setCoorType("bd09ll"); // 设置坐标类型
//        option.setScanSpan(1000);
//        option.setAddrType("all");
////设置locationClientOption
//        mLocationClient.setLocOption(option);
//
////注册LocationListener监听器
//        MyLocationListener myLocationListener = new MyLocationListener();
//        mLocationClient.registerLocationListener(myLocationListener);
////开启地图定位图层
//        mLocationClient.start();
//    }
//    public class MyLocationListener extends BDAbstractLocationListener {
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            //mapView 销毁后不在处理新接收的位置
//            if (location == null || mapview == null) {
//                return;
//            }
//
//            if (isFirstLocation) {
//                isFirstLocation = false;
//                //设置并显示中心点
//                setPosition2Center(mBaiduMap, location, true);
//            }
//
//        }
//    }
//
//    public void setPosition2Center(BaiduMap map, BDLocation bdLocation, Boolean isShowLoc) {
//        MyLocationData locData = new MyLocationData.Builder()
//                .accuracy(bdLocation.getRadius())
//                .direction(bdLocation.getRadius()).latitude(bdLocation.getLatitude())
//                .longitude(bdLocation.getLongitude()).build();
//        map.setMyLocationData(locData);
//
//        if (isShowLoc) {
//            LatLng ll = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
//            MapStatus.Builder builder = new MapStatus.Builder();
//            builder.target(ll).zoom(18.0f);
//            map.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
//        }
//    }
//
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
//        mapview.onResume();
//        getHourse();
//    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
//        mapview.onPause();
//    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
//        mapview.onDestroy();
//    }
//


    private void getHourse(){


    }
}
