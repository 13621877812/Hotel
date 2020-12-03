// Generated code from Butter Knife. Do not modify!
package com.example.roombox;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.roombox.views.MenuBottom;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(MainActivity target, View source) {
    this.target = target;

    target.layout = Utils.findRequiredViewAsType(source, R.id.layout, "field 'layout'", RelativeLayout.class);
    target.menuBottom = Utils.findRequiredViewAsType(source, R.id.menu_bottom, "field 'menuBottom'", MenuBottom.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.layout = null;
    target.menuBottom = null;
  }
}
