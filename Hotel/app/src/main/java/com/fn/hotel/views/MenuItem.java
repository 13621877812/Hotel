package com.fn.hotel.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fn.hotel.R;

public class MenuItem extends LinearLayout {
  private Context mContext;
  private View mView;
  private ImageView imageView;
  private TextView titleText;
  private boolean isSelect;


  private String title;//标题
  private int iconImgUri;//图片
  private int selectedIconImgUri;//选中的图片

  public boolean isSelect() {
    return isSelect;
  }
  @SuppressLint("ResourceAsColor")
  public void setSelect(boolean select) {
    isSelect = select;
    if (isSelect){
      imageView.setImageResource(selectedIconImgUri);
      titleText.setTextColor(R.color.itemS);
    }else {
      imageView.setImageResource(iconImgUri);
      titleText.setTextColor(R.color.item1);
    }
  }

  public void setIconImgUri(int iconImgUri) {
    this.iconImgUri = iconImgUri;
    imageView.setImageResource(iconImgUri);
  }

  public void setSelectedIconImgUri(int selectedIconImgUri) {
    this.selectedIconImgUri = selectedIconImgUri;
  }

  public void setTitle(String title) {
    this.title = title;
    titleText.setText(title);
  }
  public MenuItem(Context context) {
    super(context);
  }

  public MenuItem(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context,attrs);
  }
  //自定义item
  private void init(Context context, AttributeSet attrs) {

    mContext = context;
    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    mView = inflater.inflate(R.layout.item_menu, this, true);
    imageView = (ImageView) mView.findViewById(R.id.image);
    titleText = (TextView) mView.findViewById(R.id.title);
    TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.MenuItemLayout);
    String title = a.getString(R.styleable.MenuItemLayout_title_text);
    int image = a.getResourceId(R.styleable.MenuItemLayout_iconImgUri,0);
    int imageS = a.getResourceId(R.styleable.MenuItemLayout_selectedIconImgUri,0);
    boolean isSelect = a.getBoolean(R.styleable.MenuItemLayout_isSelect,false);
    setTitle(title);
    setIconImgUri(image);
    setSelectedIconImgUri(imageS);
    setSelect(isSelect);

  }


}
