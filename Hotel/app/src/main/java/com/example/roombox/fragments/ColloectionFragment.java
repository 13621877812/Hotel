package com.example.roombox.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roombox.R;
import com.example.roombox.adapters.PointAdapter;
import com.example.roombox.bean.CollectionBean;
import com.example.roombox.bean.HotelBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColloectionFragment extends Fragment {

  Unbinder unbinder;
  @BindView(R.id.listView)
  RecyclerView listView;

  private PointAdapter pointAdapter;
  private long totalPoints;

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
    pointAdapter = new PointAdapter(getActivity());
    listView.setAdapter(pointAdapter);
    pointAdapter.setListener(new PointAdapter.ItemClickListener() {
      @Override
      public void itemClick(HotelBean bean) {

      }
    });


  }

  private void initData() {
    //从网络获取
    ArrayList<HotelBean> datas = new ArrayList<>();
    HotelBean collectionBean1 = new HotelBean();
    collectionBean1.setName("EAST DISTRICT.整套房子");
    collectionBean1.setIntro("南方舟/2～7人大套房/大东夜市");
    collectionBean1.setArea("86人评价");
    collectionBean1.setPrice("$866/每晚");
    datas.add(collectionBean1);
    datas.add(collectionBean1);
    pointAdapter.setDatas(datas);
    pointAdapter.notifyDataSetChanged();
  }


  private void refreshDatas(Object index) {
    int idx = Integer.parseInt(index.toString());
    switch (idx) {
      case 0:

        break;
      case 1:

        break;
      case 2:

        break;
      default:
        break;
    }

    pointAdapter.notifyDataSetChanged();

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
