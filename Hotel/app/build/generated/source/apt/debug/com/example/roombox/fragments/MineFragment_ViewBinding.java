// Generated code from Butter Knife. Do not modify!
package com.example.roombox.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.GridView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.roombox.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MineFragment_ViewBinding implements Unbinder {
  private MineFragment target;

  @UiThread
  public MineFragment_ViewBinding(MineFragment target, View source) {
    this.target = target;

    target.gridView = Utils.findRequiredViewAsType(source, R.id.gridView, "field 'gridView'", GridView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MineFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.gridView = null;
  }
}
