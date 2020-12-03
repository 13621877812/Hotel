package com.example.roombox.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;



public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

  List<Fragment> list;//需要顯示的fragment在構造器中傳入
  List<String> titleList;

  public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list, List<String> titleList) {

    super(fm);
    this.list = list;
    this.titleList = titleList;

  }

  //返回顯示的Fragment總數
  @Override

  public int getCount() {

    return list.size();

  }

  //返回要顯示的Fragment的某個實例
  @Override

  public Fragment getItem(int arg0) {

    return list.get(arg0);

  }
//
////返回一個自定義tab視圖（用於自定義Tablayout標簽，不自定義可忽略）
//    public View getTabView(int position) {
//
//
//        int layout_ids = R.layout.tab_view_monthly;
//
//        if (position == 1) {
//
//            layout_ids = R.layout.tab_view_daily;
//
//        }
//
//        View v = LayoutInflater.from(MainActivity.this).inflate(layout_ids, null);
//
//        return v;
//
//    }

  //返回每個Tab的標題，當要自定義Tab的時候不應該重寫該方法
  @Override

  public CharSequence getPageTitle(int position) {

    return titleList.get(position);
  }

}
