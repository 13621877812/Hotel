package com.example.roombox;

import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.roombox.base.BaseActivity;
import com.example.roombox.bean.HotelBean;
import com.example.roombox.utils.SimpleAdapter;

import java.util.ArrayList;

public class HotelListActivity extends BaseActivity {

  private LinearLayout ll_exit;
  private RecyclerView recyclerView;
  private SimpleAdapter adapter;
  private ImageView headImageView;
  private TextView nameText;
  private EditText keyEdit;


  private ArrayList<HotelBean> keyList = new ArrayList<>();
  private ArrayList<HotelBean> originalKeyList = new ArrayList<>();

  @Override
  public int intiLayout() {
    return R.layout.activity_list;
  }

  @Override
  public void initView() {

  }

  @Override
  public void initData() {

  }
//
//        ll_exit = (LinearLayout) findViewById(R.id.ll_exit);
//        keyEdit = (EditText) findViewById(R.id.keyEdit);
//        headImageView = (ImageView) findViewById(R.id.iv_head);
//        nameText = (TextView) findViewById(R.id.tv_account);
//        keyEdit.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) { }
//            @Override
//            public void afterTextChanged(Editable s) {
//               //search key words
//                String keywords = s.toString();
//                keyList.clear();
//                for (int i = 0; i < originalKeyList.size(); i++) {
//                    HotelBean hotelBean = originalKeyList.get(i);
//                    String title = hotelBean.getName();
//                    String type = hotelBean.getType();
//                    if (title.contains(keywords) || type.contains(keywords)){
//                        keyList.add(hotelBean);
//                    }
//                }
//                adapter.notifyDataSetChanged();
//            }
//        });
//
//        recyclerView = (RecyclerView)findViewById(R.id.listView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new SimpleAdapter(R.layout
//          .item_house, keyList, new SimpleAdapter.ConVert<HotelBean>() {
//            @Override
//            public void convert(BaseViewHolder helper, HotelBean o) {
//
//                String name = TextUtils.isEmpty(o.getName())?" ":o.getName();
//                helper.setText(R.id.tv_title, name);
//
//                String price = TextUtils.isEmpty(o.getPrice())?" ":o.getPrice();
//                helper.setText(R.id.tv_price, price);
//
//
//                String type = TextUtils.isEmpty(o.getType())?" ":o.getType();
//                helper.setText(R.id.tv_type, type);
//
//                String place = TextUtils.isEmpty(o.getPlace())?" ":o.getPlace();
//                helper.setText(R.id.tv_place, place);
//
//                RoundedCorners roundedCorners= new RoundedCorners(10);
//                RequestOptions options=RequestOptions.bitmapTransform(roundedCorners).override(60, 60);
//                String img = TextUtils.isEmpty(o.getImg())?" ":o.getImg();
//                ImageView view = helper.getView(R.id.iv);
//                Glide.with(HotelListActivity.this).load(Contans.IMGURL + img ).apply(options).into(view);
//
//            }
//        });
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                HotelBean bean = keyList.get(position);
//                Intent intent = new Intent(HotelListActivity.this,HotelDetialAct.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("bean",bean);
//                intent.putExtras(bundle);
//                startActivity(intent);
//            }
//        });
//        recyclerView.setAdapter(adapter);
//        headImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(HotelListActivity.this, PersonDataAct.class);
//                startActivity(intent);
//
//            }
//        });
//
//
//
//
//
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        getData();
//    }
//
//    private void getData() {
//        String account = ACache.get(HotelListActivity.this).getAsString("account");
//        String username = ACache.get(HotelListActivity.this).getAsString("username");
//        Log.i("TAG", "getData: " + username);
//
//
//        if (username.equals("null")){
//           username = account;
//
//        }
//        nameText.setText(username);
//        String img = ACache.get(HotelListActivity.this).getAsString("userimage");
//
//        RoundedCorners roundedCorners= new RoundedCorners(30);
//        RequestOptions options=RequestOptions.bitmapTransform(roundedCorners).override(60, 60);
//        Glide.with(HotelListActivity.this).load(Contans.HEADIMGURL + img ).apply(options).into(headImageView);
//
//
//
//
//
//
//        String url = Contans.URL + "hotel/list";
//        OkHttpClient okHttpClient  = new OkHttpClient.Builder()
//                .connectTimeout(10, TimeUnit.SECONDS)
//                .writeTimeout(10,TimeUnit.SECONDS)
//                .readTimeout(20, TimeUnit.SECONDS)
//                .build();
//
//        final Request request = new Request.Builder()
//                .url(url)
//                .get()
//                .build();
//
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.i("TAG", "onResponse: " + e.toString());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//
//                final  String result = response.body().string();
//                if (response.body() != null) {
//
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            keyList.clear();
//                            originalKeyList.clear();
//                            try {
//                                Log.i("TAG", "run: " + result);
//                                JSONObject jsonObject = new JSONObject(result);
//                                String code = jsonObject.getString("code");
//                                String data = jsonObject.getString("data");
//                                if ("0".equals(code)){
//                                    Contans.makeToast("data success!", HotelListActivity.this);
//                                    Type type1 = new TypeToken<ArrayList<HotelBean>>(){}.getType();
//                                    ArrayList<HotelBean> datas = new Gson().fromJson(data,type1);
//                                    keyList.addAll(datas);
//                                    originalKeyList.addAll(datas);
//                                    adapter.notifyDataSetChanged();
//
//
//                                }else {
//                                    Contans.makeToast("data error!", HotelListActivity.this);
//                                }
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//                    });
//
//
//                    response.body().close();
//                }
//            }
//        });
//
//
//
//
//
//    }
//    @Override
//    public void initData() {
//        ll_exit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ACache.get(HotelListActivity.this).clear();
//                startActivity(new Intent(HotelListActivity.this, LoginActivity.class));
//                finish();
//
//            }
//        });
//
//
//
//
//    }


}
