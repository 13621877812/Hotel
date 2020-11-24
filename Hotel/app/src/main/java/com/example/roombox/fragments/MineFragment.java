package com.example.roombox.fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.example.roombox.R;
import com.example.roombox.ui.HotelDetialAct;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {


  Unbinder unbinder;
  @BindView(R.id.gridView)
  GridView gridView;
  private double progressNum = 0.00;

  public MineFragment() {

  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_mine, container, false);
    unbinder = ButterKnife.bind(this, view);
    initView();
    return view;
  }
  private void initView(){
    ArrayList<HashMap<String,Object>> meumList = new ArrayList<HashMap<String, Object>>();
    String[] datas = new String[]{"历史订单","房屋管理","客服","登出","评论"};
    String[] datas1 = new String[]{"房屋管理","举报管理","登出"};
    for(int i = 0; i < datas.length; i++){
      HashMap<String,Object> map = new HashMap<String, Object>();
      map.put("ItemImage",R.mipmap.logo);
      map.put("ItemText",""+datas[i]);
      meumList.add(map);
    }
    SimpleAdapter saItem = new SimpleAdapter(getActivity(),meumList,R.layout.item_mine_layout,
      new String[]{"ItemText"},
      new int[]{R.id.ItemText});

    gridView.setAdapter(saItem);
    gridView.setOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int index = position + 1;
        Toast.makeText(getActivity(),"点击了选项：" + index,Toast.LENGTH_LONG).show();
      }
    });

  }
  @Override
  public void onHiddenChanged(boolean hidden) {
    super.onHiddenChanged(hidden);
    if (hidden == false) {


    }
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
