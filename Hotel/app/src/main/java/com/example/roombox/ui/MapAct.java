package com.example.roombox.ui;

import com.example.roombox.R;
import com.example.roombox.base.BaseActivity;


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
//            //marker被點擊時回調的方法
//            //若響應點擊事件，返回true，否則返回false
//            //默認返回false
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
////通過LocationClientOption設置LocationClient相關參數
//        LocationClientOption option = new LocationClientOption();
//        option.setOpenGps(true); // 打開gps
//        option.setCoorType("bd09ll"); // 設置坐標類型
//        option.setScanSpan(1000);
//        option.setAddrType("all");
////設置locationClientOption
//        mLocationClient.setLocOption(option);
//
////注冊LocationListener監聽器
//        MyLocationListener myLocationListener = new MyLocationListener();
//        mLocationClient.registerLocationListener(myLocationListener);
////開啟地圖定位圖層
//        mLocationClient.start();
//    }
//    public class MyLocationListener extends BDAbstractLocationListener {
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            //mapView 銷毀後不在處理新接收的位置
//            if (location == null || mapview == null) {
//                return;
//            }
//
//            if (isFirstLocation) {
//                isFirstLocation = false;
//                //設置並顯示中心點
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
//        //在activity執行onResume時執行mMapView. onResume ()，實現地圖生命周期管理
//        mapview.onResume();
//        getHourse();
//    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        //在activity執行onPause時執行mMapView. onPause ()，實現地圖生命周期管理
//        mapview.onPause();
//    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        //在activity執行onDestroy時執行mMapView.onDestroy()，實現地圖生命周期管理
//        mapview.onDestroy();
//    }
//


  private void getHourse() {


  }
}
