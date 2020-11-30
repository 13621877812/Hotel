package com.example.roombox.utils;

import android.app.Activity;
import android.util.Log;

import com.example.roombox.base.ResultBean;
import com.example.roombox.ui.AddHotelActivity;
import com.google.gson.Gson;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {

  public interface HttpCallBack {
    public void success(String data);
  }

  public static void httpGet(final String url, final Activity context, final HttpCallBack httpCallBack) {
    Call call = getHttpCall(url, null);
    call.enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        Log.i("TAG", "fail: " + e.toString());
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        if (response.body() != null) {
          final String result = response.body().string();
          dealData(context, httpCallBack, result,url);
        }
      }

    });
  }

  public static void httpPost(final String url,
                              HashMap<String, String> params,
                              final Activity context, final HttpCallBack httpCallBack) {
    Call call = getHttpCall(url, params);
    call.enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        Log.i("TAG", "fail: " + e.toString());
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        final String result = response.body().string();
        if (response.body() != null) {
          dealData(context, httpCallBack, result,url);
        }
      }

    });


  }

  public static void httpUpload(String url,
                              HashMap<String, String> params,
                              final Activity context, final HttpCallBack httpCallBack) {




  }

  private static Call getHttpCall(String url,
                                  HashMap<String, String> params) {
    String httpUrl = Contans.URL + url;
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
      .connectTimeout(10, TimeUnit.SECONDS)
      .writeTimeout(10, TimeUnit.SECONDS)
      .readTimeout(20, TimeUnit.SECONDS)
      .build();
    RequestBody requestBody = null;
    if (params != null) {
      FormBody.Builder builder = new FormBody.Builder();
      Set<String> keys = params.keySet();
      for (String key : keys) {
        String value = params.get(key);
        builder.add(key, value);
      }
      requestBody = builder.build();
    }
    Request.Builder requestBuild = new Request.Builder();
    if (requestBody != null) {
      requestBuild.post(requestBody);
    }
    Request request = requestBuild.url(httpUrl).build();
    Call call = okHttpClient.newCall(request);
    return call;
  }

  private static void dealData(final Activity context,
                               final HttpCallBack httpCallBack,
                               final String data,String url) {
    Log.i("HTTP", "dealData: " + url);
    Log.i("HTTP", "dealData: " + data);
    context.runOnUiThread(new Runnable() {
      @Override
      public void run() {

        Gson gson = new Gson();
        Type resultType = new TypeToken<ResultBean>() {
        }.getType();
        ResultBean bean = gson.fromJson(data, resultType);
        String code = bean.getCode();
        if ("0".equals(code)) { //// succes
          Object object = bean.getData();
          if (httpCallBack != null) {
            httpCallBack.success(object == null ? "" :gson.toJson(object) );
          }
        } else {
          Contans.makeToast(bean.getMsg(), context);
        }


      }
    });
  }


}



