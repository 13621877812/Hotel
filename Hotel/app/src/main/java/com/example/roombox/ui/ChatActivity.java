package com.example.roombox.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roombox.R;
import com.example.roombox.adapters.ChatAdapter;
import com.example.roombox.utils.HttpUtil;

import org.json.JSONObject;

import java.util.HashMap;
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

  private ChatAdapter chatAdapter;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_chat);
    initView();
    openTime();
    ButterKnife.bind(this);

  }
   private void initView(){




   }
  @OnClick(R.id.sendBtn)
  public void onViewClicked() {
    String content1 = content.getText().toString();
    if (content1.length() == 0) {
      Toast.makeText(this, "内容不能为空!", Toast.LENGTH_LONG).show();
      return;
    }
    HashMap params = new HashMap();
    params.put("sendId","2345");//当前用户id
    params.put("receiveId","content1");//接收用户
    params.put("content",content1);//聊天内
    HttpUtil.httpPost("chat/add",params,ChatActivity.this,new HttpUtil.HttpCallBack(){

      @Override
      public void success(JSONObject data) {
        getContents();
      }
    });


  }

  private void openTime() {
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        getContents();
      }
    }, 0, 1000);
  }
  //获取聊天列表内容
  private void getContents() {//返回信息
    String url = "chat/list?chatId=" + "对方用户id";
    HttpUtil.httpGet(url, ChatActivity.this, new HttpUtil.HttpCallBack() {
      @Override
      public void success(JSONObject data) {
        chatAdapter.notifyDataSetChanged();
      }
    });
  }


}
