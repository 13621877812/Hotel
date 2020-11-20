package com.fn.hotel.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fn.hotel.R;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {



    Unbinder unbinder;


    private double progressNum = 0.00;

    public MineFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mine, container, false);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden == false) {
//            SharedPreferences sp = getActivity().getSharedPreferences("points", MODE_PRIVATE);
//            Set<String> words = sp.getStringSet("words", new ArraySet<String>());
//            progressNum = words.size() / 15.0 * 100;
//            progress.setText(String.format("%.2f", progressNum) + "%");
//            long points = sp.getLong("points", 0);
//            point.setText(points + "");

        }
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.loginout)
    public void onViewClicked() {
        getActivity().finish();
    }
}
