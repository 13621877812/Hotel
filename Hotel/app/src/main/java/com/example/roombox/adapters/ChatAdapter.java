package com.example.roombox.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.roombox.R;
import com.example.roombox.bean.ChatBean;
import com.example.roombox.bean.CollectionBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

  public interface ItemClickListener {
    void itemClick(ChatBean bean);
  }

  private Context mContext;
  private List<ChatBean> datas = new ArrayList<>();
  private ItemClickListener listener;

  public ChatAdapter(Context mContext) {
    this.mContext = mContext;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item, viewGroup, false);
    ViewHolder holder = new ViewHolder(view);
    return holder;
  }

  @Override
  public void onBindViewHolder(ViewHolder viewHolder, int i) {
    viewHolder.userImage.setImageResource(R.drawable.logo);
    final ChatBean bean = datas.get(i);
    viewHolder.name.setText(bean.getSendId());
    viewHolder.time.setText(bean.getCreateTime());
    viewHolder.content.setText(bean.getContent());
    viewHolder.parentView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        listener.itemClick(bean);
      }
    });
  }

  @Override
  public int getItemCount() {
    return datas.size();
  }

  public void setListener(ItemClickListener listener) {
    this.listener = listener;
  }

  public void setDatas(List<ChatBean> datas) {
    this.datas = datas;
  }

  static class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.userImage)
    ImageView userImage;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.content)
    TextView content;
    private View parentView;

    ViewHolder(View view) {
      super(view);
      parentView = view;
      ButterKnife.bind(this, view);
    }
  }
}

