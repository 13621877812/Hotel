package com.example.roombox.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.example.roombox.R;
import com.example.roombox.adapters.RoomAdapter;
import com.example.roombox.bean.RoomBean;
import com.example.roombox.utils.Contans;
import com.example.roombox.utils.HttpUtil;
import com.google.gson.Gson;
import com.guoxiaoxing.phoenix.compress.picture.internal.PictureCompressor;
import com.guoxiaoxing.phoenix.core.PhoenixOption;
import com.guoxiaoxing.phoenix.core.listener.ImageLoader;
import com.guoxiaoxing.phoenix.core.model.MediaEntity;
import com.guoxiaoxing.phoenix.core.model.MimeType;
import com.guoxiaoxing.phoenix.picker.Phoenix;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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

//    @BindView(R.id.bednum)
//    EditText bednum;

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


    @BindView(R.id.roomimg_up)
    ImageButton roomimgUp;
    @BindView(R.id.room_sub)
    Button roomSub;
    private File file;
    private String compressPath;
    private String imagePath = "1.png";
    private RoomAdapter roomAdapter;
    ArrayList rooms = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotel);
        ButterKnife.bind(this);
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

        initListView();
        initListener();


    }

    private void initListener(){
        bedroom.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                } else {
                    if (!TextUtils.isEmpty(bedroom.getText().toString())){
                        Integer num = Integer.parseInt(bedroom.getText().toString());
                        rooms.clear();
                        for (int i = 0; i < num; i++) {
                            RoomBean roomBean = new RoomBean();
                            rooms.add(roomBean);
                        }
                        roomAdapter.setDatas(rooms);
                        roomAdapter.notifyDataSetChanged();
                    }

                }
            }
        });
    }
    private void initListView(){
        LinearLayoutManager manager = new LinearLayoutManager(this);
        listView.setLayoutManager(manager);
        roomAdapter = new RoomAdapter(this);
        listView.setAdapter(roomAdapter);
    }
    @OnClick({R.id.roomimg_up, R.id.room_sub})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.roomimg_up:
                addImage();
                break;
            case R.id.room_sub:
                addHotel();
                break;
        }
    }

    private void addImage() {
        Phoenix.with()
                .theme(PhoenixOption.THEME_DEFAULT)// 主題
                .fileType(MimeType.ofImage())//顯示的文件類型圖片、視頻、圖片和視頻
                .maxPickNumber(1)// 最大選擇數量
                .minPickNumber(0)// 最小選擇數量
                .spanCount(4)// 每行顯示個數
                .enablePreview(true)// 是否開啟預覽
                .enableCamera(true)// 是否開啟拍照
                .enableAnimation(true)// 選擇界面圖片點擊效果
                .enableCompress(true)// 是否開啟壓縮
                .compressPictureFilterSize(1024)//多少kb以下的圖片不壓縮
                .compressVideoFilterSize(2018)//多少kb以下的視頻不壓縮
                .thumbnailHeight(160)// 選擇界面圖片高度
                .thumbnailWidth(160)// 選擇界面圖片寬度
                .enableClickSound(false)// 是否開啟點擊聲音

                .mediaFilterSize(10000)//顯示多少kb以下的圖片/視頻，默認為0，表示不限制
                //如果是在Activity里使用就傳Activity，如果是在Fragment里使用就傳Fragment
                .start(AddHotelActivity.this, PhoenixOption.TYPE_PICK_MEDIA, 100);

    }

    private void addHotel() {
        String userId = t1.getText().toString(); //房東id
        String name = housename.getText().toString();
        String price = perprice.getText().toString();
        String area = zone.getText().toString();
        String place = address.getText().toString();
        String intro1 = intro.getText().toString();
        String type1 = type.getSelectedItemPosition() + "";
        String spaceType = spacetype.getCheckedRadioButtonId() == R.id.whole ? "0" : "1";//空間類型
        String num = roomnum.getText().toString(); //房源房數
        String max = mannum.getText().toString(); //可容納客戶數
        String roommax = bedroom.getText().toString();//可容納臥室數
//        String beds = bednum.getText().toString();//床

        Gson gson = new Gson();
        String beds =  gson.toJson(rooms);



        String bathnum1 = bathnum.getText().toString();
        ;//沐浴數
        StringBuffer services = new StringBuffer();;
        CheckBox[] boxes = new CheckBox[]{c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c10,c12,c13,c14,c15,c16,c17,c18,c19,c20,c21,c22,c23 };
        String service = "";
        for (int i = 0; i < boxes.length; i++) {
            CheckBox box = boxes[i];
            if(box.isChecked()){
                if (service.length() != 0){
                    services.append(service+";");
                }
                service = i + "";
            }
        }
        if (service.length() != 0){
            services.append(service+"");
        }

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
        params.put("services", services.toString());
        params.put("images", imagePath);

        String url = "hotel/add";
        HttpUtil.httpPost(url, params, AddHotelActivity.this, new HttpUtil.HttpCallBack() {

            @Override
            public void success(String data) {
                Contans.makeToast("添加成功！", AddHotelActivity.this);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        List<MediaEntity> result = Phoenix.result(data);
        MediaEntity entity = result.get(0);
        String localPath = entity.getLocalPath();
        Glide.with(AddHotelActivity.this).load(localPath).into(roomimgUp);
        File file = new File(localPath);
        try {
            File compressFIle = PictureCompressor.with(AddHotelActivity.this)
                    .savePath(AddHotelActivity.this.getCacheDir().getAbsolutePath())
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
                Log.i("TAGUPLOAD", "onResponse: " + result);
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
                                    Contans.makeToast("upload success!", AddHotelActivity.this);

                                } else {
                                    Contans.makeToast(jsonObject.getString("msg"), AddHotelActivity.this);
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
