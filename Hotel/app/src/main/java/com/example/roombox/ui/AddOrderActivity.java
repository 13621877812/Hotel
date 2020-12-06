package com.example.roombox.ui;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
  Calendar startTime;
  Calendar endTime;
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
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.YEAR,2020);
        calendar1.set(Calendar.MONTH,monthOfYear);
        calendar1.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        if (tv.getId() == R.id.startTime){
          startTime = calendar1;
        }else {
          endTime = calendar1;
        }
        Log.i("tine", "addOrder: "+ calendar1.getTimeInMillis());
        Log.i("tine", "addOrder: "+ (new  Date()).getTime());
        tv.setText(year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");
      }
    }
            , calendar.get(Calendar.YEAR)
            , calendar.get(Calendar.MONTH)
            , calendar.get(Calendar.DAY_OF_MONTH)).show();
  }
  private void addOrder(){
    String account = ACache.get(this).getAsString("account");
    Double hotel_id = Double.parseDouble(hotelBean.getHotel_id()) ;
    String url = "/order/add";
    HashMap params = new HashMap();
    params.put("startTime1",startTime.getTimeInMillis());

    params.put("endTime1",endTime.getTimeInMillis());
    params.put("account",account);
    params.put("hotel_id",hotel_id.intValue() + "");
    params.put("price",hotelBean.getPrice());

    Log.i("tTAG", "addOrder: " + params.toString());


    HttpUtil.httpPost(url, params, AddOrderActivity.this, new HttpUtil.HttpCallBack() {
      @Override
      public void success(String data) {
        Contans.makeToast("預定成功！",AddOrderActivity.this);
        finish();
      }
    });
  }


}
