package com.example.roombox.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.roombox.R;

import java.util.ArrayList;

import butterknife.OnClick;

public class MenuBottom extends LinearLayout implements View.OnClickListener {
  private Context mContext;
  private View mView;
  private ArrayList<MenuItem> views = new ArrayList<>();
  private BottomItemClickListener listener;


  public void setListener(BottomItemClickListener listener) {
    this.listener = listener;
  }

  public MenuBottom(Context context) {
    super(context);
  }
  public MenuBottom(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  //自定义item
  private void init(Context context, AttributeSet attrs) {
    mContext = context;
    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    mView = inflater.inflate(R.layout.view_bottom, this, true);
    MenuItem home = (MenuItem) mView.findViewById(R.id.navigation_home);
    MenuItem mine = (MenuItem) mView.findViewById(R.id.navigation_mine);
    MenuItem search = (MenuItem) mView.findViewById(R.id.navigation_search);
    MenuItem pay = (MenuItem) mView.findViewById(R.id.navigation_pay);
    MenuItem comment = (MenuItem) mView.findViewById(R.id.navigation_comment);
    views.add(home);
    views.add(mine);
    views.add(search);
    views.add(pay);
    views.add(comment);
    home.setOnClickListener(this);
    mine.setOnClickListener(this);
    search.setOnClickListener(this);
    pay.setOnClickListener(this);
    comment.setOnClickListener(this);
  }


  private void itemClick(int tag){
    Log.i("TAG1", "itemClick: ");
    for (int i = 0; i < views.size(); i++) {
      MenuItem menuItem = views.get(i);
      if (i == tag){//选中
          menuItem.setCurrentTab(true);
      }else {
        if (menuItem.isCurrentTab()) {
          menuItem.setCurrentTab(false);
        }
      }
      listener.itemClick(tag);
    }

  }

  @Override
  public void onClick(View v) {
    Log.i("TAG2", "itemClick: ");
    boolean isSelect = ((MenuItem)v).isSelected();
    if (isSelect){
      return;
    }
    switch (v.getId()) {
      case R.id.navigation_home:
        itemClick(0);
        break;
      case R.id.navigation_mine:
        itemClick(1);
        break;
      case R.id.navigation_search:
        itemClick(2);
        break;
      case R.id.navigation_pay:
        itemClick(3);
        break;
      case R.id.navigation_comment:
        itemClick(4);
        break;
      default:
        break;
    }
  }

  public interface BottomItemClickListener{
    public void itemClick(int tag);
  }
}
