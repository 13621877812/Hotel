package com.example.roombox.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.roombox.R;
import com.example.roombox.adapters.CollectionAdapter;
import com.example.roombox.bean.HotelBean;
import com.example.roombox.utils.ACache;
import com.example.roombox.utils.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColloectionFragment extends Fragment {

  Unbinder unbinder;
  @BindView(R.id.listView)
  RecyclerView listView;
  @BindView(R.id.totalText)
  TextView totalText;

  private CollectionAdapter collectionAdapter;

  public ColloectionFragment() {

  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_comment, container, false);
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
      }
    });
  }
  private void initData() {
    //从网络获取
    String account = ACache.get(getActivity()).getAsString("account");
    String url = "collection/list?account=" + account;
    HttpUtil.httpGet(url, getActivity(), new HttpUtil.HttpCallBack() {
      @Override
      public void success(String data) {
        Log.i("COLLECTION", "success: ");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<HotelBean>>() {
        }.getType();
        ArrayList<HotelBean> datas = gson.fromJson(data, type);
        totalText.setText(datas.size() + "间房源");
        collectionAdapter.setDatas(datas);
        collectionAdapter.notifyDataSetChanged();
      }
    });

  }


  @OnClick(R.id.editBtn)
  public void onViewClicked() {
  }

  @Override
  public void onResume() {
    super.onResume();


  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }


}
