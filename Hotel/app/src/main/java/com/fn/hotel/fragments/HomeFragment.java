package com.fn.hotel.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fn.hotel.R;
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
    private View mView;
    private Bundle savedInstanceState;
    public HomeFragment() {

    }

  public void setSavedInstanceState(Bundle savedInstanceState) {
    this.savedInstanceState = savedInstanceState;

  }

  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       mView = inflater.inflate(R.layout.fragment_home, container, false);
       initMapView();
       return mView;
    }
    private void initMapView(){
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
    double lat = 40.73;
    double lng = -73.99;
    LatLng appointLoc = new LatLng(lat, lng);
    // 移动地图到指定经度的位置
    googleMap.moveCamera(CameraUpdateFactory.newLatLng(appointLoc));
    //添加标记到指定经纬度
    googleMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).title("Marker")
      .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
   }
}
