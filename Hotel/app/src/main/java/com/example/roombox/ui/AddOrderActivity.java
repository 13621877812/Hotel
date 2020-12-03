package com.example.roombox.ui;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import com.example.roombox.R;
import com.example.roombox.bean.HotelBean;
import com.example.roombox.utils.ACache;
import com.example.roombox.utils.Contans;
import com.example.roombox.utils.HttpUtil;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddOrderActivity extends AppCompatActivity {

  Calendar calendar= Calendar.getInstance(Locale.CHINA);
  Date startTime;
  Date endTime;
  HotelBean hotelBean;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_order);
    ButterKnife.bind(this);
    hotelBean = (HotelBean) getIntent().getExtras().getSerializable("hotel");

  }

  @OnClick({R.id.startTime, R.id.endTime,R.id.orderBtn})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.startTime:
        showDatePickerDialog(4,(TextView) view,calendar);
        break;
      case R.id.endTime:
        showDatePickerDialog(4,(TextView)view,calendar);
        break;
      case R.id.orderBtn:
        addOrder();
        break;
      default:
        break;
    }
  }
  public void showDatePickerDialog(int themeResId, final TextView tv, Calendar calendar) {
    new DatePickerDialog(AddOrderActivity.this, themeResId, new DatePickerDialog.OnDateSetListener() {
      @Override
      public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Date date = new Date();
        date.setYear(year);
        date.setMonth(monthOfYear);
        date.setDate(dayOfMonth);
        if (view.getId() == R.id.startTime){
          startTime = date;
        }else {
          endTime = date;
        }
        tv.setText(year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");
      }
    }
            , calendar.get(Calendar.YEAR)
            , calendar.get(Calendar.MONTH)
            , calendar.get(Calendar.DAY_OF_MONTH)).show();
  }
  private void addOrder(){
    String account = ACache.get(this).getAsString("account");
    String url = "/order/add";
    HashMap params = new HashMap();
    params.put("startTime",startTime);
    params.put("endTime",endTime);
    params.put("account",account);
    params.put("hotel_id",hotelBean.getHotel_id());
    params.put("price",hotelBean.getPrice());
    HttpUtil.httpPost(url, params, this, new HttpUtil.HttpCallBack() {
      @Override
      public void success(String data) {
        Contans.makeToast("預定成功！",AddOrderActivity.this);
      }
    });
  }


}
