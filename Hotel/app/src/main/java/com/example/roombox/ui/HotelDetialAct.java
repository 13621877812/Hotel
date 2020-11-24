package com.example.roombox.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.roombox.R;
import com.example.roombox.base.BaseActivity;
import com.example.roombox.bean.CommentBean;
import com.example.roombox.bean.HotelBean;
import com.example.roombox.utils.Contans;
import com.example.roombox.utils.SimpleAdapter;
import com.example.roombox.utils.TimeUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HotelDetialAct extends BaseActivity {

    private HotelBean bean;

    private ImageView iv;
    private TextView nameText;
    private TextView priceText;
    private TextView typeText;
    private TextView placeText;
    private TextView areaText;
    private TextView telText;
    private TextView gradeText;

    ImageView banner;


    RecyclerView recyclerView;
    private SimpleAdapter adapter;
    private ArrayList<CommentBean> keyList = new ArrayList<>();
    @Override
    public int intiLayout() {
        return R.layout.act_hd;
    }

    @Override
    public void initView() {
      recyclerView = findViewById(R.id.listView);
      iint();
//        initBanner();
    }
  private void iint() {
    recyclerView.setLayoutManager(new LinearLayoutManager(HotelDetialAct.this));

    adapter = new SimpleAdapter(R.layout.common_item, keyList, new SimpleAdapter.ConVert<CommentBean>() {
      @Override
      public void convert(BaseViewHolder helper, CommentBean o) {
//        String userImg = TextUtils.isEmpty(o.getUserImg())?" ":o.getUserImg();
//        String name = o.getName();
//        if (TextUtils.isEmpty(name)){
//          name = TextUtils.isEmpty(o.getAccount())?" ":o.getAccount();
//        }
//        RequestOptions mRequestOptions = RequestOptions.circleCropTransform()
//          .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
//          .skipMemoryCache(true);//不做内存缓存
//        ImageView view = helper.getView(R.id.userImg);
//        Glide.with(HotelDetialAct.this).load(Contans.HEADIMGURL + userImg ).apply(mRequestOptions).into(view);
//        helper.setText(R.id.user, name);
//
//
////                helper.setText(R.id.grade,  o.getGrade()+"");
//        RatingBar ratingBar = helper.getView(R.id.ratingBar);
//        ratingBar.setRating(Float.valueOf(o.getGrade()));
//
//
//        String comment = TextUtils.isEmpty(o.getContent())?" ":o.getContent();
//        helper.setText(R.id.comment, comment);
//        String time = TextUtils.isEmpty(o.getAddtime())?" ":o.getAddtime();
//
//        helper.setText(R.id.time, TimeUtil.date2TimeStamp(time,"HH:mm yyyy/MM/dd"));

      }
    });

    recyclerView.setAdapter(adapter);



  }

  @Override
  public void initData() {

  }

  @Override
    protected void onResume() {
        super.onResume();

//        refreshData();



    }
    private void setViewData(){

    }
//    private void refreshData(){
//        String url = Contans.URL + "hotel/getOne?hotel_id="+bean.getHotel_id();
//        OkHttpClient okHttpClient  = new OkHttpClient.Builder()
//                .connectTimeout(10, TimeUnit.SECONDS)
//                .writeTimeout(10,TimeUnit.SECONDS)
//                .readTimeout(20, TimeUnit.SECONDS)
//                .build();
//
//        final Request request = new Request.Builder()
//                .url(url)
//                .get()
//                .build();
//
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.i("TAG", "onResponse: " + e.toString());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//
//                final  String result = response.body().string();
//                if (response.body() != null) {
//
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            try {
//                                Log.i("TAG", "run: " + result);
//                                JSONObject jsonObject = new JSONObject(result);
//                                String code = jsonObject.getString("code");
//
//                                if ("0".equals(code)){
//                                    String data = jsonObject.getString("data");
//                                    Type type1 = new TypeToken<HotelBean>(){}.getType();
//                                    bean = new Gson().fromJson(data,type1);
//                                    setViewData();
//
//
//                                }else {
//                                    Contans.makeToast("data error!",HotelDetialAct.this);
//                                }
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//                    });
//
//
//                    response.body().close();
//                }
//            }
//        });
//
//    }
//
//    private void initBanner() {
////        if (TextUtils.isEmpty(bean.getImages())){
////            return;
////        }
////        String[] images = bean.getImages().split(",");
////        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
////        //设置图片加载器
////        banner.setImageLoader(new GlideImageLoader());
////
////        if (images != null && images.length > 0) {
////            //banner url集合
////            List<String> mImageUrls = new ArrayList<>();
////            for (int i = 0; i < images.length; i++) {
////                String path = Contans.URL + "images/"  + bean.getName() + "/" + images[i];
////                mImageUrls.add(path);
////            }
////            //设置图片集合
////            banner.setImages(mImageUrls);
////        }
////
////        //banner设置方法全部调用完毕时最后调用
////        banner.start();
//    }
//
//    @Override
//    public void initData() {
//
//    }
//
//    public static boolean isQQClientAvailable(Context context) {
//        final PackageManager packageManager = context.getPackageManager();
//        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
//        if (pinfo != null) {
//            for (int i = 0; i < pinfo.size(); i++) {
//                String pn = pinfo.get(i).packageName;
//                if (pn.equals("com.tencent.mobileqq")) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//
//    private void commit() {
//
//
//
//    }
//
//    private void upDate() {
//
//
//    }
//
//
//    @Override
//    public void onClick(View v) {
////     switch (v.getId()){
////         case R.id.checkComment:
////         {
////
////             Intent intent = new Intent(HotelDetialAct.this,CommentActivity.class);
////             Bundle bundle = new Bundle();
////             bundle.putSerializable("bean",bean);
////             intent.putExtras(bundle);
////             startActivity(intent);
////
////
////         }
////         break;
////         case R.id.comment:
////         {
////             Intent intent = new Intent(HotelDetialAct.this,CriticalActivity.class);
////             Bundle bundle = new Bundle();
////             bundle.putSerializable("bean",bean);
////             intent.putExtras(bundle);
////             startActivity(intent);
////         }
////         break;
////
////         default:
////          break;
////
////     }
//    }
//
//    private boolean isInstalled(String packageName) {
//        PackageManager manager = HotelDetialAct.this.getPackageManager();
//        //获取所有已安装程序的包信息
//        List<PackageInfo> installedPackages = manager.getInstalledPackages(0);
//        if (installedPackages != null) {
//            for (PackageInfo info : installedPackages) {
//                if (info.packageName.equals(packageName)) {
//                    return true;
//                }
//
//            }
//        }
//        return false;
//    }
//    public class GlideImageLoader extends ImageLoader {
//        @Override
//        public void displayImage(Context context, Object path, ImageView imageView) {
//            //Glide 加载图片简单用法
//            Glide.with(context).load(path).into(imageView);
//        }
//    }
//    @SuppressLint("MissingPermission")
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case 110: //拨打电话
//                if (permissions.length != 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {//失败
//
//                } else {//成功
//                    Intent intent = new Intent(Intent.ACTION_CALL);
//                    Uri data = Uri.parse("tel:" + bean.getTel());
//                    intent.setData(data);
//                    startActivity(intent);
//                }
//                break;
//                default:
//                    break;
//        }
//    }
//


}
