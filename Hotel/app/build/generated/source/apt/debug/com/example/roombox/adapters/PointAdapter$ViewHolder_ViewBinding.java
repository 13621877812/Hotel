// Generated code from Butter Knife. Do not modify!
package com.example.roombox.adapters;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.roombox.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PointAdapter$ViewHolder_ViewBinding implements Unbinder {
  private PointAdapter.ViewHolder target;

  @UiThread
  public PointAdapter$ViewHolder_ViewBinding(PointAdapter.ViewHolder target, View source) {
    this.target = target;

    target.topImage = Utils.findRequiredViewAsType(source, R.id.topImage, "field 'topImage'", ImageView.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.desc = Utils.findRequiredViewAsType(source, R.id.desc, "field 'desc'", TextView.class);
    target.price = Utils.findRequiredViewAsType(source, R.id.price, "field 'price'", TextView.class);
    target.eval = Utils.findRequiredViewAsType(source, R.id.eval, "field 'eval'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PointAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.topImage = null;
    target.name = null;
    target.desc = null;
    target.price = null;
    target.eval = null;
  }
}
