package com.fn.hotel.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.WindowManager;

import com.fn.hotel.HotelListActivity;
import com.fn.hotel.R;
import com.fn.hotel.base.BaseActivity;
import com.fn.hotel.utils.ACache;

public class WelcomAct extends BaseActivity {



    @Override
    public int intiLayout() {
        return R.layout.act_wel;
    }

    @Override
    public void initView() {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);




        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                String islogin = ACache.get(WelcomAct.this).getAsString("islogin");
                if (TextUtils.isEmpty(islogin)){
                    Intent intent = new Intent(WelcomAct.this,LoginActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(WelcomAct.this, HotelListActivity.class);
                    startActivity(intent);

                }

                finish();
            }
        },2000);
    }

    @Override
    public void initData() {

    }
}
