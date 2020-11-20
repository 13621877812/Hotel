package com.fn.hotel.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import com.fn.hotel.R;

import com.fn.hotel.bean.HotelBean;
import com.fn.hotel.utils.ACache;
import com.fn.hotel.utils.Contans;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
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
        if (TextUtils.isEmpty(editText.getText())){
            Contans.makeToast("comment is empty！",CriticalActivity.this);
            return;
        }

        saveData(editText.getText().toString());

    }
    private void saveData(String comment) {
        HotelBean bean = (HotelBean)getIntent().getExtras().getSerializable("bean");
        String hotel_id = bean.getHotel_id();
        String username = ACache.get(CriticalActivity.this).getAsString("account");
        String url = Contans.URL + "comment/add";
        OkHttpClient okHttpClient  = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        FormBody formBody = new FormBody.Builder()
                .add("hotel_id", hotel_id)
//                .add("userID", userID)
                .add("user_id", username)
                .add("comment", comment)
                .add("grade", String.valueOf(ratingBar.getRating()))
                .build();
        final Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("TAG", "onResponse: " + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


                final  String result = response.body().string();
                if (response.body() != null) {

                   runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                Log.i("TAG", "run: " + result);
                                JSONObject jsonObject = new JSONObject(result);
                                String code = jsonObject.getString("code");
                                if ("0".equals(code)){
                                    Contans.makeToast("comment submit success!",CriticalActivity.this);
                                    finish();
                                }else {
                                    Contans.makeToast("comment submit error!",CriticalActivity.this);
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
