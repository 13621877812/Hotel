package com.example.roombox.fragments;


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
import com.example.roombox.adapters.PointAdapter;
import com.example.roombox.bean.CollectionBean;

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
  Unbinder unbinder;
  private PointAdapter pointAdapter;

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
    pointAdapter = new PointAdapter(getActivity());
    listView.setAdapter(pointAdapter);
    pointAdapter.setListener(new PointAdapter.ItemClickListener() {
      @Override
      public void itemClick(CollectionBean bean) {

      }
    });


  }

  private void initData() {
    //从网络获取
    ArrayList<CollectionBean> datas = new ArrayList<>();
    CollectionBean collectionBean1 = new CollectionBean();
    collectionBean1.setName("EAST DISTRICT.整套房子");
    collectionBean1.setDesc("南方舟/2～7人大套房/大东夜市");
    collectionBean1.setEval("86人评价");
    collectionBean1.setPrice("$866/每晚");
    datas.add(collectionBean1);
    datas.add(collectionBean1);
    pointAdapter.setDatas(datas);
    pointAdapter.notifyDataSetChanged();
  }


  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @OnClick(R.id.searchBtn)
  public void onViewClicked() {
  }
}
