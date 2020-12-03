package com.example.roombox.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.roombox.R;
import com.example.roombox.bean.CommentBean;

import com.example.roombox.bean.HotelBean;
import com.example.roombox.utils.Contans;
import com.example.roombox.utils.SimpleAdapter;
import com.example.roombox.utils.TimeUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CommentActivity extends AppCompatActivity {

  private RecyclerView recyclerView;
  private SimpleAdapter adapter;
  private ArrayList<CommentBean> keyList = new ArrayList<>();
  HotelBean scenicBean;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_comment);
    recyclerView = findViewById(R.id.recycler);
    Bundle bundle = getIntent().getExtras();
    scenicBean = (HotelBean) bundle.getSerializable("bean");


    iint();
  }

  private void iint() {
    recyclerView.setLayoutManager(new LinearLayoutManager(CommentActivity.this));

    adapter = new SimpleAdapter(R.layout.reportview_item, keyList, new SimpleAdapter.ConVert<CommentBean>() {
      @Override
      public void convert(BaseViewHolder helper, CommentBean o) {
        String userImg = TextUtils.isEmpty(o.getUserImg()) ? " " : o.getUserImg();
        String name = o.getName();
        if (TextUtils.isEmpty(name)) {
          name = TextUtils.isEmpty(o.getAccount()) ? " " : o.getAccount();
        }
        RequestOptions mRequestOptions = RequestOptions.circleCropTransform()
                .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盤緩存
                .skipMemoryCache(true);//不做內存緩存
        ImageView view = helper.getView(R.id.topImage);
        Glide.with(CommentActivity.this).load(Contans.HEADIMGURL + userImg).apply(mRequestOptions).into(view);
        helper.setText(R.id.name, name);
        String comment = TextUtils.isEmpty(o.getContent()) ? " " : o.getContent();
        helper.setText(R.id.eva1l, comment);
        String time = TextUtils.isEmpty(o.getAddtime()) ? " " : o.getAddtime();

        helper.setText(R.id.desc, TimeUtil.date2TimeStamp(time, "yyyy年MM月"));

      }
    });

    recyclerView.setAdapter(adapter);
    getData();


  }

  private void getData() {
    keyList.clear();
    String url = Contans.URL + "comment/list?hotel_id=" + scenicBean.getHotel_id();
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build();


    final Request request = new Request.Builder()
            .url(url)
            .get()
            .build();

    Call call = okHttpClient.newCall(request);
    call.enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        Log.i("TAG", "onResponse: " + e.toString());
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {


        final String result = response.body().string();
        if (response.body() != null) {

          runOnUiThread(new Runnable() {
            @Override
            public void run() {

              try {
                Log.i("TAG", "run: " + result);
                JSONObject jsonObject = new JSONObject(result);
                String code = jsonObject.getString("code");
                String data = jsonObject.getString("data");
                if ("0".equals(code)) {


                  Contans.makeToast("data update success!", CommentActivity.this);
                  Type type1 = new TypeToken<ArrayList<CommentBean>>() {
                  }.getType();
                  ArrayList<CommentBean> datas = new Gson().fromJson(data, type1);
                  keyList.addAll(datas);
                  adapter.notifyDataSetChanged();


                } else {
                  Contans.makeToast("data update error!", CommentActivity.this);
                }

              } catch (JSONException e) {
                e.printStackTrace();
              } catch (Exception e) {
                e.printStackTrace();
              }

            }
          });


          response.body().close();
        }
      }
    });


  }
}
