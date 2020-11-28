package com.example.roombox.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.roombox.R;
import com.example.roombox.utils.Contans;
import com.example.roombox.utils.HttpUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddHotelActivity extends AppCompatActivity {


    @BindView(R.id.t1)
    EditText t1;
    @BindView(R.id.housename)
    EditText housename;

    @BindView(R.id.perprice)
    EditText perprice;

    @BindView(R.id.zone)
    EditText zone;

    @BindView(R.id.address)
    EditText address;

    @BindView(R.id.intro)
    EditText intro;

    @BindView(R.id.type)
    Spinner type;

    @BindView(R.id.whole)
    RadioButton whole;

    @BindView(R.id.share)
    RadioButton share;

    @BindView(R.id.spacetype)
    RadioGroup spacetype;

    @BindView(R.id.roomnum)
    EditText roomnum;

    @BindView(R.id.mannum)
    EditText mannum;

    @BindView(R.id.bedroom)
    EditText bedroom;

    @BindView(R.id.bednum)
    EditText bednum;

    @BindView(R.id.listView)
    RecyclerView listView;

    @BindView(R.id.bathnum)
    EditText bathnum;

    @BindView(R.id.c1)
    CheckBox c1;
    @BindView(R.id.c2)
    CheckBox c2;
    @BindView(R.id.c3)
    CheckBox c3;
    @BindView(R.id.c4)
    CheckBox c4;
    @BindView(R.id.c5)
    CheckBox c5;
    @BindView(R.id.c6)
    CheckBox c6;
    @BindView(R.id.c7)
    CheckBox c7;
    @BindView(R.id.c8)
    CheckBox c8;
    @BindView(R.id.c9)
    CheckBox c9;
    @BindView(R.id.c10)
    CheckBox c10;
    @BindView(R.id.c11)
    CheckBox c11;
    @BindView(R.id.c12)
    CheckBox c12;
    @BindView(R.id.c13)
    CheckBox c13;
    @BindView(R.id.c14)
    CheckBox c14;
    @BindView(R.id.c15)
    CheckBox c15;
    @BindView(R.id.c16)
    CheckBox c16;
    @BindView(R.id.c17)
    CheckBox c17;
    @BindView(R.id.c18)
    CheckBox c18;
    @BindView(R.id.c19)
    CheckBox c19;
    @BindView(R.id.c20)
    CheckBox c20;
    @BindView(R.id.c21)
    CheckBox c21;
    @BindView(R.id.c22)
    CheckBox c22;
    @BindView(R.id.c23)
    CheckBox c23;
    @BindView(R.id.c24)
    CheckBox c24;

    @BindView(R.id.roomimg_up)
    Button roomimgUp;
    @BindView(R.id.room_sub)
    Button roomSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotel);
        ButterKnife.bind(this);

    }



    @OnClick({R.id.roomimg_up, R.id.room_sub})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.roomimg_up:
                addImage()
                break;
            case R.id.room_sub:
                addHotel();
                break;
        }
    }
    private void addImage(){


    }
    private void addHotel(){
        String userId = t1.getText().toString(); //房东id
        String name = housename.getText().toString();
        String price = perprice.getText().toString();
        String area = zone.getText().toString();
        String place = address.getText().toString();
        String intro1 = intro.getText().toString();
        String type1 = type.getSelectedItemPosition() + "";
        String spaceType = spacetype.getCheckedRadioButtonId() == R.id.whole ? "0" : "1";//空间类型
        String num = roomnum.getText().toString(); //房源房数
        String max = mannum.getText().toString(); //可容纳客户数
        String roommax = bedroom.getText().toString();//可容纳卧室数
        String beds = bednum.getText().toString();//床
        String bathnum1 = bathnum.getText().toString();
        ;//沐浴数
        String services = "很多";
        String images = "8888";


        HashMap params = new HashMap();
        params.put("userId", userId);
        params.put("name", name);
        params.put("price", price);
        params.put("area", area);
        params.put("place", place);
        params.put("intro", intro1);
        params.put("type", type1);
        params.put("spaceType", spaceType);
        params.put("num", num);
        params.put("max", max);
        params.put("roommax", roommax);
        params.put("beds", beds);
        params.put("bathnum", bathnum1);
        params.put("services", services);
        params.put("images", images);

        String url = "hotel/add";
        HttpUtil.httpPost(url, params, AddHotelActivity.this, new HttpUtil.HttpCallBack() {

            @Override
            public void success(String data) {
                Contans.makeToast("添加成功！", AddHotelActivity.this);
            }
        });

    }
}
