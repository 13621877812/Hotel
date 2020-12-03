// Generated code from Butter Knife. Do not modify!
package com.example.roombox.adapters;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bigkoo.snappingstepper.SnappingStepper;
import com.example.roombox.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RoomAdapter$ViewHolder_ViewBinding implements Unbinder {
  private RoomAdapter.ViewHolder target;

  @UiThread
  public RoomAdapter$ViewHolder_ViewBinding(RoomAdapter.ViewHolder target, View source) {
    this.target = target;

    target.roomName = Utils.findRequiredViewAsType(source, R.id.roomName, "field 'roomName'", TextView.class);
    target.bednum = Utils.findRequiredViewAsType(source, R.id.bednum, "field 'bednum'", TextView.class);
    target.stepper1 = Utils.findRequiredViewAsType(source, R.id.stepper1, "field 'stepper1'", SnappingStepper.class);
    target.stepper2 = Utils.findRequiredViewAsType(source, R.id.stepper2, "field 'stepper2'", SnappingStepper.class);
    target.stepper3 = Utils.findRequiredViewAsType(source, R.id.stepper3, "field 'stepper3'", SnappingStepper.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RoomAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.roomName = null;
    target.bednum = null;
    target.stepper1 = null;
    target.stepper2 = null;
    target.stepper3 = null;
  }
}
