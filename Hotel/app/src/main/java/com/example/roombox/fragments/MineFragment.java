package com.example.roombox.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.roombox.R;
import com.example.roombox.ui.AddHotelActivity;
import com.example.roombox.ui.ChatActivity;
import com.example.roombox.ui.CommentActivity;
import com.example.roombox.ui.HotelManagerActivity;
import com.example.roombox.ui.LoginActivity;
import com.example.roombox.ui.OrderActivity;
import com.example.roombox.utils.ACache;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
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

  private void initView() {
    //0 租客 1 房东 2管理员
    final String type = ACache.get(getActivity()).getAsString("type");
    //管理员
    String[] datas = new String[]{"房屋管理", "举报管理", "登出"};
    if ("1".equals(type)){
      datas = new String[]{"历史订单", "房屋管理", "客服", "登出", "评论"};
    }
    if ("0".equals(type)){
      return;
    }
    ArrayList meumList = new ArrayList();
    for (int i = 0; i < datas.length; i++) {
      HashMap<String, Object> map = new HashMap<String, Object>();
      map.put("ItemText", "" + datas[i]);
      meumList.add(map);
    }
    SimpleAdapter saItem = new SimpleAdapter(getActivity(), meumList, R.layout.item_mine_layout,
      new String[]{"ItemText"},
      new int[]{R.id.ItemText});

    gridView.setAdapter(saItem);
    gridView.setOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          if ("2".equals(type)){
              startPage(position);
          }else {
              startPage1(position);
          }

      }
    });

  }

  //跳转对应的页面(管理员)
  private void startPage(int index) {
    switch (index) {
      case 0:
        startActivity(new Intent(getActivity(), AddHotelActivity.class));
        break;
      case 1:
        startActivity(new Intent(getActivity(), PersonDataAct.class));
        break;
      case 2:
        ACache.get(getActivity()).put("account", "");
        ACache.get(getActivity()).put("type", "");
        startActivity(new Intent(getActivity(), LoginActivity.class));
        break;
      default:
        break;
    }


  }
  //跳转对应的页面(房东)
  private void startPage1(int index) {
    switch (index) {
      case 0:
        startActivity(new Intent(getActivity(), OrderActivity.class));
        break;
      case 1:
        startActivity(new Intent(getActivity(), HotelManagerActivity.class));
        break;
      case 2:
        startActivity(new Intent(getActivity(), ChatActivity.class));
        break;
      case 3://退出
        ACache.get(getActivity()).put("account", "");
        ACache.get(getActivity()).put("type", "");
        startActivity(new Intent(getActivity(), LoginActivity.class));
        break;
      case 4:
        startActivity(new Intent(getActivity(), CommentActivity.class));
        break;
      default:
        break;
    }


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
