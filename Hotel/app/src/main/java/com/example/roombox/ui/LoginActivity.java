package com.example.roombox.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roombox.HotelListActivity;
import com.example.roombox.MainActivity;
import com.example.roombox.R;
import com.example.roombox.base.BaseActivity;
import com.example.roombox.utils.ACache;
import com.example.roombox.utils.Contans;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LoginActivity extends BaseActivity implements View.OnClickListener {


    private EditText et_account;
    private EditText et_pwd;
    private Button tv_reg;
    private Button bt_Login;


    @Override
    public int intiLayout() {
        return R.layout.act_login;
    }

    @Override
    public void initView() {

        et_account = (EditText) findViewById(R.id.et_account);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        tv_reg = (Button) findViewById(R.id.tv_reg);
        bt_Login = (Button) findViewById(R.id.bt_Login);

        bt_Login.setOnClickListener(this);
        tv_reg.setOnClickListener(this);
    }

    @Override
    public void initData() {


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_Login:
                    submit();
                break;
                case R.id.tv_reg:
                startActivity(new Intent(LoginActivity.this,RegActivity.class));
                break;
        }
    }

    private void submit() {
        // validate
        String account = et_account.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            Toast.makeText(this, "please enter your account", Toast.LENGTH_SHORT).show();
            return;
        }

        String pwd = et_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something

        login(account,pwd);


    }


        private void login(final String name, final String pwd) {
            String url = Contans.URL + "user/login";
            OkHttpClient okHttpClient  = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10,TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .build();
            RequestBody requestBody = new FormBody.Builder().add("account",name).add("password",pwd).build();
            final Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)//默认就是GET请求，可以不写
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
                                        JSONObject data =  jsonObject.getJSONObject("data");
                                        Contans.makeToast("login success!",LoginActivity.this);

                                        String userimage = TextUtils.isEmpty(data.getString("userimage"))?"":data.getString("userimage");
                                        String username = TextUtils.isEmpty(data.getString("username"))?"":data.getString("username");
                                        String gender = TextUtils.isEmpty(data.getString("gender"))?"":data.getString("gender");
                                        ACache.get(LoginActivity.this).put("account",name);
                                        ACache.get(LoginActivity.this).put("userimage",userimage);
                                        ACache.get(LoginActivity.this).put("username",username);
                                        ACache.get(LoginActivity.this).put("gender",gender);
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                        finish();


                                    }else {
                                        Contans.makeToast(jsonObject.getString("msg"),LoginActivity.this);
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
