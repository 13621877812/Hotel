package com.example.roombox.fragments;


import android.Manifest;
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
import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.content.Context;
import android.widget.Toast;

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

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class HomeFragment extends Fragment implements
        OnMapReadyCallback , GoogleMap.OnMarkerClickListener {

  private MapView mMap;
  private GoogleMap mMap1;
  private View mView;
  private ImageView imgMyLocation;


  LocationManager lm;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    mView = inflater.inflate(R.layout.fragment_home, container, false);
    initMapView(savedInstanceState);

    return mView;
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
    String price="3.1K";







    BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap( drawTextToBitmap(getContext(),price));


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



      //地址轉經緯
      String addressString ="台北市中正區館前路2號";
      Geocoder geoCoder = new Geocoder(getContext(), Locale.getDefault());
      List<Address> addressLocation = null;
      try {
        addressLocation = geoCoder.getFromLocationName(addressString, 1);
      } catch (IOException e) {
        e.printStackTrace();
      }
      double latitude = addressLocation.get(0).getLatitude();
      double longitude = addressLocation.get(0).getLongitude();

      LatLng latLng4 = new LatLng((latitude), (longitude));
      mMap1.addMarker(new MarkerOptions().position(latLng4).icon(icon));




      LatLng latLng = new LatLng((lm.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude()), (lm.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude()));
      mMap1.addMarker(new MarkerOptions().position(latLng).icon(icon));
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
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 18);
        mMap1.moveCamera(cameraUpdate);
      }


    });
//    // 移动地图到指定经度的位置
//    googleMap.moveCamera(CameraUpdateFactory.newLatLng(appointLoc));
//    //添加标记到指定经纬度
//    googleMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).title("Marker")
//      .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
  }


  public boolean onMarkerClick(final Marker marker) {
    //轉至訂房頁面
    // Retrieve the data from the marker.
    Integer clickCount = (Integer) marker.getTag();

    // Check if a click count was set, then display the click count.

    Toast.makeText(getContext(), "f", Toast.LENGTH_SHORT).show();


    // Return false to indicate that we have not consumed the event and that we wish
    // for the default behavior to occur (which is for the camera to move such that the
    // marker is centered and for the marker's info window to open, if it has one).
    return false;
  }


  public void onLocationChanged(Location location, GoogleMap googleMap) {


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

