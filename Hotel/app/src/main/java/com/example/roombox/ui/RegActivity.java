package com.example.roombox.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.roombox.R;
import com.example.roombox.base.BaseActivity;
import com.example.roombox.bean.User;
import com.example.roombox.utils.Contans;
import com.example.roombox.utils.HttpUtil;

import java.util.HashMap;


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
    radioGroup = (RadioGroup) findViewById(R.id.typeGroup);//獲取單選按鈕組
    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup radioGroup, int i) {
        RadioButton radioButton = (RadioButton) findViewById(i);//獲取被選擇的單選按鈕
        type = radioButton.getText().equals("owner") ? 1 : 0;
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

    reg(account, pwd);

  }

  private void reg(final String name, final String pwd) {
    String url = "user/regist";
    HashMap<String, Object> params = new HashMap<>();
    params.put("account", name);
    params.put("password", pwd);
    params.put("type", type.toString());
    HttpUtil.httpPost(url, params, RegActivity.this, new HttpUtil.HttpCallBack() {

      @Override
      public void success(String data) {
        Contans.makeToast("regist success!", RegActivity.this);
        finish();
      }
    });

  }

  private void save(String name, String pwd) {
    User category = new User();
    category.username = (name);
    category.password = (pwd);


  }


}
