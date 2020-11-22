package com.example.roombox.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roombox.R;
import com.example.roombox.adapters.PointAdapter;
import com.example.roombox.bean.GiftBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColloectionFragment extends Fragment {




    private String[] gift1 = {"gift11", "gift12", "gift13", "gift14"};
    private String[] gift2 = {"gift21", "gift22", "gift23", "gift24"};
    private String[] gift3 = {"gift31", "gift32", "gift33", "gift34", "gift35"};
    private int[] giftPoint1 = {100,200,300,100};
    private int[] giftPoint2 = {100,200,300,100};
    private int[] giftPoint3 = {100,200,300,300,300};
    ArrayList<GiftBean> datas1 = new ArrayList<>();
    ArrayList<GiftBean> datas2 = new ArrayList<>();
    ArrayList<GiftBean> datas3 = new ArrayList<>();

    private String[] titles = new String[]{"毛绒玩具","汽车积木类","英语绘本"};

    @BindView(R.id.pointTextView)
    TextView pointTextView;
    Unbinder unbinder;
    @BindView(R.id.listView)
    RecyclerView listView;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    private PointAdapter pointAdapter;
    private long totalPoints;
    public ColloectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comment, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    private void initView() {

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        listView.setLayoutManager(manager);
        pointAdapter = new PointAdapter(getActivity());
        listView.setAdapter(pointAdapter);
        pointAdapter.setListener(new PointAdapter.ItemClickListener() {
            @Override
            public void itemClick(GiftBean bean) {
               int points = bean.getPoint();
               if (points > totalPoints){
                   Toast.makeText(getActivity(),"您的积分不足，请先学习后再兑换！",Toast.LENGTH_SHORT).show();
               }else {
                   Toast.makeText(getActivity(),"恭喜您成功兑换该礼品！",Toast.LENGTH_SHORT).show();
                   totalPoints = totalPoints -  points;
                   pointTextView.setText("您目前拥有" + totalPoints + "积分");
                   SharedPreferences sp = getActivity().getSharedPreferences("points",MODE_PRIVATE);
                   SharedPreferences.Editor editor = sp.edit();
                   editor.putLong("points", totalPoints);
                   editor.commit();
               }


            }
        });


        //选择器监听
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                refreshDatas(tab.getTag());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        for(int i=0;i<titles.length;i++){
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setTag(i);
            tabLayout.addTab(tab);
            tabLayout.getTabAt(i).setText(titles[i]);
        }






        Log.i("TAG", "initListView: ");

    }
    private void initData() {
        for (int i = 0; i <gift1.length ; i++) {
            String giftImage = gift1[i];
            int resourceImg = getActivity().getResources().getIdentifier(giftImage,"drawable","fn.com.emulate");
            int points = giftPoint1[i];
            GiftBean bean = new GiftBean();
            bean.setImage(resourceImg);
            bean.setPoint(points);
            datas1.add(bean);
        }
        for (int i = 0; i <gift2.length ; i++) {
            String giftImage = gift2[i];
            int resourceImg = getActivity().getResources().getIdentifier(giftImage,"drawable","fn.com.emulate");
            int points = giftPoint2[i];
            GiftBean bean = new GiftBean();
            bean.setImage(resourceImg);
            bean.setPoint(points);
            datas2.add(bean);
        }
        for (int i = 0; i <gift3.length ; i++) {
            String giftImage = gift3[i];
            int resourceImg = getActivity().getResources().getIdentifier(giftImage,"drawable","fn.com.emulate");
            int points = giftPoint3[i];
            GiftBean bean = new GiftBean();
            bean.setImage(resourceImg);
            bean.setPoint(points);
            datas3.add(bean);
        }
        pointAdapter.setDatas(datas1);
        pointAdapter.notifyDataSetChanged();
    }


    private void refreshDatas(Object index) {
      int idx = Integer.parseInt(index.toString());
      switch (idx){
          case 0:
              pointAdapter.setDatas(datas1);
              break;
          case 1:
              pointAdapter.setDatas(datas2);
              break;
          case 2:
              pointAdapter.setDatas(datas3);
              break;
          default:
              break;
      }

        pointAdapter.notifyDataSetChanged();

    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sp = getActivity().getSharedPreferences("points", MODE_PRIVATE);
        long points = sp.getLong("points", 0);
        totalPoints = points;
        pointTextView.setText("您目前拥有" + points + "积分");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
