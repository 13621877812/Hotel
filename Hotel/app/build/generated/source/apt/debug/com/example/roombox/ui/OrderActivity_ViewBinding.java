// Generated code from Butter Knife. Do not modify!
package com.example.roombox.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.roombox.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrderActivity_ViewBinding implements Unbinder {
  private OrderActivity target;

  @UiThread
  public OrderActivity_ViewBinding(OrderActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OrderActivity_ViewBinding(OrderActivity target, View source) {
    this.target = target;

    target.listView = Utils.findRequiredViewAsType(source, R.id.listView, "field 'listView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OrderActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.listView = null;
  }
}
