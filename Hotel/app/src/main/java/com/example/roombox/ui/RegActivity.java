package com.example.roombox.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.roombox.R;
import com.example.roombox.base.BaseActivity;
import com.example.roombox.bean.User;

import com.example.roombox.utils.ACache;
import com.example.roombox.utils.Contans;
import com.google.gson.Gson;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class RegActivity extends BaseActivity implements View.OnClickListener {


    private EditText et_account;
    private EditText et_pwd;
    private EditText et_pwd_once;
    private Button bt_Login;
    private RadioGroup radioGroup;
    private Integer type = 0;


    @Override
    public int intiLayout() {
        return R.layout.act_reg;
    }

    @Override
    public void initView() {

        et_account = (EditText) findViewById(R.id.et_account);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        et_pwd_once = (EditText) findViewById(R.id.et_pwd_once);
        bt_Login = (Button) findViewById(R.id.bt_Login);
        radioGroup = (RadioGroup) findViewById(R.id.typeGroup);//获取单选按钮组
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton =(RadioButton) findViewById(i);//获取被选择的单选按钮
                type = radioButton.getText().equals("owner")?1:0;
            }
        });

        bt_Login.setOnClickListener(this);
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

        String once = et_pwd_once.getText().toString().trim();
        if (TextUtils.isEmpty(once)) {
            Toast.makeText(this, "please enter your password again", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pwd.equals(once)) {
            Toast.makeText(this, "the password is not equal confirm password", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
        reg(account,pwd);

    }

    private void reg(final String name, final String pwd) {
        String url = Contans.URL + "user/regist";
        OkHttpClient okHttpClient  = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        FormBody formBody = new FormBody.Builder()
                .add("account", name)
                .add("password", pwd)
                .add("isowner", type.toString())
                .build();
        final Request request = new Request.Builder()
                .url(url)
                .post(formBody)//默认就是GET请求，可以不写
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
                                    Contans.makeToast("regist success!",RegActivity.this);

                                    finish();

                                }else {
                                    Contans.makeToast(jsonObject.getString("msg"),RegActivity.this);
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

    private void save(String name,String  pwd) {
        User category = new User();
        category.username = (name);
        category.password = (pwd);


    }


}
