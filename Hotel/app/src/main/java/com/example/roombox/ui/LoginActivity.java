package com.example.roombox.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roombox.MainActivity;
import com.example.roombox.R;
import com.example.roombox.base.BaseActivity;
import com.example.roombox.utils.ACache;
import com.example.roombox.utils.Contans;
import com.example.roombox.utils.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


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
        startActivity(new Intent(LoginActivity.this, RegActivity.class));
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


    login(account, pwd);


  }


  private void login(final String name, final String pwd) {
    String url = "user/login";
    HashMap params = new HashMap();
    params.put("account", name);
    params.put("password", pwd);
    HttpUtil.httpPost(url, params, LoginActivity.this, new HttpUtil.HttpCallBack() {
      @Override
      public void success(String data1) {

        Contans.makeToast("login success!", LoginActivity.this);
        try {
          JSONObject data = new JSONObject((String) data1);
          String account = TextUtils.isEmpty(data.getString("account")) ? "" : data.getString("account");
          String type = TextUtils.isEmpty(data.getString("type")) ? "" : data.getString("type");
//
          ACache.get(LoginActivity.this).put("account", account);
          ACache.get(LoginActivity.this).put("type", type);
          startActivity(new Intent(LoginActivity.this, MainActivity.class));
          finish();
        } catch (JSONException e) {
          e.printStackTrace();
        }


      }
    });


  }


}
