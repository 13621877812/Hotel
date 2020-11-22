package com.example.roombox.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.example.roombox.R;

public class CouchView extends LinearLayout {
  private Context mContext;
  private View mView;
  public CouchView(Context context) {
    super(context);
  }

  public CouchView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context,attrs);
  }
  //自定义item
  private void init(Context context, AttributeSet attrs) {
    mContext = context;
    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    mView = inflater.inflate(R.layout.couch_layout, this, true);
  }

}
