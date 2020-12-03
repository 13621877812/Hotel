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

public class ChatAdapter$ViewHolder_ViewBinding implements Unbinder {
  private ChatAdapter.ViewHolder target;

  @UiThread
  public ChatAdapter$ViewHolder_ViewBinding(ChatAdapter.ViewHolder target, View source) {
    this.target = target;

    target.userImage = Utils.findRequiredViewAsType(source, R.id.userImage, "field 'userImage'", ImageView.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.time = Utils.findRequiredViewAsType(source, R.id.time, "field 'time'", TextView.class);
    target.content = Utils.findRequiredViewAsType(source, R.id.content, "field 'content'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ChatAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.userImage = null;
    target.name = null;
    target.time = null;
    target.content = null;
  }
}
