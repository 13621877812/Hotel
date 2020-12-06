// Generated code from Butter Knife. Do not modify!
package com.example.roombox.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.roombox.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddHotelActivity_ViewBinding implements Unbinder {
  private AddHotelActivity target;

  private View view2131296604;

  private View view2131296602;

  @UiThread
  public AddHotelActivity_ViewBinding(AddHotelActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddHotelActivity_ViewBinding(final AddHotelActivity target, View source) {
    this.target = target;

    View view;
    target.t1 = Utils.findRequiredViewAsType(source, R.id.t1, "field 't1'", EditText.class);
    target.housename = Utils.findRequiredViewAsType(source, R.id.housename, "field 'housename'", EditText.class);
    target.perprice = Utils.findRequiredViewAsType(source, R.id.perprice, "field 'perprice'", EditText.class);
    target.zone = Utils.findRequiredViewAsType(source, R.id.zone, "field 'zone'", EditText.class);
    target.address = Utils.findRequiredViewAsType(source, R.id.address, "field 'address'", EditText.class);
    target.intro = Utils.findRequiredViewAsType(source, R.id.intro, "field 'intro'", EditText.class);
    target.type = Utils.findRequiredViewAsType(source, R.id.type, "field 'type'", Spinner.class);
    target.whole = Utils.findRequiredViewAsType(source, R.id.whole, "field 'whole'", RadioButton.class);
    target.share = Utils.findRequiredViewAsType(source, R.id.share, "field 'share'", RadioButton.class);
    target.spacetype = Utils.findRequiredViewAsType(source, R.id.spacetype, "field 'spacetype'", RadioGroup.class);
    target.roomnum = Utils.findRequiredViewAsType(source, R.id.roomnum, "field 'roomnum'", EditText.class);
    target.mannum = Utils.findRequiredViewAsType(source, R.id.mannum, "field 'mannum'", EditText.class);
    target.bedroom = Utils.findRequiredViewAsType(source, R.id.bedroom, "field 'bedroom'", EditText.class);
    target.listView = Utils.findRequiredViewAsType(source, R.id.listView, "field 'listView'", RecyclerView.class);
    target.bathnum = Utils.findRequiredViewAsType(source, R.id.bathnum, "field 'bathnum'", EditText.class);
    target.c1 = Utils.findRequiredViewAsType(source, R.id.c1, "field 'c1'", CheckBox.class);
    target.c2 = Utils.findRequiredViewAsType(source, R.id.c2, "field 'c2'", CheckBox.class);
    target.c3 = Utils.findRequiredViewAsType(source, R.id.c3, "field 'c3'", CheckBox.class);
    target.c4 = Utils.findRequiredViewAsType(source, R.id.c4, "field 'c4'", CheckBox.class);
    target.c5 = Utils.findRequiredViewAsType(source, R.id.c5, "field 'c5'", CheckBox.class);
    target.c6 = Utils.findRequiredViewAsType(source, R.id.c6, "field 'c6'", CheckBox.class);
    target.c7 = Utils.findRequiredViewAsType(source, R.id.c7, "field 'c7'", CheckBox.class);
    target.c8 = Utils.findRequiredViewAsType(source, R.id.c8, "field 'c8'", CheckBox.class);
    target.c9 = Utils.findRequiredViewAsType(source, R.id.c9, "field 'c9'", CheckBox.class);
    target.c10 = Utils.findRequiredViewAsType(source, R.id.c10, "field 'c10'", CheckBox.class);
    target.c11 = Utils.findRequiredViewAsType(source, R.id.c11, "field 'c11'", CheckBox.class);
    target.c12 = Utils.findRequiredViewAsType(source, R.id.c12, "field 'c12'", CheckBox.class);
    target.c13 = Utils.findRequiredViewAsType(source, R.id.c13, "field 'c13'", CheckBox.class);
    target.c14 = Utils.findRequiredViewAsType(source, R.id.c14, "field 'c14'", CheckBox.class);
    target.c15 = Utils.findRequiredViewAsType(source, R.id.c15, "field 'c15'", CheckBox.class);
    target.c16 = Utils.findRequiredViewAsType(source, R.id.c16, "field 'c16'", CheckBox.class);
    target.c17 = Utils.findRequiredViewAsType(source, R.id.c17, "field 'c17'", CheckBox.class);
    target.c18 = Utils.findRequiredViewAsType(source, R.id.c18, "field 'c18'", CheckBox.class);
    target.c19 = Utils.findRequiredViewAsType(source, R.id.c19, "field 'c19'", CheckBox.class);
    target.c20 = Utils.findRequiredViewAsType(source, R.id.c20, "field 'c20'", CheckBox.class);
    target.c21 = Utils.findRequiredViewAsType(source, R.id.c21, "field 'c21'", CheckBox.class);
    target.c22 = Utils.findRequiredViewAsType(source, R.id.c22, "field 'c22'", CheckBox.class);
    target.c23 = Utils.findRequiredViewAsType(source, R.id.c23, "field 'c23'", CheckBox.class);
    view = Utils.findRequiredView(source, R.id.roomimg_up, "field 'roomimgUp' and method 'onClick'");
    target.roomimgUp = Utils.castView(view, R.id.roomimg_up, "field 'roomimgUp'", ImageButton.class);
    view2131296604 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.room_sub, "field 'roomSub' and method 'onClick'");
    target.roomSub = Utils.castView(view, R.id.room_sub, "field 'roomSub'", Button.class);
    view2131296602 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    AddHotelActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.t1 = null;
    target.housename = null;
    target.perprice = null;
    target.zone = null;
    target.address = null;
    target.intro = null;
    target.type = null;
    target.whole = null;
    target.share = null;
    target.spacetype = null;
    target.roomnum = null;
    target.mannum = null;
    target.bedroom = null;
    target.listView = null;
    target.bathnum = null;
    target.c1 = null;
    target.c2 = null;
    target.c3 = null;
    target.c4 = null;
    target.c5 = null;
    target.c6 = null;
    target.c7 = null;
    target.c8 = null;
    target.c9 = null;
    target.c10 = null;
    target.c11 = null;
    target.c12 = null;
    target.c13 = null;
    target.c14 = null;
    target.c15 = null;
    target.c16 = null;
    target.c17 = null;
    target.c18 = null;
    target.c19 = null;
    target.c20 = null;
    target.c21 = null;
    target.c22 = null;
    target.c23 = null;
    target.roomimgUp = null;
    target.roomSub = null;

    view2131296604.setOnClickListener(null);
    view2131296604 = null;
    view2131296602.setOnClickListener(null);
    view2131296602 = null;
  }
}
