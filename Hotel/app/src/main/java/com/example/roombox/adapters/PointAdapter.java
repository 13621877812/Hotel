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
import com.example.roombox.bean.CollectionBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PointAdapter extends RecyclerView.Adapter<PointAdapter.ViewHolder> {

  public interface ItemClickListener {
    void itemClick(CollectionBean bean);
  }

  private Context mContext;
  private List<CollectionBean> datas;
  private ItemClickListener listener;

  public PointAdapter(Context mContext) {
    this.mContext = mContext;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(mContext).inflate(R.layout.point_item, viewGroup, false);
    ViewHolder holder = new ViewHolder(view);
    return holder;
  }

  @Override
  public void onBindViewHolder(ViewHolder viewHolder, int i) {
    final CollectionBean bean = datas.get(i);
    viewHolder.name.setText(bean.getName());
    Glide.with(mContext).load(bean.getImage()).into(viewHolder.topImage);
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

  public void setDatas(List<CollectionBean> datas) {
    this.datas = datas;
  }

  static class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.topImage)
    ImageView topImage;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.desc)
    TextView desc;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.eval)
    TextView eval;
    private View parentView;

    ViewHolder(View view) {
      super(view);
      parentView = view;
      ButterKnife.bind(this, view);
    }
  }
}
