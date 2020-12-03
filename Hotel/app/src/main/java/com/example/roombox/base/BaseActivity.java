package com.example.roombox.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.roombox.bean.User;
import com.example.roombox.utils.ACache;
import com.example.roombox.utils.Contans;


public abstract class BaseActivity extends AppCompatActivity {
//    /***是否顯示標題欄*/
//    private  boolean isshowtitle = true;
//    /***是否顯示標題欄*/
//    private  boolean isshowstate = true;
  /***封裝toast對象**/
  private static Toast toast;
  /***獲取TAG的activity名稱**/
  protected final String TAG = this.getClass().getSimpleName();
  protected User userbean;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

//        if(!isshowtitle){
//            requestWindowFeature(Window.FEATURE_NO_TITLE);
//        }
    userbean = (User) ACache.get(this).getAsObject("userbean");
//        if(isshowstate){
//            getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
//                    WindowManager.LayoutParams. FLAG_FULLSCREEN);
//        }
    //設置布局
    setContentView(intiLayout());
    //初始化控件
    initView();
    //設置數據
    initData();
  }

  public void jumpAct(Class s) {
    Intent intent = new Intent(this, s);
    startActivity(intent);
  }

  public void jumpAct(Class s, int res) {
    Intent intent = new Intent(this, s);
    startActivityForResult(intent, res);
  }

  public void jumpAct(Class s, Bundle bundle) {
    Intent intent = new Intent(this, s);
    intent.putExtras(bundle);
    startActivity(intent);
  }

  /**
   * 設置布局
   *
   * @return
   */
  public abstract int intiLayout();

  /**
   * 初始化布局
   */
  public abstract void initView();

  /**
   * 設置數據
   */
  public abstract void initData();

//    /**
//     * 是否設置標題欄
//     *
//     * @return
//     */
//    public void setTitle(boolean ishow) {
//         isshowtitle=ishow;
//    }
//
//    /**
//     * 設置是否顯示狀態欄
//     * @param ishow
//     */
//    public void setState(boolean ishow) {
//        isshowstate=ishow;
//    }

  /**
   * 顯示長toast
   *
   * @param msg
   */
  public void toastLong(String msg) {
    if (null == toast) {
      toast = new Toast(this);
      toast.setDuration(Toast.LENGTH_LONG);
      toast.setText(msg);
      toast.show();
    } else {
      toast.setText(msg);
      toast.show();
    }
  }

  /**
   * 顯示短toast
   *
   * @param msg
   */
  public void toastShort(String msg) {
    if (null == toast) {
      toast = new Toast(this);
      toast.setDuration(Toast.LENGTH_SHORT);
      toast.setText(msg);
      toast.show();
    } else {
      toast.setText(msg);
      toast.show();
    }
  }

}
