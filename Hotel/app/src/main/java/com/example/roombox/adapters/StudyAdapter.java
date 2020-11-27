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
import com.example.roombox.bean.WordBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StudyAdapter extends RecyclerView.Adapter<StudyAdapter.ViewHolder> {


  public interface ItemClickListener {
    void itemClick(Integer index);
  }

  private Context mcontext;
  private ItemClickListener listener;
  private List<WordBean> datas = new ArrayList<>();

  public StudyAdapter(Context mcontext) {
    this.mcontext = mcontext;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


    View view = LayoutInflater.from(this.mcontext).inflate(R.layout.item_layout, viewGroup, false);
    ViewHolder itemHoldView = new ViewHolder(view);
    return itemHoldView;
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder itemHoldView, int i) {

    WordBean bean = this.datas.get(i);
    final ItemClickListener listener1 = this.listener;
    final int index = i;
    itemHoldView.text.setText(bean.getName());
    itemHoldView.textZH.setText(bean.getNameZH());
    Glide.with(mcontext).load(bean.getImage()).into(itemHoldView.topImage);
    itemHoldView.parentView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (listener1 != null) {
          listener1.itemClick(index);
        }

      }
    });
  }

  @Override
  public int getItemCount() {
    return this.datas.size();
  }


  public void setDatas(List<WordBean> datas) {
    this.datas = datas;
  }

  public void setListener(ItemClickListener listener) {
    this.listener = listener;
  }

  static class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.textZH)
    TextView textZH;
    @BindView(R.id.topImage)
    ImageView topImage;
    @BindView(R.id.text)
    TextView text;
    private View parentView;

    ViewHolder(View view) {
      super(view);
      this.parentView = view;
      ButterKnife.bind(this, view);
    }
  }
}
