package com.example.roombox.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

  //這個activity就是MainActivity
  public Activity mActivity;

  // Fragment被創建
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mActivity = getActivity();// 獲取所在的activity對象
  }

  // 初始化Fragment布局
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = initView();
    return view;
  }

  // activity創建結束
  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    initData();
  }

  /**
   * 初始化布局, 子類必須實現
   */
  public abstract View initView();

  /**
   * 初始化數據, 子類可以不實現
   */
  public void initData() {

  }
}
