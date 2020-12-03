package com.example.roombox.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.roombox.R;
import com.example.roombox.adapters.CollectionAdapter;
import com.example.roombox.bean.HotelBean;
import com.example.roombox.ui.HotelDetialAct;
import com.example.roombox.utils.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class SearchFragment extends Fragment {

  @BindView(R.id.searchView)
  SearchView searchView;
  @BindView(R.id.searchBtn)
  TextView searchBtn;
  @BindView(R.id.listView)
  RecyclerView listView;
  ArrayList<HotelBean> datas;
  Unbinder unbinder;
  private CollectionAdapter collectionAdapter;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_search, container, false);
    unbinder = ButterKnife.bind(this, view);
    initView();
    initData();
    return view;
  }

  private void initView() {

    LinearLayoutManager manager = new LinearLayoutManager(getActivity());
    listView.setLayoutManager(manager);
    collectionAdapter = new CollectionAdapter(getActivity());
    listView.setAdapter(collectionAdapter);
    collectionAdapter.setListener(new CollectionAdapter.ItemClickListener() {
      @Override
      public void itemClick(HotelBean bean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("hotel",bean);
        Intent intent = new Intent(getActivity(), HotelDetialAct.class);
        intent.putExtras(bundle);
        startActivity(intent);
      }
    });


  }

  //獲取房源數據
  private void initData(){

    String url = "hotel/list";
    HttpUtil.httpGet(url, getActivity(), new HttpUtil.HttpCallBack() {
      @Override
      public void success(String data) {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<HotelBean>>() {
        }.getType();
        datas = gson.fromJson(data, type);
        collectionAdapter.setDatas(datas);
        collectionAdapter.notifyDataSetChanged();
      }
    });
  }


  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @OnClick(R.id.searchBtn)
  public void onViewClicked() {
    String keyWord = searchView.getQuery().toString();
    ArrayList<HotelBean> data1 = new ArrayList<>();
    for (HotelBean bean:datas){
      String name = bean.getName();
      if (name.contains(keyWord)){
        data1.add(bean);
      }
    }
    collectionAdapter.setDatas(data1);
    collectionAdapter.notifyDataSetChanged();
  }
}
