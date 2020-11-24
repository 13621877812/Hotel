package com.example.roombox.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.roombox.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class HomeFragment extends Fragment implements OnMapReadyCallback {

    private MapView mMap;
    private GoogleMap mMap1;
    private View mView;

    public HomeFragment() {

    }



  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       mView = inflater.inflate(R.layout.fragment_home, container, false);
       initMapView(savedInstanceState);
       return mView;
    }
    private void initMapView(Bundle savedInstanceState){
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
       LatLng sydney = new LatLng(-34, 151);
       mMap1.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
       mMap1.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//    double lat = 40.73;
//    double lng = -73.99;
//    LatLng appointLoc = new LatLng(lat, lng);
//    // 移动地图到指定经度的位置
//    googleMap.moveCamera(CameraUpdateFactory.newLatLng(appointLoc));
//    //添加标记到指定经纬度
//    googleMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).title("Marker")
//      .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
   }
}
