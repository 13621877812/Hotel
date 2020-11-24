package com.example.roombox.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {

 public interface  HttpCallBack{
    public void success(JSONObject data);
  }
  public static void httpGet(){

  }
  public static void httpPost(String url,
                              HashMap<String,String> params,
                              final Activity context, final HttpCallBack httpCallBack){
    String httpUrl = Contans.URL + url;
    OkHttpClient okHttpClient  = new OkHttpClient.Builder()
      .connectTimeout(10, TimeUnit.SECONDS)
      .writeTimeout(10,TimeUnit.SECONDS)
      .readTimeout(20, TimeUnit.SECONDS)
      .build();
    FormBody.Builder builder = new FormBody.Builder();
    Set<String> keys = params.keySet();
    for (String key : keys) {
      String value = params.get(key);
      builder.add(key,value);
    }
    RequestBody requestBody = builder.build();
    final Request request = new Request.Builder()
      .url(url)
      .post(requestBody)//默认就是GET请求，可以不写
      .build();
    Call call = okHttpClient.newCall(request);
    call.enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        Log.i("TAG", "fail: " + e.toString());
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        final  String result = response.body().string();
        if (response.body() != null) {

          context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
              try {
                JSONObject jsonObject = new JSONObject(result);
                String code = jsonObject.getString("code");
                if ("0".equals(code)){ //success
                  JSONObject data =  jsonObject.getJSONObject("data");
                  if (httpCallBack != null){
                    httpCallBack.success(data);
                  }

                }else {
                  Contans.makeToast(jsonObject.getString("msg"),context);
                }

              } catch (JSONException e) {
                Contans.makeToast("data error",context);
                e.printStackTrace();
              } catch (Exception e) {
                Contans.makeToast("data error",context);
                e.printStackTrace();
              }

            }
          });
        }
      }

    });


  }




}



