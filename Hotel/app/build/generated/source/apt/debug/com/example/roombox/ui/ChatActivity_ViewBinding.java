// Generated code from Butter Knife. Do not modify!
package com.example.roombox.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.roombox.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChatActivity_ViewBinding implements Unbinder {
  private ChatActivity target;

  private View view2131296574;

  @UiThread
  public ChatActivity_ViewBinding(ChatActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChatActivity_ViewBinding(final ChatActivity target, View source) {
    this.target = target;

    View view;
    target.content = Utils.findRequiredViewAsType(source, R.id.content, "field 'content'", EditText.class);
    target.chatList = Utils.findRequiredViewAsType(source, R.id.chatList, "field 'chatList'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.sendBtn, "method 'onViewClicked'");
    view2131296574 = view;
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
    ChatActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.content = null;
    target.chatList = null;

    view2131296574.setOnClickListener(null);
    view2131296574 = null;
  }
}