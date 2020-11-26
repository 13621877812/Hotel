package com.example.roombox.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.roombox.R;
import com.example.roombox.bean.ChatBean;
import com.example.roombox.utils.HttpUtil;
import com.example.roombox.utils.SimpleAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

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
        String name = TextUtils.isEmpty(o.getSendName()) ? "AKi" : o.getContent();
        String content = TextUtils.isEmpty(o.getContent()) ? "有活动" : o.getContent();
        String time = TextUtils.isEmpty(o.getCreateTime()) ? "10月31" : o.getCreateTime();
        helper.setText(R.id.tv_name, name);
        helper.setText(R.id.tv_content, content);
        helper.setText(R.id.tv_time, time);
//        String name = o.getName();
//        if (TextUtils.isEmpty(name)){
//          name = TextUtils.isEmpty(o.getAccount())?" ":o.getAccount();
//        }
//        RequestOptions mRequestOptions = RequestOptions.circleCropTransform()
//          .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
//          .skipMemoryCache(true);//不做内存缓存
//        ImageView view = helper.getView(R.id.userImg);
//        Glide.with(HotelDetialAct.this).load(Contans.HEADIMGURL + userImg ).apply(mRequestOptions).into(view);
//        helper.setText(R.id.user, name);
//
//
////                helper.setText(R.id.grade,  o.getGrade()+"");
//        RatingBar ratingBar = helper.getView(R.id.ratingBar);
//        ratingBar.setRating(Float.valueOf(o.getGrade()));
//
//
//        String comment = TextUtils.isEmpty(o.getContent())?" ":o.getContent();
//        helper.setText(R.id.comment, comment);
//        String time = TextUtils.isEmpty(o.getAddtime())?" ":o.getAddtime();
//
//        helper.setText(R.id.time, TimeUtil.date2TimeStamp(time,"HH:mm yyyy/MM/dd"));

      }
    });

    listView.setAdapter(adapter);
  }

  private void initData() {
    String url = "chat/list?sendId=1&receiveId=2";
    HttpUtil.httpGet(url, getActivity(), new HttpUtil.HttpCallBack() {
      @Override
      public void success(JSONObject data) {
        Gson gson = new Gson();
        Type userListType = new TypeToken<ArrayList<ChatBean>>() {
        }.getType();
        List<ChatBean> datas = gson.fromJson(data.toString(), userListType);
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
