package com.example.roombox.utils;

import android.content.Context;
import android.widget.Toast;

public class Contans {

    public static String  URL="http://192.168.2.103:8082/";
    public static String  IMGURL= URL +  "img/";
    public static String  HEADIMGURL= URL +  "head/";
    public static final void makeToast(String msg, Context context){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }


}
