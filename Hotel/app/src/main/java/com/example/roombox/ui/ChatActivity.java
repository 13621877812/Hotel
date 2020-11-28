package com.example.roombox.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roombox.R;
import com.example.roombox.adapters.ChatAdapter;
import com.example.roombox.bean.ChatBean;
import com.example.roombox.utils.ACache;
import com.example.roombox.utils.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity {

  @BindView(R.id.content)
  EditText content;
  @BindView(R.id.chatList)
  RecyclerView chatList;
  @BindView(R.id.back)
  TextView back;
  @BindView(R.id.titleView)
  TextView titleView;

  private ChatAdapter chatAdapter;
  String otherId;
  String account;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_chat);
    ButterKnife.bind(this);
    otherId = getIntent().getStringExtra("sendId");
    account = ACache.get(this).getAsString("account");
    initView();
    openTime();
    getContents();
  }

  private void initView() {


      titleView.setText(otherId);
    LinearLayoutManager manager = new LinearLayoutManager(ChatActivity.this);
    chatList.setLayoutManager(manager);
    chatAdapter = new ChatAdapter(this);
    chatList.setAdapter(chatAdapter);
    chatAdapter.setListener(new ChatAdapter.ItemClickListener() {
      @Override
      public void itemClick(ChatBean bean) {

      }
    });


  }

  @OnClick(R.id.sendBtn)
  public void onViewClicked() {
    String content1 = content.getText().toString();
    if (content1.length() == 0) {
      Toast.makeText(this, "内容不能为空!", Toast.LENGTH_LONG).show();
      return;
    }


    HashMap params = new HashMap();

    params.put("sendId", account);//当前用户id
    params.put("receiveId", otherId);//接收用户
    params.put("content", content1);//聊天内
    HttpUtil.httpPost("chat/add", params, ChatActivity.this, new HttpUtil.HttpCallBack() {

      @Override
      public void success(String data) {
        content.setFocusable(false);
        content.setText("");
        hideKeyboard();
        getContents();
      }
    });


  }

  private void hideKeyboard() {
    View v = getCurrentFocus();
    if (v != null) {
      InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      im.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
  }

  private void openTime() {
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
//        getContents();
      }
    }, 0, 1000);
  }

  //获取聊天列表内容
  private void getContents() {//返回信息

    String url = "chat/list?sendId=" + otherId + "&receiveId=" + account;
    HttpUtil.httpGet(url, ChatActivity.this, new HttpUtil.HttpCallBack() {
      @Override
      public void success(String data) {
        Gson gson = new Gson();
        Type userListType = new TypeToken<ArrayList<ChatBean>>() {
        }.getType();
        List<ChatBean> datas = gson.fromJson(data, userListType);
        chatAdapter.setDatas(datas);
        chatAdapter.notifyDataSetChanged();
      }
    });
  }


}
