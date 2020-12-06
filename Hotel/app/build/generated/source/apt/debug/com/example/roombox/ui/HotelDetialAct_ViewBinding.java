// Generated code from Butter Knife. Do not modify!
package com.example.roombox.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.roombox.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HotelDetialAct_ViewBinding implements Unbinder {
  private HotelDetialAct target;

  private View view2131296364;

  private View view2131296533;

  private View view2131296375;

  private View view2131296368;

  private View view2131296298;

  private View view2131296356;

  @UiThread
  public HotelDetialAct_ViewBinding(HotelDetialAct target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HotelDetialAct_ViewBinding(final HotelDetialAct target, View source) {
    this.target = target;

    View view;
    target.info = Utils.findRequiredViewAsType(source, R.id.info, "field 'info'", TextView.class);
    target.type = Utils.findRequiredViewAsType(source, R.id.type, "field 'type'", TextView.class);
    target.ownerText = Utils.findRequiredViewAsType(source, R.id.ownerText, "field 'ownerText'", TextView.class);
    target.hotelInfo = Utils.findRequiredViewAsType(source, R.id.hotelInfo, "field 'hotelInfo'", TextView.class);
    target.hotelImage = Utils.findRequiredViewAsType(source, R.id.hotelImage, "field 'hotelImage'", ImageView.class);
    target.location = Utils.findRequiredViewAsType(source, R.id.location, "field 'location'", TextView.class);
    target.priceText = Utils.findRequiredViewAsType(source, R.id.priceText, "field 'priceText'", TextView.class);
    target.bedListView = Utils.findRequiredViewAsType(source, R.id.bedListView, "field 'bedListView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.collectionBtn, "field 'collectionBtn' and method 'onViewClicked'");
    target.collectionBtn = Utils.castView(view, R.id.collectionBtn, "field 'collectionBtn'", TextView.class);
    view2131296364 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.orderBtn, "field 'orderBtn' and method 'onViewClicked'");
    target.orderBtn = Utils.castView(view, R.id.orderBtn, "field 'orderBtn'", TextView.class);
    view2131296533 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.criticalBtn, "method 'onViewClicked'");
    view2131296375 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.commentBtn, "method 'onViewClicked'");
    view2131296368 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.back, "method 'onViewClicked'");
    view2131296298 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.chatBtn, "method 'onViewClicked'");
    view2131296356 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    HotelDetialAct target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.info = null;
    target.type = null;
    target.ownerText = null;
    target.hotelInfo = null;
    target.hotelImage = null;
    target.location = null;
    target.priceText = null;
    target.bedListView = null;
    target.collectionBtn = null;
    target.orderBtn = null;

    view2131296364.setOnClickListener(null);
    view2131296364 = null;
    view2131296533.setOnClickListener(null);
    view2131296533 = null;
    view2131296375.setOnClickListener(null);
    view2131296375 = null;
    view2131296368.setOnClickListener(null);
    view2131296368 = null;
    view2131296298.setOnClickListener(null);
    view2131296298 = null;
    view2131296356.setOnClickListener(null);
    view2131296356 = null;
  }
}
