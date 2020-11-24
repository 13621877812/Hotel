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

public class StudyAdapter$ViewHolder_ViewBinding implements Unbinder {
  private StudyAdapter.ViewHolder target;

  @UiThread
  public StudyAdapter$ViewHolder_ViewBinding(StudyAdapter.ViewHolder target, View source) {
    this.target = target;

    target.textZH = Utils.findRequiredViewAsType(source, R.id.textZH, "field 'textZH'", TextView.class);
    target.topImage = Utils.findRequiredViewAsType(source, R.id.topImage, "field 'topImage'", ImageView.class);
    target.text = Utils.findRequiredViewAsType(source, R.id.text, "field 'text'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    StudyAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.textZH = null;
    target.topImage = null;
    target.text = null;
  }
}
