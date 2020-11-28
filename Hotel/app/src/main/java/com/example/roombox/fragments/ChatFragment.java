package com.example.roombox.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.roombox.R;
import com.example.roombox.bean.ChatBean;
import com.example.roombox.ui.ChatActivity;
import com.example.roombox.ui.HotelDetialAct;
import com.example.roombox.utils.ACache;
import com.example.roombox.utils.HttpUtil;
import com.example.roombox.utils.SimpleAdapter;
import com.example.roombox.utils.TimeUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment {
  @BindView(R.id.listView)
  RecyclerView listView;
  private SimpleAdapter adapter;
  private ArrayList<ChatBean> keyList = new ArrayList<>();
  Unbinder unbinder;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_chat, container, false);
    unbinder = ButterKnife.bind(this, view);
    initListView();
    initData();
    return view;
  }

  private void initListView() {
    listView.setLayoutManager(new LinearLayoutManager(getActivity()));

    adapter = new SimpleAdapter(R.layout.item_chat, keyList, new SimpleAdapter.ConVert<ChatBean>() {
      @Override
      public void convert(BaseViewHolder helper, ChatBean o) {
        String name = o.getSendName();
        String content = o.getContent();
        String time = o.getCreateTime();
        helper.setText(R.id.tv_name, name);
        helper.setText(R.id.tv_content, content);
        helper.setText(R.id.tv_time, TimeUtil.date2TimeStamp(time,"HH:mm yyyy/MM/dd"));

      }
    });
    adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
      @Override
      public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ChatBean chatBean = keyList.get(position);
        String account = ACache.get(getActivity()).getAsString("account");
        String sendId = account.equals(chatBean.getSendId())?chatBean.getReceiveId():chatBean.getSendId();
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        intent.putExtra("sendId",sendId);
        startActivity(intent);

      }
    });

    listView.setAdapter(adapter);
  }

  private void initData() {
    String url = "chat/list?sendId=1&receiveId=2";
    HttpUtil.httpGet(url, getActivity(), new HttpUtil.HttpCallBack() {
      @Override
      public void success(String data) {
        Gson gson = new Gson();
        Type userListType = new TypeToken<ArrayList<ChatBean>>() {
        }.getType();
        List<ChatBean> datas = gson.fromJson((String) data, userListType);
        keyList.clear();
        keyList.addAll(datas);
      }
    });


  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @OnClick(R.id.startBtn)
  public void onViewClicked() {
    initData();


  }
}
