package com.example.roombox.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.snappingstepper.SnappingStepper;
import com.bigkoo.snappingstepper.listener.SnappingStepperValueChangeListener;
import com.example.roombox.R;
import com.example.roombox.bean.HotelBean;
import com.example.roombox.bean.RoomBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {


  private Context mContext;
  private List<RoomBean> datas = new ArrayList<>();


  public RoomAdapter(Context mContext) {
    this.mContext = mContext;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(mContext).inflate(R.layout.room_item, viewGroup, false);
    ViewHolder holder = new ViewHolder(view);
    return holder;
  }

  @Override
  public void onBindViewHolder(ViewHolder viewHolder, int i) {
    final RoomBean bean = datas.get(i);
    Integer total = bean.getBed1num()+bean.getBed2num() + bean.getBed3num();
    viewHolder.roomName.setText("卧室" + (i+1));
    viewHolder.bednum.setText(total+"张床");
    viewHolder.setBean(bean);
  }

  @Override
  public int getItemCount() {
    return datas.size();
  }


  public void setDatas(List<RoomBean> datas) {
    this.datas = datas;
  }



  static class ViewHolder extends RecyclerView.ViewHolder implements SnappingStepperValueChangeListener {
    @BindView(R.id.roomName)
    TextView roomName;
    @BindView(R.id.bednum)
    TextView bednum;
    @BindView(R.id.stepper1)
    SnappingStepper stepper1;
    @BindView(R.id.stepper2)
    SnappingStepper stepper2;
    @BindView(R.id.stepper3)
    SnappingStepper stepper3;
    private View parentView;
    private RoomBean bean;
    ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
      parentView = view;
      stepper1.setOnValueChangeListener(this);
      stepper2.setOnValueChangeListener(this);
      stepper3.setOnValueChangeListener(this);
    }

    public void setBean(RoomBean bean) {
      this.bean = bean;

    }

    @Override
    public void onValueChange(View view, int value) {
      switch (view.getId()){
        case R.id.stepper1:
          bean.setBed1num(value);
          break;
        case R.id.stepper2:
          bean.setBed2num(value);
          break;
        case R.id.stepper3:
          bean.setBed3num(value);
          break;
        default:
          break;
      }
    }
  }
}
