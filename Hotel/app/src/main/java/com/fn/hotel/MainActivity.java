package com.fn.hotel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.fn.hotel.adapters.PointAdapter;
import com.fn.hotel.fragments.ChatFragment;
import com.fn.hotel.fragments.ColloectionFragment;
import com.fn.hotel.fragments.HomeFragment;
import com.fn.hotel.fragments.MineFragment;
import com.fn.hotel.fragments.SearchFragment;
import com.fn.hotel.views.MenuBottom;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

  private List<Fragment> fragments;
  @BindView(R.id.layout)
  RelativeLayout layout;
  @BindView(R.id.menu_bottom)
  MenuBottom menuBottom;

  private Bundle savedInstanceState;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    savedInstanceState = savedInstanceState;
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    menuBottom.setListener(new MenuBottom.BottomItemClickListener() {
      @Override
      public void itemClick(int tag) {
        showFragment(tag);
      }
    });
    addFragments();
  }
  //add Fragment
  private void addFragments() {
    fragments = new ArrayList<Fragment>();
    HomeFragment homeFragment = new HomeFragment();
    homeFragment.setSavedInstanceState(savedInstanceState);
    MineFragment mineFragment = new MineFragment();
    SearchFragment searchFragment = new SearchFragment();
    ChatFragment chatFragment = new ChatFragment();
    ColloectionFragment colloectionFragment = new ColloectionFragment();
    fragments.add(homeFragment);
    fragments.add(mineFragment);
    fragments.add(searchFragment);
    fragments.add(chatFragment);
    fragments.add(colloectionFragment);

    FragmentManager manager = getSupportFragmentManager();
    FragmentTransaction ft = manager.beginTransaction();
    ft.add(R.id.layout,homeFragment);
    ft.add(R.id.layout,mineFragment);
    ft.add(R.id.layout,searchFragment);
    ft.add(R.id.layout,chatFragment);
    ft.add(R.id.layout,colloectionFragment);
    ft.commit();
    showFragment(0);

  }

  private void showFragment(int index) {
    FragmentManager manager = getSupportFragmentManager();
    FragmentTransaction ft = manager.beginTransaction();
    for (int i = 0; i < fragments.size(); i++) {
      ft.hide(fragments.get(i));
    }
    ft.show(fragments.get(index));
    ft.commit();
  }
}
