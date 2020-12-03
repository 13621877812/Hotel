// Generated code from Butter Knife. Do not modify!
package com.example.roombox.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.roombox.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ColloectionFragment_ViewBinding implements Unbinder {
  private ColloectionFragment target;

  private View view2131296389;

  @UiThread
  public ColloectionFragment_ViewBinding(final ColloectionFragment target, View source) {
    this.target = target;

    View view;
    target.listView = Utils.findRequiredViewAsType(source, R.id.listView, "field 'listView'", RecyclerView.class);
    target.totalText = Utils.findRequiredViewAsType(source, R.id.totalText, "field 'totalText'", TextView.class);
    view = Utils.findRequiredView(source, R.id.editBtn, "method 'onViewClicked'");
    view2131296389 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ColloectionFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.listView = null;
    target.totalText = null;

    view2131296389.setOnClickListener(null);
    view2131296389 = null;
  }
}
