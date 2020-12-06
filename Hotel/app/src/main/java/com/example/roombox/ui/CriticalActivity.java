package com.example.roombox.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.roombox.R;

import com.example.roombox.bean.HotelBean;
import com.example.roombox.utils.ACache;
import com.example.roombox.utils.Contans;
import com.example.roombox.utils.HttpUtil;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CriticalActivity extends AppCompatActivity implements View.OnClickListener {

  private RatingBar ratingBar;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_critical);

    findViewById(R.id.submit).setOnClickListener(this);
    ratingBar = findViewById(R.id.ratingBar);
  }

  @Override
  public void onClick(View v) {
    EditText editText = findViewById(R.id.comment);
    if (TextUtils.isEmpty(editText.getText())) {
      Contans.makeToast("comment is empty！", CriticalActivity.this);
      return;
    }

    saveData(editText.getText().toString());

  }

  private void saveData(String comment) {
    HotelBean  hotelBean = (HotelBean) getIntent().getExtras().getSerializable("hotel");
    String hotel_id = hotelBean.getHotel_id();
    String account = ACache.get(CriticalActivity.this).getAsString("account");
    String url = "comment/add";
    Double hotel_id1 = Double.parseDouble(hotel_id) ;
    HashMap params = new HashMap();
    params.put("hotel_id",hotel_id1.intValue()+"");
    params.put("account",account);
    params.put("comment",comment);
    params.put("grade",String.valueOf(ratingBar.getRating()));

    HttpUtil.httpPost(url, params, CriticalActivity.this, new HttpUtil.HttpCallBack() {
      @Override
      public void success(String data) {
        Contans.makeToast("comment submit success!", CriticalActivity.this);
        finish();
      }
    });

  }

}
