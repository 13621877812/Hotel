package com.example.roombox.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roombox.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity {

  @BindView(R.id.content)
  EditText content;
  @BindView(R.id.otherContent)
  TextView otherContent;
  @BindView(R.id.mineContent)
  TextView mineContent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_chat);
    openTime();
    ButterKnife.bind(this);
  }

  @OnClick(R.id.sendBtn)
  public void onViewClicked() {
    String content1 = content.getText().toString();
    if (content1.length() == 0) {
      Toast.makeText(this, "内容不能为空!", Toast.LENGTH_LONG).show();
      return;
    }
    String mineContent1 = mineContent.getText().toString();
    StringBuffer buffer = new StringBuffer();
    buffer.append(mineContent1);
    buffer.append("\n"+content1);
    mineContent.setText(buffer.toString());
    //发送

  }
  private void openTime(){
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        getContents();
      }
    },0,1000);

  }
  private void getContents(){//返回信息

  }
}
