package com.example.roombox.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.roombox.utils.ACache;
import com.example.roombox.utils.Contans;
import com.guoxiaoxing.phoenix.compress.picture.internal.PictureCompressor;
import com.guoxiaoxing.phoenix.core.PhoenixOption;
import com.guoxiaoxing.phoenix.core.listener.ImageLoader;
import com.guoxiaoxing.phoenix.core.model.MediaEntity;
import com.guoxiaoxing.phoenix.core.model.MimeType;
import com.guoxiaoxing.phoenix.picker.Phoenix;
import com.example.roombox.R;
import com.example.roombox.base.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class PersonDataAct extends BaseActivity {
  private ImageView iv;
  private EditText tv_title;
  private TextView tv_address;
  private EditText tv_price;
  private EditText tv_qq;
  private EditText tv_dec;
  private TextView tv_com;

  private String imgUrl;
  private String account;
  private String name;
  private String gender;


  private File file;
  private String compressPath;
  private Bundle extras;
  private EditText tv_account;
  private EditText tv_name;
  private EditText tv_dianti;
  private EditText tv_zx;
  private EditText tv_laiyuan;
  private RadioGroup radioGroup;
  private RadioButton radioButton1;
  private RadioButton radioButton2;
  private String imagePath = "1.png";

  @Override
  public int intiLayout() {
    return R.layout.act_fb;
  }

  @Override
  public void initView() {
    extras = getIntent().getExtras();

    Phoenix.config()
      .imageLoader(new ImageLoader() {
        @Override
        public void loadImage(Context mContext, ImageView imageView
          , String imagePath, int type) {
          Glide.with(mContext)
            .load(imagePath)
            .into(imageView);
        }
      });

    iv = (ImageView) findViewById(R.id.iv);
    iv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Phoenix.with()
          .theme(PhoenixOption.THEME_DEFAULT)// 主题
          .fileType(MimeType.ofImage())//显示的文件类型图片、视频、图片和视频
          .maxPickNumber(1)// 最大选择数量
          .minPickNumber(0)// 最小选择数量
          .spanCount(4)// 每行显示个数
          .enablePreview(true)// 是否开启预览
          .enableCamera(true)// 是否开启拍照
          .enableAnimation(true)// 选择界面图片点击效果
          .enableCompress(true)// 是否开启压缩
          .compressPictureFilterSize(1024)//多少kb以下的图片不压缩
          .compressVideoFilterSize(2018)//多少kb以下的视频不压缩
          .thumbnailHeight(160)// 选择界面图片高度
          .thumbnailWidth(160)// 选择界面图片宽度
          .enableClickSound(false)// 是否开启点击声音

          .mediaFilterSize(10000)//显示多少kb以下的图片/视频，默认为0，表示不限制
          //如果是在Activity里使用就传Activity，如果是在Fragment里使用就传Fragment
          .start(PersonDataAct.this, PhoenixOption.TYPE_PICK_MEDIA, 100);

      }
    });
    tv_account = (EditText) findViewById(R.id.tv_account);
    tv_name = (EditText) findViewById(R.id.tv_name);
    radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        RadioButton rb = (RadioButton) findViewById(checkedId);
        gender = rb.getText().toString();

      }
    });
    tv_com = (TextView) findViewById(R.id.tv_com);
    radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
    radioButton2 = (RadioButton) findViewById(R.id.radioButton2);

    String account = ACache.get(PersonDataAct.this).getAsString("account");
    String username = ACache.get(PersonDataAct.this).getAsString("username");
    String userimage = ACache.get(PersonDataAct.this).getAsString("userimage");
    String gender = ACache.get(PersonDataAct.this).getAsString("gender");
    if (username.equals("null")) {
      username = "";
    }
    tv_account.setText(account);
    tv_name.setText(username);
    if (gender.equals("null") || gender.equals("Male")) {
      radioButton1.setSelected(true);
    } else {
      radioButton2.setChecked(true);
    }

    Glide.with(PersonDataAct.this).load(Contans.HEADIMGURL + userimage).into(iv);


    tv_com.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        submit();

      }
    });
  }

  @Override
  public void initData() {
    if (extras != null) {


    }

  }

  List<Uri> mSelected;

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    List<MediaEntity> result = Phoenix.result(data);
    MediaEntity entity = result.get(0);
    String localPath = entity.getLocalPath();
    Glide.with(PersonDataAct.this).load(localPath).into(iv);
    File file = new File(localPath);
    try {
      File compressFIle = PictureCompressor.with(PersonDataAct.this)
        .savePath(PersonDataAct.this.getCacheDir().getAbsolutePath())
        .load(file)
        .get();
      if (compressFIle != null) {
        compressPath = compressFIle.getAbsolutePath();
        file = new File(localPath);

        saveUritoFile(file);


      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void saveUritoFile(File file1) {
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
      .connectTimeout(10, TimeUnit.SECONDS)
      .writeTimeout(10, TimeUnit.SECONDS)
      .readTimeout(20, TimeUnit.SECONDS)
      .build();
    RequestBody requestBody = new MultipartBody.Builder()
      .setType(MultipartBody.FORM)
      .addFormDataPart("file", file1.getName(),
        RequestBody.create(MediaType.parse("application/octet-stream"), file1))
      .build();
    Request request = new Request.Builder()
      .url(Contans.URL + "upload").post(requestBody)
      .build();
    Call call = okHttpClient.newCall(request);
    call.enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        Log.i("TAG", "onResponse: " + e.toString());
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {


        final String result = response.body().string();
        if (response.body() != null) {

          runOnUiThread(new Runnable() {
            @Override
            public void run() {

              try {
                Log.i("TAG", "run: " + result);
                JSONObject jsonObject = new JSONObject(result);
                String code = jsonObject.getString("code");
                imagePath = jsonObject.getString("data");
                if ("0".equals(code)) {
                  Contans.makeToast("upload success!", PersonDataAct.this);

                } else {
                  Contans.makeToast(jsonObject.getString("msg"), PersonDataAct.this);
                }

              } catch (JSONException e) {
                e.printStackTrace();
              } catch (Exception e) {
                e.printStackTrace();
              }

            }
          });


          response.body().close();
        }
      }
    });


  }

  private void submit() {
    // validate
    name = tv_name.getText().toString().trim();
    String account = tv_account.getText().toString().trim();

    if (TextUtils.isEmpty(name)) {
      Toast.makeText(this, "please enter your nickname", Toast.LENGTH_SHORT).show();
      return;
    }
    String url = Contans.URL + "user/update";
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
      .connectTimeout(10, TimeUnit.SECONDS)
      .writeTimeout(10, TimeUnit.SECONDS)
      .readTimeout(20, TimeUnit.SECONDS)
      .build();
    RequestBody requestBody = new FormBody.Builder().add("account", account)
      .add("name", name).add("gender", gender).add("img", imagePath).build();
    final Request request = new Request.Builder()
      .url(url)
      .post(requestBody)
      .build();

    Call call = okHttpClient.newCall(request);
    call.enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        Log.i("TAG", "onResponse: " + e.toString());
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {


        final String result = response.body().string();
        if (response.body() != null) {

          runOnUiThread(new Runnable() {
            @Override
            public void run() {

              try {
                Log.i("TAG", "run: " + result);
                JSONObject jsonObject = new JSONObject(result);
                String code = jsonObject.getString("code");

                if ("0".equals(code)) {
                  Contans.makeToast("update success!", PersonDataAct.this);


                  ACache.get(PersonDataAct.this).put("userimage", imagePath);
                  ACache.get(PersonDataAct.this).put("username", name);
                  ACache.get(PersonDataAct.this).put("gender", gender);

                  finish();


                } else {
                  Contans.makeToast(jsonObject.getString("msg"), PersonDataAct.this);
                }

              } catch (JSONException e) {
                e.printStackTrace();
              } catch (Exception e) {
                e.printStackTrace();
              }

            }
          });


          response.body().close();
        }
      }
    });


  }


}
