package com.example.roombox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;

import com.example.roombox.adapters.PointAdapter;
import com.example.roombox.fragments.ChatFragment;
import com.example.roombox.fragments.ColloectionFragment;
import com.example.roombox.fragments.HomeFragment;
import com.example.roombox.fragments.MineFragment;
import com.example.roombox.fragments.SearchFragment;
import com.example.roombox.views.MenuBottom;

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

  private Bundle savedInstanceState1;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    Log.i("TAG123", "initMapView12: "+ savedInstanceState);
    super.onCreate(savedInstanceState);
    savedInstanceState1 = savedInstanceState;
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
