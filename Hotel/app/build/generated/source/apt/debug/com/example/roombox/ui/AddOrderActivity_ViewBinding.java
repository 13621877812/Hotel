// Generated code from Butter Knife. Do not modify!
package com.example.roombox.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.roombox.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddOrderActivity_ViewBinding implements Unbinder {
  private AddOrderActivity target;

  private View view2131296651;

  private View view2131296394;

  private View view2131296529;

  @UiThread
  public AddOrderActivity_ViewBinding(AddOrderActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddOrderActivity_ViewBinding(final AddOrderActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.startTime, "method 'onViewClicked'");
    view2131296651 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.endTime, "method 'onViewClicked'");
    view2131296394 = view;
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
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131296651.setOnClickListener(null);
    view2131296651 = null;
    view2131296394.setOnClickListener(null);
    view2131296394 = null;
    view2131296529.setOnClickListener(null);
    view2131296529 = null;
  }
}
