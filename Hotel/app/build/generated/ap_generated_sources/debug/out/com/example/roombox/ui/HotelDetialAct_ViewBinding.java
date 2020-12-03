// Generated code from Butter Knife. Do not modify!
package com.example.roombox.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
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

  private View view2131296372;

  private View view2131296366;

  private View view2131296529;

  private View view2131296362;

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
    view = Utils.findRequiredView(source, R.id.criticalBtn, "method 'onViewClicked'");
    view2131296372 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.commentBtn, "method 'onViewClicked'");
    view2131296366 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.orderBtn, "method 'onViewClicked'");
    view2131296529 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.collectionBtn, "method 'onViewClicked'");
    view2131296362 = view;
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

    view2131296372.setOnClickListener(null);
    view2131296372 = null;
    view2131296366.setOnClickListener(null);
    view2131296366 = null;
    view2131296529.setOnClickListener(null);
    view2131296529 = null;
    view2131296362.setOnClickListener(null);
    view2131296362 = null;
  }
}
