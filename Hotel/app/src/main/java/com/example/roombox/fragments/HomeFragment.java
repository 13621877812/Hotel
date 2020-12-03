package com.example.roombox.fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.content.Context;
import android.widget.Toast;

import com.example.roombox.bean.ChatBean;
import com.example.roombox.bean.HotelBean;
import com.example.roombox.ui.HotelDetialAct;
import com.example.roombox.utils.HttpUtil;
import com.google.android.gms.common.ConnectionResult;

import com.example.roombox.R;

import com.google.android.gms.common.GooglePlayServicesUtil;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.android.gms.maps.UiSettings;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class HomeFragment extends Fragment implements
        OnMapReadyCallback , GoogleMap.OnMarkerClickListener {

  private MapView mMap;
  private GoogleMap mMap1;
  private View mView;
  private ImageView imgMyLocation;
  ArrayList<HotelBean> hotelDatas;
  java.text.DecimalFormat   df=new   java.text.DecimalFormat("#.#");




  LocationManager lm;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    mView = inflater.inflate(R.layout.fragment_home, container, false);
    initMapView(savedInstanceState);

    mView.findViewById(R.id.toDetail).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        HotelBean bean = hotelDatas.get(0);
        Bundle bundle = new Bundle();
        bundle.putSerializable("hotel",bean);
        Intent intent = new Intent(getActivity(), HotelDetialAct.class);
        intent.putExtras(bundle);
        startActivity(intent);

      }
    });
    initData();

    return mView;
  }
  @Override
  public void onHiddenChanged(boolean hidden) {
    super.onHiddenChanged(hidden);
    if (this != null && !hidden) {
      if (mMap1 != null){
        mMap1.clear();
      }
      initData();
    }
  }
  //獲取房源數據
  private void initData(){

    String url = "hotel/list";
    HttpUtil.httpGet(url, getActivity(), new HttpUtil.HttpCallBack() {
      @Override
      public void success(String data) {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<HotelBean>>() {
        }.getType();
        hotelDatas = gson.fromJson(data, type);

        for (HotelBean bean : hotelDatas){
          addresstoMarker(bean.getPlace(),bean.getName(),bean.getPrice(),bean.getHotel_id());

        }


      }
    });
  }
  private void initMapView(Bundle savedInstanceState) {
    mMap = (MapView) mView.findViewById(R.id.mapview);
    mMap.onCreate(savedInstanceState);


    mMap.onResume();

    try {
      MapsInitializer.initialize(getActivity());
    } catch (Exception e) {
      e.printStackTrace();
    }
    int errorCode = GooglePlayServicesUtil
            .isGooglePlayServicesAvailable(this.getActivity());
    if (ConnectionResult.SUCCESS != errorCode) {
      GooglePlayServicesUtil.getErrorDialog(errorCode,
              this.getActivity(), 0).show();
    } else {
      mMap.getMapAsync(this);
    }

  }








  @Override
  public void onMapReady(GoogleMap googleMap) {
    mMap1 = googleMap;
    // Add a marker in Sydney and move the camera
    imgMyLocation = (ImageView) mView.findViewById(R.id.locatebt);
    lm = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
    UiSettings settings = mMap1.getUiSettings();
    settings.setTiltGesturesEnabled(false);
    settings.setCompassEnabled(true);
    settings.setTiltGesturesEnabled(true);
    settings.setMyLocationButtonEnabled(false);
    settings.setMapToolbarEnabled(false);
    if (ContextCompat.checkSelfPermission(getContext(),
            Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
      //Location Permission already granted
      mMap1.setOnMarkerClickListener(this);
      mMap1.setMyLocationEnabled(true);
      LatLng latLng = new LatLng((lm.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude()), (lm.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude()));
      CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 14);
      mMap1.moveCamera(cameraUpdate);
    }


    imgMyLocation.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        getMyLocation();


      }

      private void getMyLocation() {

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        LatLng latLng = new LatLng((lm.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude()), (lm.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude()));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 14);
        mMap1.moveCamera(cameraUpdate);
      }


    });

  }


  public boolean onMarkerClick(final Marker marker) {
    //轉至訂房頁面


    String id= marker.getId().replaceAll("[A-Za-z]+","");




    int i =Integer.valueOf(id);
    HotelBean bean = hotelDatas.get(i);
    Bundle bundle = new Bundle();
    bundle.putSerializable("hotel",bean);
    Intent intent = new Intent(getActivity(), HotelDetialAct.class);
    intent.putExtras(bundle);
    startActivity(intent);


    return false;
  }


  public void onLocationChanged(Location location, GoogleMap googleMap) {


  }
  public void addresstoMarker(String address,String name,String price,String id){
    double i=Integer.valueOf(price)*0.001;
    price=df.format(i)+"K";
    BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap( drawTextToBitmap(getContext(),price));

    Geocoder geoCoder = new Geocoder(getContext(), Locale.getDefault());
    List<Address> addressLocation = null;
    try {
      addressLocation = geoCoder.getFromLocationName(address, 1);
    } catch (IOException e) {
      e.printStackTrace();
    }
    double latitude = addressLocation.get(0).getLatitude();
    double longitude = addressLocation.get(0).getLongitude();


    mMap1.addMarker(new MarkerOptions().position(new LatLng(latitude,longitude)).icon(icon));



  }

  public static Bitmap drawTextToBitmap(Context gContext, String gText) {
    //加價格至marker

    Resources resources = gContext.getResources();
    float scale = resources.getDisplayMetrics().density;
    Bitmap bitmap =
            BitmapFactory.decodeResource(resources, R.drawable.marker);

    android.graphics.Bitmap.Config bitmapConfig =
            bitmap.getConfig();
    // set default bitmap config if none
    if (bitmapConfig == null) {
      bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888;
    }
    // resource bitmaps are imutable,
    // so we need to convert it to mutable one
    bitmap = bitmap.copy(bitmapConfig, true);

    Canvas canvas = new Canvas(bitmap);
    // new antialised Paint
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    // text color - #3D3D3D
    paint.setColor(Color.rgb(61, 61, 61));
    // text size in pixels
    paint.setTextSize((int) (12 * scale));
    // text shadow
    paint.setShadowLayer(1f, 0f, 1f, Color.WHITE);

    // draw text to the Canvas center
    Rect bounds = new Rect();
//	    paint.setTextAlign(Align.CENTER);

    paint.getTextBounds(gText, 0, gText.length(), bounds);
    int x = (bitmap.getWidth() - bounds.width()) / 2;
    int y = (bitmap.getHeight() + bounds.height()) / 2-10;

//	    canvas.drawText(gText, x * scale, y * scale, paint);
    canvas.drawText(gText, x, y, paint);

    return bitmap;


  }
}



